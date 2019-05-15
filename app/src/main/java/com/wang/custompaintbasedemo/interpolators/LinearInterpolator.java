package com.wang.custompaintbasedemo.interpolators;

import android.view.animation.Interpolator;

/**
 * Created by Wongerfeng on 2018/10/11.
 */

public class LinearInterpolator implements Interpolator {


    @Override
    public float getInterpolation(float input) {
        return input;
    }
}
