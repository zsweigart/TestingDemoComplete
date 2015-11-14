package com.yodle.testingdemocomplete.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginController.LoginActivityNavigator {
    private LoginView loginView;
    private LoginController loginController;
    private MainApp mainApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View rootView = inflater.inflate(R.layout.activity_login, null);
        setContentView(rootView);

        mainApp = (MainApp) getApplication();

        loginView = new LoginView(this, rootView);
        loginController = new LoginController(loginView, this);
        loginView.setLoginController(loginController);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    @Override
    public void openRegistrationActivity() {
        startActivity(RegistrationActivity.getBlankRegistrationIntent(this));
    }

    @Override
    public void openRegistrationActivity(String email) {
        startActivity(RegistrationActivity.getRegistrationIntentWithEmail(this, email));
    }

    @Override
    public void openGpaCalculatorActivity(Student student) {
        startActivity(GpaCalculatorActivity.getGpaIntentWithStudent(this, student));
    }

    @Override
    public Datastore getDatastore() {
        return mainApp.getDatastore();
    }
}
