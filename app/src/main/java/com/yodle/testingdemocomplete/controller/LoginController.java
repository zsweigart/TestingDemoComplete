package com.yodle.testingdemocomplete.controller;

import android.support.annotation.VisibleForTesting;

import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.LoginView;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class LoginController {
    private LoginView loginView;
    private LoginActivityNavigator loginActivityNavigator;
    private Datastore datastore;

    public LoginController(LoginView loginView, LoginActivityNavigator loginActivityNavigator) {
        this.loginView = loginView;
        this.loginActivityNavigator = loginActivityNavigator;
        this.datastore = loginActivityNavigator.getDatastore();
    }

    public void attemptSignIn(String email, String password) {
        Set<Student> studentSet = datastore.getStudents();
        for(Student student : studentSet) {
            if(student.getEmail().equals(email) && student.getPassword().equals(password)) {
                loginActivityNavigator.openGpaCalculatorActivity(student);
                return;
            }
        }

        loginView.showSignInInvalidEmailOrPassword();
    }

    public void register(String email) {
        if(StringUtils.isEmpty(email)) {
            loginActivityNavigator.openRegistrationActivity();
        } else {
            loginActivityNavigator.openRegistrationActivity(email);
        }
    }

    public interface LoginActivityNavigator {
        void openRegistrationActivity();
        void openRegistrationActivity(String email);
        void openGpaCalculatorActivity(Student student);
        Datastore getDatastore();
    }
}
