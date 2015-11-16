package com.yodle.testingdemocomplete.view;

import android.app.Activity;
import android.widget.EditText;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.activity.LoginActivity;
import com.yodle.testingdemocomplete.controller.LoginController;
import com.yodle.testingdemocomplete.customviews.DialogUtil;
import com.yodle.testingdemocomplete.testingframework.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(CustomRobolectricRunner.class)
public class LoginViewTest {
    private LoginController loginController;
    private LoginView loginView;
    private LoginActivity loginActivity;
    private DialogUtil dialogUtil;

    @Before
    public void setup() {
        loginController = mock(LoginController.class);
        loginActivity = mock(LoginActivity.class);
        loginView = new LoginView(RuntimeEnvironment.application, loginActivity);
        loginView.setLoginController(loginController);
        dialogUtil = mock(DialogUtil.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(dialogUtil).alertError(Matchers.<Activity>any(), anyString());

        loginView.setDialogUtil(dialogUtil);
    }

    @Test
    public void clickingSignInButton_whenNoCredentials_callsAttemptSignIn() {
        loginView.findViewById(R.id.login_sign_in).performClick();

        String email = ((EditText)loginView.findViewById(R.id.login_email)).getText().toString();
        String password = ((EditText)loginView.findViewById(R.id.login_password)).getText().toString();

        verify(loginController).attemptSignIn(email, password);
    }

    @Test
    public void clickingSignInButton_whenCredentialsGiven_callsAttemptSignIn() {
        EditText emailEditText = ((EditText)loginView.findViewById(R.id.login_email));
        EditText passwordEditText = ((EditText)loginView.findViewById(R.id.login_password));
        emailEditText.setText("first@first.com");
        passwordEditText.setText("asdf1234");

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginView.findViewById(R.id.login_sign_in).performClick();

        verify(loginController).attemptSignIn(email, password);
    }

    @Test
    public void clickingRegisterButton_whenFieldsEmpty_callRegisterWithNoParameters(){
        loginView.findViewById(R.id.login_register).performClick();

        verify(loginController).register();
    }

    @Test
    public void clickingRegisterButton_whenEmailNotEmpty_callRegisterWithEmail(){
        String email = "first@first.com";
        ((EditText)loginView.findViewById(R.id.login_email)).setText(email);
        loginView.findViewById(R.id.login_register).performClick();

        verify(loginController).register(email);
    }

    @Test
    public void callingShowSignInInvalidEmailOrPassword_callsDialogController() {
        loginView.showSignInInvalidEmailOrPassword();

        verify(dialogUtil).alertError(loginActivity, "Sorry we couldn't find that username and password combination. Please check your credentials and try again.");
    }
}
