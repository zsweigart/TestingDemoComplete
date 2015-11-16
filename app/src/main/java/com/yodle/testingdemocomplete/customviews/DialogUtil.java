package com.yodle.testingdemocomplete.customviews;

import android.app.Activity;
import android.app.AlertDialog;

import com.yodle.testingdemocomplete.R;

public class DialogUtil {

    public void alertError(Activity activity, String message) {
        if(!activity.isFinishing()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            dialog.setTitle(R.string.oops_something_went_wrong);
            dialog.setMessage(message);
            dialog.setPositiveButton(R.string.option_ok, null);
            dialog.show();
        }
    }
}
