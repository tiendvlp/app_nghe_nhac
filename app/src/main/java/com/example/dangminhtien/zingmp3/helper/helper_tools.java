package com.example.dangminhtien.zingmp3.helper;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by tiend on 7/1/2017.
 */

public class helper_tools {
    private Context context;
    private Activity activity;

    public helper_tools(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public int convert_dp_to_px(int dp) {
        DisplayMetrics displayMetrics= activity.getResources().getDisplayMetrics();
        return (int) (dp*(displayMetrics.densityDpi/160));
    }

}
