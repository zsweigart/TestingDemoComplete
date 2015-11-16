package com.yodle.testingdemocomplete.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginController.LoginActivityNavigator {
    public static final String NEW_REGISTERED_STUDENT = "NEW_REGISTERED_STUDENT";

    private static final int REGISTER_STUDENT = 1;

    private LoginView loginView;
    private LoginController loginController;
    private MainApp mainApp;
    private Datastore datastore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginView = new LoginView(this, this);
        setContentView(loginView);

        mainApp = (MainApp) getApplication();
        datastore = mainApp.getSharedPrefsDatastore();
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
        startActivityForResult(RegistrationActivity.getBlankRegistrationIntent(this), REGISTER_STUDENT);
    }

    @Override
    public void openRegistrationActivity(String email) {
        startActivityForResult(RegistrationActivity.getRegistrationIntentWithEmail(this, email), REGISTER_STUDENT);
    }

    @Override
    public void openGpaCalculatorActivity(Student student) {
        startActivity(GpaCalculatorActivity.getGpaIntentWithStudent(this, student));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REGISTER_STUDENT) {
                Student student = (Student) data.getSerializableExtra(NEW_REGISTERED_STUDENT);
                loginController.attemptSignIn(student.getEmail(), student.getPassword());
            }
        }
    }

    @Override
    public Datastore getDatastore() {
        return datastore;
    }
}
