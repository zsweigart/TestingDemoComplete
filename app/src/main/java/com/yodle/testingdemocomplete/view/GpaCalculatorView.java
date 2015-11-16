package com.yodle.testingdemocomplete.view;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.adapter.GpaRecyclerAdapter;
import com.yodle.testingdemocomplete.controller.GpaCalculatorController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpaCalculatorView {
    private Context context;
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

    public GpaCalculatorView(Context context, View view) {
        this.context = context;
        this.view = view;

        ButterKnife.bind(this, view);
        gpaRecyclerAdapter = new GpaRecyclerAdapter(context);
        gpaCourseList.setAdapter(gpaRecyclerAdapter);
        gpaCourseList.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setGpaCalculatorController(GpaCalculatorController gpaCalculatorController) {
        this.gpaCalculatorController = gpaCalculatorController;
    }

    @OnClick(R.id.gpa_calculate)
    public void calculate() {
        double gpa = gpaCalculatorController.calculate(gpaRecyclerAdapter.getCourses());
        gpaValue.setText(context.getString(R.string.gpa_value_string, gpa));
    }

    @VisibleForTesting
    GpaRecyclerAdapter getGpaRecyclerAdapter() {
        return gpaRecyclerAdapter;
    }
}
