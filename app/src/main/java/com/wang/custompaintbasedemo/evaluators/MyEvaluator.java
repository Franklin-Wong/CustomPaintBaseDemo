package com.wang.custompaintbasedemo.evaluators;

import android.animation.TypeEvaluator;

/**
 * Created by Wongerfeng on 2018/10/11.
 */

public class MyEvaluator implements TypeEvaluator<Integer> {

    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {

        int startInt = startValue;
        return (int)(200 + startInt + fraction * (endValue - startInt));
    }
}
