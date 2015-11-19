package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.model.Course;
import com.yodle.testingdemocomplete.view.GpaCalculatorView;

import java.util.List;

public class GpaCalculatorController {
    private GpaCalculatorView gpaCalculatorView;
    private GpaCalculatorActivityNavigator gpaCalculatorActivityNavigator;

    public GpaCalculatorController(GpaCalculatorView gpaCalculatorView, GpaCalculatorActivityNavigator gpaCalculatorActivityNavigator) {
        this.gpaCalculatorView = gpaCalculatorView;
        this.gpaCalculatorActivityNavigator = gpaCalculatorActivityNavigator;
    }

    public double calculate(List<Course> courseList) {
        double gpa = 0;
        int numCourseHours = 0;
        for(Course course : courseList) {
            if(course.getNumCredits() != 0) {
                gpa += course.getGrade().getGpaPoints() * course.getNumCredits();
                numCourseHours += course.getNumCredits();
            }
        }

        gpa = numCourseHours == 0 ? 0 : gpa/numCourseHours;
        determineGpaMessage(gpa);
        return gpa;
    }

    public void determineGpaMessage(double gpa) {
        if(gpa >= 3.67) {
            gpaCalculatorView.showAmazingGpaToast();
        } else if(gpa >= 3.33) {
            gpaCalculatorView.showGreatGpaToast();
        } else if(gpa >= 3.00) {
            gpaCalculatorView.showKeepItUpGpaToast();
        } else {
            gpaCalculatorView.showStudyHarderGpaToast();
        }
    }

    public interface GpaCalculatorActivityNavigator {

    }
}
