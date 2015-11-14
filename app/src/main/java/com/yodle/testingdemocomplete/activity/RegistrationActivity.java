package com.yodle.testingdemocomplete.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.controller.RegistrationController;
import com.yodle.testingdemocomplete.view.RegistrationView;

public class RegistrationActivity extends AppCompatActivity implements RegistrationController.RegistrationActivityNavigator {
    private static final String EMAIL_EXTRA = "EMAIL";
    private RegistrationView registrationView;
    private RegistrationController registrationController;
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.activity_registration, null);
        setContentView(rootView);

        mainApp = (MainApp) getApplication();

        registrationView = new RegistrationView(this, rootView);
        registrationController = new RegistrationController(registrationView, this);
        registrationView.setRegistrationController(registrationController);
    }

    public static Intent getBlankRegistrationIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    public static Intent getRegistrationIntentWithEmail(Context context, String email) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        intent.putExtra(EMAIL_EXTRA, email);
        return intent;
    }
}
