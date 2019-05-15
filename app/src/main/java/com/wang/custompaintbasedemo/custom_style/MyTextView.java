package com.wang.custompaintbasedemo.custom_style;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/11/29.
 */

public class MyTextView extends TextView {
    private static final String TAG = "MyTextView";

    public MyTextView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        //获取属性数组
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        final float headerHeight = typedArray.getDimension(R.styleable.MyTextView_headerHeight, -1);
        int age = typedArray.getInt(R.styleable.MyTextView_age, -1);

        int bgColor = typedArray.getColor(R.styleable.MyTextView_backGround, -1);

        typedArray.recycle();

        //使用继承 的父类setText方法
        setText("headerHeight="+ headerHeight +";age="+ age);
        setBackgroundColor(bgColor);

    }





}
