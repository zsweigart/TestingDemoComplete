package com.yodle.testingdemocomplete.customviews;


import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.customviews.AfterTextChangedWatcher;
import com.yodle.testingdemocomplete.model.Course;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.course_name)
    EditText courseName;
    @Bind(R.id.course_hours)
    EditText courseHours;
    @Bind(R.id.course_grade)
    Spinner courseGrade;

    private Course course;

    public CourseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        courseName.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                course.setCourseName(s.toString());
            }
        });

        courseHours.addTextChangedListener(new AfterTextChangedWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 0) {
                    try {
                        course.setNumCredits(Integer.parseInt(s.toString()));
                    } catch (NumberFormatException ex) {
                        courseHours.setText(0);
                    }
                }
            }
        });

        courseGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course.setGrade(Course.GRADE.getGradeByPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setContent(Course course) {
        this.course = course;

        if(course.getCourseName() != null) {
            courseName.setText(course.getCourseName());
        }

        courseHours.setText(String.valueOf(course.getNumCredits()));

        if(course.getGrade() != null) {
            courseGrade.setSelection(course.getGrade().ordinal());
        }
    }
}
