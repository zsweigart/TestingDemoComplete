package com.yodle.testingdemocomplete.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.yodle.testingdemocomplete.model.Student;

public class GpaCalculatorActivity extends AppCompatActivity {
    private static final String STUDENT_EXTRA = "STUDENT";

    public static Intent getGpaIntentWithStudent(Context context, Student student) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        intent.putExtra(STUDENT_EXTRA, student);
        return intent;
    }

}
