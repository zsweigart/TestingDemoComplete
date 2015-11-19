package com.yodle.testingdemocomplete.view;

import com.yodle.testingdemocomplete.controller.RegistrationController;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.testingframework.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(CustomRobolectricRunner.class)
public class RegistrationViewTest {
    private RegistrationController registrationController;
    private RegistrationView registrationView;

    @Before
    public void setup() {
        registrationView = new RegistrationView(RuntimeEnvironment.application);
        registrationController = mock(RegistrationController.class);
        registrationView.setRegistrationController(registrationController);
    }

    @Test
    public void settingAllFields_enablesButton() {
        registrationView.registerFirstName.setText("first");
        registrationView.registerLastName.setText("last");
        registrationView.registerEmail.setText("first@first.com");
        registrationView.registerPassword.setText("password");
        registrationView.registerAge.setText("23");

        assertThat(registrationView.registerButton.isEnabled(), equalTo(true));
    }

    @Test
    public void settingFieldToNull_whenAllFieldsHaveText_disablesButton() {
        registrationView.registerFirstName.setText("first");
        registrationView.registerLastName.setText("last");
        registrationView.registerEmail.setText("first@first.com");
        registrationView.registerPassword.setText("password");
        registrationView.registerAge.setText("23");
        assertThat(registrationView.registerButton.isEnabled(), equalTo(true));

        registrationView.registerFirstName.setText(null);
        assertThat(registrationView.registerButton.isEnabled(), equalTo(false));
    }

    @Test
    public void register_whenAgeIsAnInteger_callsRegister() {
        registrationView.registerFirstName.setText("first");
        registrationView.registerLastName.setText("last");
        registrationView.registerEmail.setText("first@first.com");
        registrationView.registerPassword.setText("password");
        registrationView.registerAge.setText("23");

        registrationView.registerButton.performClick();

        verify(registrationController).registerNewStudent(Matchers.<Student>any());
    }

    @Test
    public void register_whenAgeIsNotAnInteger_showsError() {
        registrationView.registerFirstName.setText("first");
        registrationView.registerLastName.setText("last");
        registrationView.registerEmail.setText("first@first.com");
        registrationView.registerPassword.setText("password");
        registrationView.registerAge.setText("23.5");

        registrationView.registerButton.performClick();

        assertThat(registrationView.registerAge.getError().toString(), equalTo("Sorry, age must be a number"));
        verify(registrationController, never()).registerNewStudent(Matchers.<Student>any());
    }
}
