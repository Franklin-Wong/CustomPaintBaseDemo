package com.wang.custompaintbasedemo.mix_mode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/29.
 */

public class InregularWavwView extends View {
    private static final String TAG = "InregularWavwView";
    private Paint mPaint;
    private Bitmap dstBmp, srcBmp, revertBmp;
    private PorterDuffXfermode mXfermode;
    private int mItemWaveLength = 0;
    private int dx;

    public InregularWavwView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        //创建波纹的背景图像
        dstBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);
        //创建合成的圆形图像
        srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);

        mItemWaveLength = dstBmp.getWidth();

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        startAnim();

    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画圆形图像
        canvas.drawBitmap(srcBmp,0,0, mPaint);
        //再开始画结果
        int id = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBmp,new Rect(dx, 0, dx + srcBmp.getWidth(), srcBmp.getHeight()),
                new Rect(0,0, srcBmp.getWidth(), srcBmp.getHeight()), mPaint);

        mPaint.setXfermode(mXfermode);

        canvas.drawBitmap(srcBmp, 0,0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(id);

    }
}
