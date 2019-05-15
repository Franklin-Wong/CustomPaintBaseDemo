package com.wang.custompaintbasedemo.evaluators;

import android.animation.TypeEvaluator;

/**
 * Created by Wongerfeng on 2018/10/12.
 * 倒序移动
 */

public class ReverseEvaluator implements TypeEvaluator<Integer> {


    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(endValue - fraction * (endValue - startInt));
    }
}
