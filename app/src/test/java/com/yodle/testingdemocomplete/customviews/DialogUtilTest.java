package com.yodle.testingdemocomplete.customviews;


import android.app.Activity;

import com.yodle.testingdemocomplete.testingframework.CustomRobolectricRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(CustomRobolectricRunner.class)
public class DialogUtilTest {
    private DialogUtil dialogUtil;
    private Activity activity;

    @Before
    public void setup() {
        dialogUtil = new DialogUtil();
        activity = setupActivity();
    }

    @Test
    public void alertError_displaysAlertDialogWithProperMessage() {
        String errorMessage = "Test error message";
        dialogUtil.alertError(activity, errorMessage);

        assertThat(shadowOf(ShadowAlertDialog.getLatestAlertDialog()).getMessage().toString(), equalTo(errorMessage));
    }

    /*
     * Helper Methods
     */
    private Activity setupActivity() {
        return Robolectric.buildActivity(Activity.class).create().start().resume().visible().get();
    }
}
