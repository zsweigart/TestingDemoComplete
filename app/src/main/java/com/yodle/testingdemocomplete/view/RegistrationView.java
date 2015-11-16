package com.yodle.testingdemocomplete.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.controller.RegistrationController;
import com.yodle.testingdemocomplete.customviews.AfterTextChangedWatcher;
import com.yodle.testingdemocomplete.model.Student;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationView {
    private Context context;
    private View view;
    private RegistrationController registrationController;

    @Bind(R.id.register_email)
    EditText registerEmail;
    @Bind(R.id.register_password)
    EditText registerPassword;
    @Bind(R.id.register_first_name)
    EditText registerFirstName;
    @Bind(R.id.register_last_name)
    EditText registerLastName;
    @Bind(R.id.register_age)
    EditText registerAge;
    @Bind(R.id.register_year)
    Spinner registerYear;
    @Bind(R.id.register_register)
    Button registerButton;

    public RegistrationView(Context context, View view) {
        this.context = context;
        this.view = view;

        ButterKnife.bind(this, view);

        registerEmail.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });

        registerPassword.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });

        registerFirstName.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });

        registerLastName.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });

        registerAge.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });
    }

    @OnClick(R.id.register_register)
    public void registerStudent() {
        registerAge.setError(null);

        Student newStudent = new Student();
        newStudent.setEmail(registerEmail.getText().toString());
        newStudent.setPassword(registerPassword.getText().toString());
        newStudent.setFirstName(registerFirstName.getText().toString());
        newStudent.setLastName(registerLastName.getText().toString());

        try {
            newStudent.setAge(Integer.parseInt(registerAge.getText().toString()));
        } catch (NumberFormatException ex) {
            registerAge.setError(context.getString(R.string.register_header));
            return;
        }

        registrationController.registerNewStudent(newStudent);
    }

    private void enableRegisterButton() {
        if(!TextUtils.isEmpty(registerEmail.getText()) && !TextUtils.isEmpty(registerPassword.getText())
                && !TextUtils.isEmpty(registerFirstName.getText()) && !TextUtils.isEmpty(registerLastName.getText())
                && !TextUtils.isEmpty(registerAge.getText())) {
            registerButton.setEnabled(true);
            registerButton.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        } else {
            registerButton.setEnabled(false);
            registerButton.setBackgroundColor(context.getResources().getColor(R.color.theme_supplemental_gray));
        }
    }

    public void setRegistrationController(RegistrationController registrationController) {
        this.registrationController = registrationController;
    }
}
