package com.wang.custompaintbasedemo.path__measure;

import android.animation.ValueAnimator;
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
 * Created by Wongerfeng on 2018/10/22.
 */

public class AlipayView extends View {
    private static final String TAG = "AlipayView";
    private Path mCirclePath, mDstPath;
    private PathMeasure mPathMeasure;
    private Paint mPaint;
    private float mCurValue;

    private float mCentX = 100;
    private float mCentY = 100;
    private float mRadius = 50;


    public AlipayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        mCirclePath = new Path();
        mDstPath = new Path();

        mCirclePath.addCircle(mCentX,mCentY, mRadius, Path.Direction.CW );
        ///移动至对勾的起点
        mCirclePath.moveTo(mCentX - mRadius/2, mCentY);
        ///移动至对勾的转折点
        mCirclePath.lineTo(mCentX, mCentY + mRadius/2);
        ///移动至对勾的终点
        mCirclePath.lineTo(mCentX + mRadius/2, mCentY - mRadius/3);

        mPathMeasure = new PathMeasure(mCirclePath, false);
        ///因为路线有两条，所以区间有 0~1, 1~2 两个
        ValueAnimator animator = ValueAnimator.ofFloat(0, 2);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurValue = (float) animation.getAnimatedValue();
                ///不断地重绘动画
                invalidate();
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: mCurValue = " + mCurValue);

        if (mCurValue < 1){
            float stop = mPathMeasure.getLength() * mCurValue;
            mPathMeasure.getSegment(0, stop, mDstPath, true);

        }else if (mCurValue == 1){
            ///将动画路径从圆转移到对勾上
            mPathMeasure.getSegment(0, mPathMeasure.getLength(), mDstPath, true);
            mPathMeasure.nextContour();

        }else {
            ///在1~2区间，开始画对勾
            float stop = mPathMeasure.getLength() * (mCurValue - 1);
            mPathMeasure.getSegment(0, stop, mDstPath, true);
        }

        canvas.drawPath(mDstPath, mPaint);
    }
}
