package com.yodle.testingdemocomplete.view;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yodle.testingdemocomplete.BuildConfig;
import com.yodle.testingdemocomplete.R;
import com.yodle.testingdemocomplete.activity.GpaCalculatorActivity;
import com.yodle.testingdemocomplete.adapter.GpaRecyclerAdapter;
import com.yodle.testingdemocomplete.controller.GpaCalculatorController;
import com.yodle.testingdemocomplete.model.Course;
import com.yodle.testingdemocomplete.testingframework.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(CustomRobolectricRunner.class)
public class GpaCalculatorViewTest {
    private View view;
    private GpaCalculatorController gpaCalculatorController;
    private GpaCalculatorView gpaCalculatorView;
    private GpaRecyclerAdapter gpaRecyclerAdapter;

    @Before
    public void setup() {
        view = LayoutInflater.from(RuntimeEnvironment.application).inflate(R.layout.activity_gpa, null);
        gpaCalculatorController = mock(GpaCalculatorController.class);
        gpaCalculatorView = new GpaCalculatorView(RuntimeEnvironment.application, view);
        gpaCalculatorView.setGpaCalculatorController(gpaCalculatorController);

        gpaRecyclerAdapter = gpaCalculatorView.getGpaRecyclerAdapter();
        gpaRecyclerAdapter.addCourse(new Course("Only Course", 3, Course.GRADE.B_PLUS));

        when(gpaCalculatorController.calculate(Matchers.<List<Course>>any())).thenReturn(3.5);
    }

    @Test
    public void clickingCalculateButton_whenEnabled_callsCalculateAndUpdatesView() {
        view.findViewById(R.id.gpa_calculate).performClick();
        verify(gpaCalculatorController).calculate(gpaRecyclerAdapter.getCourses());

        String actualValue = ((TextView)view.findViewById(R.id.gpa_value)).getText().toString();
        assertThat(actualValue, equalTo("Your GPA is: 3.500"));
    }
}
