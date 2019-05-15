package com.wang.custompaintbasedemo.bitmap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by Wongerfeng on 2018/10/30.
 */

public class PathShape extends Shape {
    private static final String TAG = "PathShape";
    private Path mPath;
    private float mStdWidth;
    private float mStdHeight;

    private float mScaleX;
    private float mScaleY;

    private PathShape(Path path, float stdWidth, float stdHeight) {
        mPath = path;
        mStdWidth = stdWidth;
        mStdHeight = stdHeight;
    }

    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);
        //获取比例
        mScaleX = width / mStdWidth;
        mScaleY = height / mStdHeight;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        canvas.save();
        canvas.scale(mScaleX, mScaleY);
        canvas.drawPath(mPath, paint);
        canvas.restore();
    }
}
