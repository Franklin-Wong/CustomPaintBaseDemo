package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/25.
 */

public class TwitterView extends View {

    private static final String TAG = "TwitterView";
    private Paint mPaint;
    private Bitmap dstBmp, srcBmp;
    private PorterDuffXfermode mDuffXfermode;
    public TwitterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        dstBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);
        srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);
        mDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int id = canvas.saveLayer(0,0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBmp, 0,0, mPaint);
        mPaint.setXfermode(mDuffXfermode);
        canvas.drawBitmap(srcBmp, 0,0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(id);

    }
}
