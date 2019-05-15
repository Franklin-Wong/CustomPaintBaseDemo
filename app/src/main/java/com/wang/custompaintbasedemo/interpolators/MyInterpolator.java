package com.wang.custompaintbasedemo.interpolators;

import android.animation.TimeInterpolator;
import android.util.Log;

/**
 * Created by Wongerfeng on 2018/10/11.
 * 倒序移动插值器
 */

public class MyInterpolator implements TimeInterpolator {
    private static final String TAG = "MyInterpolator";

    @Override
    public float getInterpolation(float input) {
        Log.i(TAG, "getInterpolation: input:" + (1 - input));
        return 1 - input;
    }
}
