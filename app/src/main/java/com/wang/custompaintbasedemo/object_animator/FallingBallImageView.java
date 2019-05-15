package com.wang.custompaintbasedemo.object_animator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

/**
 * TODO: document your custom view class.
 */
public class FallingBallImageView extends ImageView {
    private String mExampleString;
    private int mExampleColor = Color.RED;
    private float mExampleDimension = 0;
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public FallingBallImageView(Context context) {
        super(context);
    }

    public FallingBallImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *
     * @param pos
     */
    public void setFallingPos(Point pos){

        layout(pos.x, pos.y, pos.x + getWidth(), pos.y + getHeight());
    }


}
