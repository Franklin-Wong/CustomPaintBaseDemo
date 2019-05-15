package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/11/22.
 */

public class RectView extends View{
    private static final String TAG = "RectView";
    private Paint mPaint;

    private Rect mBigRect, mMidRect, mSmallRect;

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setTextSize(40);
        mBigRect = new Rect(10, 10, 600, 600);
        mMidRect = new Rect(30, 30, 570, 570);
        mSmallRect = new Rect(60, 60, 540, 540);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画大方格
        mPaint.setColor(Color.RED);
        canvas.drawRect(mBigRect, mPaint);
        //画中方格
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mMidRect, mPaint);
        //画小方格
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mSmallRect, mPaint);
        //画圆形
        mPaint.setColor(Color.argb(0x3f, 0xff , 0xff, 0xff));
        canvas.drawCircle(300, 300, 100, mPaint);
        //写数字
        mPaint.setColor(Color.GREEN);
        canvas.drawText("6", 300, 300, mPaint);

    }
}
