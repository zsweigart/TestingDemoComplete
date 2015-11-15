package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.model.Course;
import com.yodle.testingdemocomplete.model.Student;
import com.yodle.testingdemocomplete.persistence.Datastore;
import com.yodle.testingdemocomplete.view.GpaCalculatorView;
import com.yodle.testingdemocomplete.view.RegistrationView;

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

        return gpa/numCourseHours;
    }

    public interface GpaCalculatorActivityNavigator {

    }
}
