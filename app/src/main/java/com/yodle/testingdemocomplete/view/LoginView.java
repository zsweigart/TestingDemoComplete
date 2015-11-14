package com.yodle.testingdemocomplete.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.activity.LoginActivity;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.customviews.DialogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginView {
    private LoginActivity activity;
    private View view;
    private LoginController loginController;

    @Bind(R.id.login_email)
    EditText loginEmail;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_sign_in)
    Button loginSignInButton;
    @Bind(R.id.login_register)
    Button loginRegisterButton;

    public LoginView(LoginActivity activity, View view) {
        this.activity = activity;
        this.view = view;

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.login_sign_in)
    public void signIn() {
        loginController.attemptSignIn(loginEmail.getText().toString(), loginPassword.getText().toString());
    }

    @OnClick(R.id.login_register)
    public void register() {
        String loginEmailString = loginEmail.getText().toString();
        if(TextUtils.isEmpty(loginEmailString)) {
            loginController.register();
        } else {
            loginController.register(loginEmailString);
        }
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void showSignInInvalidEmailOrPassword() {
        DialogUtil.alertError(activity, activity.getString(R.string.login_invalid_username_or_password));
    }
}