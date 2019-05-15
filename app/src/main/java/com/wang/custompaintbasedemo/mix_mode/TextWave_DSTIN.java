package com.wang.custompaintbasedemo.mix_mode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/26.
 */

public class TextWave_DSTIN extends View {
    private static final String TAG = "TextWave_DSTIN";
    private Paint mPaint;
    private Path mPath;
    private int mItemWaveLength = 1000;
    private int dx;
    private Bitmap srcBmp, dstBmp;


    private PorterDuffXfermode mPorterDuffXfermode;
    private Canvas c;

    public TextWave_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);
        dstBmp = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        ///
        c = new Canvas(dstBmp);

        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        //设置动画
        startAnim();


    }

    private void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mItemWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
            }
        });
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将波纹绘制到空白的图像上
        generateWavePath();

        c.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);

        c.drawPath(mPath, mPaint);

        //先绘制文字，再绘制合成效果
        canvas.drawBitmap(srcBmp, 0, 0, mPaint);

        int id = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBmp, 0,0, mPaint);

        mPaint.setXfermode(mPorterDuffXfermode);

        canvas.drawBitmap(srcBmp, 0,0,mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(id);

    }

    private void generateWavePath() {
        mPath.reset();

        int originY = srcBmp.getHeight() / 2;
        int halfWaveLen = mItemWaveLength / 2;

        mPath.moveTo(- mItemWaveLength + dx, originY);

        for (int i = -mItemWaveLength; i < getWidth() + mItemWaveLength; i+=mItemWaveLength){
            mPath.rQuadTo(halfWaveLen / 2, -50, halfWaveLen, 0);
            mPath.rQuadTo(halfWaveLen / 2, 50, halfWaveLen, 0);
        }
        mPath.lineTo(srcBmp.getWidth(), srcBmp.getHeight());
        mPath.lineTo(0, srcBmp.getHeight());
        mPath.close();

    }
}
