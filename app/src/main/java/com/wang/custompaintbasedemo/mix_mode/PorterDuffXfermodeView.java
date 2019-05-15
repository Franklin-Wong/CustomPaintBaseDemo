package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/24.
 */

public class PorterDuffXfermodeView extends View {
    private static final String TAG = "PorterDuffXfermodeView";

    private int width = 200;
    private int height = 200;
    private Bitmap dstBmp;
    private Bitmap scrBmp;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        ///绘制圆形目标图像
        dstBmp = makeDst(width, height);
        ///绘制矩形源图像
        scrBmp = makeSrc(width, height);
        //设置PorterDuff模式为Mode.SRC_IN
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    /**
     * 绘制圆形目标图像
     * @param width
     * @param height
     * @return
     */
    private Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xffffcc44);
        //绘制圆形目标图像
        canvas.drawOval(new RectF(0,0, width,height),paint);

        return bitmap;
    }

    /**
     * 绘制矩形源图像
     * @param width
     * @param height
     * @return
     */
    private Bitmap makeSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xff66aaff);
        //绘制源图像
        c.drawRect(0,0,width,height, p);

        return bitmap;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.LTGRAY);
        //绘制目标图像
        canvas.drawBitmap(dstBmp, 0,0, mPaint);
        //设置PorterDuff模式为Mode.SRC_IN
        mPaint.setXfermode(mPorterDuffXfermode);
        //已目标圆形的中心点绘制矩形
        canvas.drawBitmap(scrBmp, width/2, height/2, mPaint);
        //最后清空mPorterDuffXfermode
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);

    }





}
