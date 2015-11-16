package com.yodle.testingdemocomplete.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.customviews.DialogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginView extends LinearLayout {
    private Activity activity;
    private Context context;
    private View view;
    private LoginController loginController;
    private DialogUtil dialogUtil;

    @Bind(R.id.login_email)
    EditText loginEmail;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_sign_in)
    Button loginSignInButton;
    @Bind(R.id.login_register)
    Button loginRegisterButton;

    public LoginView(Context context, Activity activity) {
        super(context);
        this.context = context;
        inflate(context, R.layout.activity_login, this);
        this.activity = activity;
        this.dialogUtil = new DialogUtil();

        ButterKnife.bind(this);
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
        dialogUtil.alertError(activity, context.getString(R.string.login_invalid_username_or_password));
    }

    @VisibleForTesting
    void setDialogUtil(DialogUtil dialogUtil) {
        this.dialogUtil = dialogUtil;
    }
}
