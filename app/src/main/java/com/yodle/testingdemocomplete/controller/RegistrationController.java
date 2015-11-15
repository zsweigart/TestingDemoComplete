package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.activity.RegistrationActivity;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.RegistrationView;

public class RegistrationController {
    private RegistrationView registrationView;
    private RegistrationActivityNavigator registrationActivityNavigator;
    private Datastore datastore;

    public RegistrationController(RegistrationView registrationView, RegistrationActivityNavigator registrationActivityNavigator) {
        this.registrationView = registrationView;
        this.registrationActivityNavigator = registrationActivityNavigator;
        this.datastore = registrationActivityNavigator.getDatastore();
    }

    public void registerNewStudent(Student student) {
        datastore.persistStudent(student);
        registrationActivityNavigator.closeAndLogin(student);
    }

    public interface RegistrationActivityNavigator {
        Datastore getDatastore();
        void closeAndLogin(Student student);
    }
}
