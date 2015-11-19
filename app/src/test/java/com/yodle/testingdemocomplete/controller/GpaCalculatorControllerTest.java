package com.yodle.testingdemocomplete.controller;

import com.yodle.testingdemocomplete.model.Course;
import com.yodle.testingdemocomplete.view.GpaCalculatorView;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

public class GpaCalculatorControllerTest {

    private GpaCalculatorController gpaCalculatorController;
    private GpaCalculatorView gpaCalculatorView;
    private GpaCalculatorController.GpaCalculatorActivityNavigator gpaCalculatorActivityNavigator;
    private double epsilon = .001;

    @Before
    public void setup() {
        gpaCalculatorView = mock(GpaCalculatorView.class);
        gpaCalculatorActivityNavigator = mock(GpaCalculatorController.GpaCalculatorActivityNavigator.class);
        gpaCalculatorController = new GpaCalculatorController(gpaCalculatorView, gpaCalculatorActivityNavigator);
    }

    @Test
    public void calculate_whenGivenEmptyList_returnsZero() {
        List<Course> courseList = new ArrayList<>();

        assertThat(gpaCalculatorController.calculate(courseList), equalTo(0.0));
    }

    @Test
    public void calculate_whenGivenListWithEmptyCourses_returnsZero() {
        List<Course> courseList = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            courseList.add(new Course());
        }

        assertThat(gpaCalculatorController.calculate(courseList), equalTo(0.0));
    }

    @Test
    public void calculate_whenGivenListWithOneCourse_returnsLetterValueGpa() {
        List<Course> courseList = new ArrayList<>();
        Course course = new Course("Course", 3, Course.GRADE.A);
        courseList.add(course);

        assertThat(gpaCalculatorController.calculate(courseList), equalTo(4.0));
    }

    @Test
    public void calculate_whenGiventListWithMultipleCourses_returnsCorrectGPA() {
        List<Course> courseList = new ArrayList<>();
        Course course1 = new Course("First", 3, Course.GRADE.A);
        Course course2 = new Course("Second", 6, Course.GRADE.B);
        Course course3 = new Course("Third", 3, Course.GRADE.C);
        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);

        assertThat(gpaCalculatorController.calculate(courseList), equalTo(3.0));

    }

    @Test
    public void showMessage_whenAmazing_callsAmazingViewMethod() {
        gpaCalculatorController.determineGpaMessage(3.67);
        gpaCalculatorController.determineGpaMessage(3.67 + epsilon);
        gpaCalculatorController.determineGpaMessage(3.85);
        verify(gpaCalculatorView, times(3)).showAmazingGpaToast();
    }

    @Test
    public void showMessage_whenGreat_callsGreatViewMethod() {
        gpaCalculatorController.determineGpaMessage(3.33);
        gpaCalculatorController.determineGpaMessage(3.33 + epsilon);
        gpaCalculatorController.determineGpaMessage(3.55);
        verify(gpaCalculatorView, times(2)).showGreatGpaToast();
    }

    @Test
    public void showMessage_whenKeepItUp_callsKeepItUpViewMethod() {
        gpaCalculatorController.determineGpaMessage(3.00);
        gpaCalculatorController.determineGpaMessage(3.00 + epsilon);
        gpaCalculatorController.determineGpaMessage(3.20);
        verify(gpaCalculatorView, times(2)).showKeepItUpGpaToast();
    }

    @Test
    public void showMessage_whenStudyHarder_callsStudyHarderViewMethod() {
        gpaCalculatorController.determineGpaMessage(2.00);
        verify(gpaCalculatorView, times(2)).showStudyHarderGpaToast();
    }
}
