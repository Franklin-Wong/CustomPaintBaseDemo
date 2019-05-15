package com.wang.custompaintbasedemo.evaluators;

import android.animation.TypeEvaluator;

/**
 * Created by Wongerfeng on 2018/10/12.
 * A ~ Z 字符渐变
 */

public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        /*
        利用字符和数字之间的转换
         */
        int startInt = startValue;
        int endInt = endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        char result = (char) curInt;
        return result;
    }
}
