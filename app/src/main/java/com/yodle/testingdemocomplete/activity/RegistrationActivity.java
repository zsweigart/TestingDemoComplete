package com.yodle.testingdemocomplete.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.controller.RegistrationController;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.RegistrationView;

public class RegistrationActivity extends AppCompatActivity implements RegistrationController.RegistrationActivityNavigator {
    private static final String EMAIL_EXTRA = "EMAIL";
    private RegistrationView registrationView;
    private RegistrationController registrationController;
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrationView = new RegistrationView(this);
        setContentView(registrationView);

        mainApp = (MainApp) getApplication();

        registrationController = new RegistrationController(registrationView, this);
        registrationView.setRegistrationController(registrationController);
    }

    @Override
    public Datastore getDatastore() {
        return mainApp.getSharedPrefsDatastore();
    }

    @Override
    public void closeAndLogin(Student student) {
        Intent resultData = new Intent();
        resultData.putExtra(LoginActivity.NEW_REGISTERED_STUDENT, student);

        this.setResult(RESULT_OK, resultData);
        finish();
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
