package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.testingframework.StudentGenerator;
import com.yodle.testingdemocomplete.view.LoginView;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController loginController;
    private LoginView loginView;
    private LoginController.LoginActivityNavigator loginActivityNavigator;
    private Set<Student> students;
    private StudentGenerator studentGenerator;

    @Before
    public void setup() {
        loginView = mock(LoginView.class);
        loginActivityNavigator = mock(LoginController.LoginActivityNavigator.class);
        studentGenerator = new StudentGenerator();
        students = studentGenerator.getValidStudentLogins();

        when(loginActivityNavigator.getDatastore()).thenReturn(new Datastore() {
            @Override
            public void persistStudent(Student student) {

            }

            @Override
            public Set<Student> getStudents() {
                return students;
            }
        });

        loginController = new LoginController(loginView, loginActivityNavigator);
    }

    @Test
    public void attemptSignIn_whenCredentialsNull_callsShowSignInInvalidEmailOrPassword() {
        loginController.attemptSignIn(null, null);

        verify(loginView).showSignInInvalidEmailOrPassword();
    }

    @Test
    public void attemptSignIn_whenCredentialsEmpty_callsShowSignInInvalidEmailOrPassword() {
        loginController.attemptSignIn("", "");

        verify(loginView).showSignInInvalidEmailOrPassword();
    }

    @Test
    public void attemptSignIn_whenCredentialsInvalid_callsShowSignInInvalidEmailOrPassword() {
        loginController.attemptSignIn("third@third.com", "abcde54321");

        verify(loginView).showSignInInvalidEmailOrPassword();
    }

    @Test
    public void attemptSignIn_whenEmailValidAndPasswordIncorrect_callsShowSignInInvalidEmailOrPassword() {
        loginController.attemptSignIn("first@first.com", "password");

        verify(loginView).showSignInInvalidEmailOrPassword();
    }

    @Test
    public void attemptSignIn_whenCredentialsValid_callsOpenGpaCalculatorActivity() {
        loginController.attemptSignIn("second@second.com", "password");

        verify(loginActivityNavigator).openGpaCalculatorActivity(studentGenerator.second);
    }

    @Test
    public void register_whenNoEmail_callsOpenRegistrationActivity() {
        loginController.register(null);

        verify(loginActivityNavigator).openRegistrationActivity();
    }

    @Test
    public void register_whenEmail_callsOpenRegistrationActivityWithEmail() {
        String email = "third@third.com";
        loginController.register(email);

        verify(loginActivityNavigator).openRegistrationActivity(email);
    }
}
