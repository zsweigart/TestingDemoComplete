package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.activity.RegistrationActivity;
import com.yodle.testingdemocomplete.view.RegistrationView;

public class RegistrationController {
    private RegistrationView registrationView;
    private RegistrationActivityNavigator registrationActivityNavigator;

    public RegistrationController(RegistrationView registrationView, RegistrationActivityNavigator registrationActivityNavigator) {
        this.registrationView = registrationView;
        this.registrationActivityNavigator = registrationActivityNavigator;
    }

    public interface RegistrationActivityNavigator {

    }
}
