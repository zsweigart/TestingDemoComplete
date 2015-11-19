package com.yodle.testingdemocomplete.activity;

import android.content.Intent;

import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.persistence.SharedPrefsDatastore;
import com.yodle.testingdemocomplete.testingframework.CustomRobolectricRunner;
import com.yodle.testingdemocomplete.testingframework.StudentGenerator;
import com.yodle.testingdemocomplete.view.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricRunner.class)
public class LoginActivityTest {
    private LoginView loginView;
    private LoginController loginController;
    private MainApp mainApp;
    private SharedPrefsDatastore datastore;
    private StudentGenerator studentGenerator;
    private LoginActivity loginActivity;

    @Before
    public void setup() {
        studentGenerator = new StudentGenerator();
        datastore = mock(SharedPrefsDatastore.class);
        when(datastore.getStudents()).thenReturn(studentGenerator.getValidStudentLogins());
        mainApp = mock(MainApp.class);
        when(mainApp.getSharedPrefsDatastore()).thenReturn(datastore);
        loginController = mock(LoginController.class);
        loginView = mock(LoginView.class);

        loginActivity = setupLoginActivity();
        loginActivity.setLoginController(loginController);
    }

    @Test
    public void openRegistrationActivity_whenNotGivenEmail_opensRegistrationActivityWithoutData() {
        loginActivity.openRegistrationActivity();

        Intent expectedIntent = RegistrationActivity.getBlankRegistrationIntent(loginActivity);
        assertThat(shadowOf(loginActivity).getNextStartedActivity(), equalTo(expectedIntent));
    }

    @Test
    public void openRegistrationActivity_whenGivenEmail_opensRegistrationActivityWithData() {
        String email = "test@test.com";
        loginActivity.openRegistrationActivity(email);

        Intent expectedIntent = RegistrationActivity.getRegistrationIntentWithEmail(loginActivity, email);
        assertThat(shadowOf(loginActivity).getNextStartedActivity(), equalTo(expectedIntent));
    }

    @Test
    public void openGpaActivity_whenGivenStudent_opensGpaCalculatorActivity() {
        loginActivity.openGpaCalculatorActivity(studentGenerator.first);

        Intent expectedIntent = GpaCalculatorActivity.getGpaIntentWithStudent(loginActivity, studentGenerator.first);
        assertThat(shadowOf(loginActivity).getNextStartedActivity(), equalTo(expectedIntent));
    }

    @Test
    public void onActivityResult_whenReturningFromRegisterCancelled_doesnNotAttemptsLogin() {
        loginActivity.onActivityResult(LoginActivity.REGISTER_STUDENT, LoginActivity.RESULT_CANCELED, null);

        verify(loginController, never()).attemptSignIn(anyString(), anyString());
    }

    @Test
    public void onActivityResult_whenReturningFromRegisterSucessfully_attemptsLogin() {
        Intent intent = new Intent();
        intent.putExtra(LoginActivity.NEW_REGISTERED_STUDENT, studentGenerator.first);
        loginActivity.onActivityResult(LoginActivity.REGISTER_STUDENT, LoginActivity.RESULT_OK, intent);

        verify(loginController).attemptSignIn(studentGenerator.first.getEmail(), studentGenerator.first.getPassword());
    }

    /*
     * Helper Methods
     */
    private LoginActivity setupLoginActivity() {
        return Robolectric.buildActivity(LoginActivity.class).create().start().resume().visible().get();
    }
}
