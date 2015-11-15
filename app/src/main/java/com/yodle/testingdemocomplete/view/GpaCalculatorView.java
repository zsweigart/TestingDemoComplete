package com.yodle.testingdemocomplete.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.activity.GpaCalculatorActivity;
import com.yodle.testingdemocomplete.adapter.GpaRecyclerAdapter;
import com.yodle.testingdemocomplete.controller.GpaCalculatorController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpaCalculatorView {
    private GpaCalculatorActivity activity;
    private View view;
    private GpaCalculatorController gpaCalculatorController;
    private GpaRecyclerAdapter gpaRecyclerAdapter;

    @Bind(R.id.gpa_header)
    TextView gpaHeader;
    @Bind(R.id.gpa_list)
    RecyclerView gpaCourseList;
    @Bind(R.id.gpa_value)
    TextView gpaValue;
    @Bind(R.id.gpa_calculate)
    Button calculate;

    public GpaCalculatorView(GpaCalculatorActivity activity, View view) {
        this.activity = activity;
        this.view = view;

        ButterKnife.bind(this, view);
        gpaRecyclerAdapter = new GpaRecyclerAdapter(activity);
        gpaCourseList.setAdapter(gpaRecyclerAdapter);
        gpaCourseList.setLayoutManager(new LinearLayoutManager(activity));
    }

    public void setGpaCalculatorController(GpaCalculatorController gpaCalculatorController) {
        this.gpaCalculatorController = gpaCalculatorController;
    }

    @OnClick(R.id.gpa_calculate)
    public void calculate() {
        double gpa = gpaCalculatorController.calculate(gpaRecyclerAdapter.getCourses());
        gpaValue.setText(activity.getString(R.string.gpa_value_string, gpa));
    }
}
