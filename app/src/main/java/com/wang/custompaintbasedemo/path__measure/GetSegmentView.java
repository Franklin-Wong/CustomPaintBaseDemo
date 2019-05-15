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
 * Created by Wongerfeng on 2018/10/19.
 */

public class GetSegmentView extends View {
    private static final String TAG = "GetSegmentView";
    private Path mCirclePath;
    private PathMeasure mPathMeasure;
    private Paint mPaint;

    private Path dst;
    private float mCurValue;

    public GetSegmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);


        mCirclePath = new Path();
        dst = new Path();

        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath, true);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurValue = (float) animation.getAnimatedValue();
                invalidate();
                Log.i(TAG, "onAnimationUpdate: value = "+ mCurValue);
            }
        });
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mPathMeasure.getLength();
        float stop = length * mCurValue;

        float start = 0;
        /*改变起点位置*/
        start = (float) (stop - ((0.5 - Math.abs(mCurValue - 0.5)) * length));
        /*或者*/
//        if (start >= 0.5){
//            start = 2 * mCurValue -1;
//        }

        dst.reset();
        mPathMeasure.getSegment(start, stop, dst, true);

        canvas.drawPath(dst, mPaint);

    }
}
