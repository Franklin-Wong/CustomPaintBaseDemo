package com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder;

import android.animation.TypeEvaluator;

/**
 * Created by Wongerfeng on 2018/10/16.
 */

public class CharEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int)startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
