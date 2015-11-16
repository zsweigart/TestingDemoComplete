package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.testingframework.StudentGenerator;
import com.yodle.testingdemocomplete.view.LoginView;
import com.yodle.testingdemocomplete.view.RegistrationView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest {

    private RegistrationController registrationController;
    private RegistrationView registrationView;
    private RegistrationController.RegistrationActivityNavigator registrationActivityNavigator;
    private StudentGenerator studentGenerator;
    private Set<Student> registeredStudents;
    private Datastore datastore;

    @Before
    public void setup() {
        registrationView = mock(RegistrationView.class);
        registrationActivityNavigator = mock(RegistrationController.RegistrationActivityNavigator.class);
        registeredStudents = new HashSet<>();
        studentGenerator = new StudentGenerator();
        datastore = mock(Datastore.class);
        when(datastore.getStudents()).thenReturn(studentGenerator.getValidStudentLogins());
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                registeredStudents.add((Student) invocation.getArguments()[0]);
                return null;
            }
        }).when(datastore).persistStudent(Matchers.<Student>any());
        when(registrationActivityNavigator.getDatastore()).thenReturn(datastore);

        registrationController = new RegistrationController(registrationView, registrationActivityNavigator);
    }

    @Test
    public void registerStudent_whenStudentNull_doesNotRegistersStudentInDatastore() {
        registrationController.registerNewStudent(null);

        verify(datastore, never()).persistStudent(Matchers.<Student>any());
    }

    @Test
    public void registerStudent_whenStudentEmailNull_doesNotRegistersStudentInDatastore() {
        Student student = studentGenerator.first;
        student.setEmail(null);
        registrationController.registerNewStudent(student);

        verify(datastore, never()).persistStudent(Matchers.<Student>any());
    }

    @Test
    public void registerStudent_whenStudentPasswordNull_doesNotRegistersStudentInDatastore() {
        Student student = studentGenerator.first;
        student.setPassword(null);
        registrationController.registerNewStudent(student);

        verify(datastore, never()).persistStudent(Matchers.<Student>any());
    }

    @Test
    public void registerStudent_whenGivenStudent_registersStudentInDatastore() {
        registrationController.registerNewStudent(studentGenerator.first);

        assertTrue(registeredStudents.contains(studentGenerator.first));
        verify(registrationActivityNavigator).closeAndLogin(studentGenerator.first);
    }

}
