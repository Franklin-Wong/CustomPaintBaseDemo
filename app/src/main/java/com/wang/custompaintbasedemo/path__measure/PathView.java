package com.wang.custompaintbasedemo.path__measure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/18.
 */

public class PathView extends View {

    private static final String TAG = "PathView";
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mMeasure1,mMeasure2;

//    public PathView(Context context) {
//        super(context);
//    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPath = new Path();
        mMeasure1 = new PathMeasure(mPath, false);
        mMeasure2 = new PathMeasure(mPath, true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(50, 50);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        mPath.moveTo(0,0);
        mPath.lineTo(0,100);
        mPath.lineTo(100, 100);
        mPath.lineTo(100,0);


        Log.i(TAG, "onDraw:forceClose = false, mMeasure1 = "+mMeasure1.getLength());
        Log.i(TAG, "onDraw:forceClose = true, mMeasure2 = "+mMeasure2.getLength());

        canvas.drawPath(mPath, mPaint);
    }
}
