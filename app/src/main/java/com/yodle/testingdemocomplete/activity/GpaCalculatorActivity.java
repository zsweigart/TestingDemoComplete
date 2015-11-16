package com.yodle.testingdemocomplete.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.yodle.testingdemocomplete.MainApp;
import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.controller.GpaCalculatorController;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.view.GpaCalculatorView;

public class GpaCalculatorActivity extends AppCompatActivity implements GpaCalculatorController.GpaCalculatorActivityNavigator {
    private static final String STUDENT_EXTRA = "STUDENT";
    private GpaCalculatorView gpaCalculatorView;
    private GpaCalculatorController gpaCalculatorController;
    private MainApp mainApp;

    public static Intent getGpaIntentWithStudent(Context context, Student student) {
        Intent intent = new Intent(context, GpaCalculatorActivity.class);
        intent.putExtra(STUDENT_EXTRA, student);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gpaCalculatorView = new GpaCalculatorView(this);
        setContentView(gpaCalculatorView);

        mainApp = (MainApp) getApplication();
        gpaCalculatorController = new GpaCalculatorController(gpaCalculatorView, this);
        gpaCalculatorView.setGpaCalculatorController(gpaCalculatorController);
    }
}
