package com.wang.custompaintbasedemo.object_animator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Wongerfeng on 2018/10/15.
 */

public class CustomScaleTextView extends TextView {

    public CustomScaleTextView(Context context) {
        super(context);
    }

    public CustomScaleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置字体X方向大小
     * @param num
     */
    public void setScaleSize(float num){
        setScaleX(num);
    }

    ///避免出现 ：PropertyValuesHolder: Method getScaleSize() with type null not found on target class
    /// class com.wang.custompaintbasedemo.object_animator.CustomTextView

    /**
     *
     * @return
     */
    public float getScaleSize(){
        return 1f;
    }
}
