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
 * Created by Wongerfeng on 2018/10/19.
 */

public class RectPathView extends View {
    private static final String TAG = "RectPathView";
    private Path mPath;
    private PathMeasure mPathMeasure;
    private Paint mPaint;

    private Path dst;

    public RectPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        dst = new Path();

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);

        mPath.addRect(-50,-50, 50, 50, Path.Direction.CW);
        /*canvas.drawPath(mPath, mPaint);
        mPath.addRect(-100, -100, 100, 100, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);
        mPath.addRect(-120, -120, 120, 120, Path.Direction.CCW);
        canvas.drawPath(mPath, mPaint);*/

        dst.lineTo(10, 100);

        mPathMeasure = new PathMeasure(mPath, false);
        mPathMeasure.getSegment(0, 150, dst, false);

        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(450,150);

        canvas.drawPath(dst, mPaint);

        do {
            float length = mPathMeasure.getLength();
            Log.i(TAG, "onDraw: length = "+length);

        }while (mPathMeasure.nextContour());

    }
}
