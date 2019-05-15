package com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Wongerfeng on 2018/10/16.
 */

public class CustomCharacterTextView extends TextView {
    private static final String TAG = "CustomCharacterTextView";


    public CustomCharacterTextView(Context context) {
        super(context);
    }

    public CustomCharacterTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character character){
        setText(String.valueOf(character));
        Log.i(TAG, "setCharText: character = "+ character);
    }
}
