package com.yodle.testingdemocomplete.view;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.adapter.GpaRecyclerAdapter;
import com.yodle.testingdemocomplete.controller.GpaCalculatorController;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GpaCalculatorView extends LinearLayout {
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

    public GpaCalculatorView(Context context) {
        super(context);
        this.context = context;
        inflate(context, R.layout.activity_gpa, this);

        ButterKnife.bind(this);
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

    public void showAmazingGpaToast() {
        Toast.makeText(context, context.getString(R.string.gpa_thats_amazing), Toast.LENGTH_SHORT).show();
    }

    public void showGreatGpaToast() {
        Toast.makeText(context, context.getString(R.string.gpa_great_job), Toast.LENGTH_SHORT).show();
    }

    public void showKeepItUpGpaToast() {
        Toast.makeText(context, context.getString(R.string.gpa_keep_it_up), Toast.LENGTH_SHORT).show();
    }

    public void showStudyHarderGpaToast() {
        Toast.makeText(context, context.getString(R.string.gpa_study_harder), Toast.LENGTH_SHORT).show();
    }

    @VisibleForTesting
    GpaRecyclerAdapter getGpaRecyclerAdapter() {
        return gpaRecyclerAdapter;
    }
}
