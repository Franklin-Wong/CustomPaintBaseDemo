package com.wang.custompaintbasedemo.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Wongerfeng on 2018/11/1.
 * 自定义Drawable，全实现其中的抽象方法
 * 将传入的bitmap对象。 转换为圆角
 */

public class CustomDrawable extends Drawable {

    private static final String TAG = "CustomDrawable";

    private Paint mPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private RectF mRectF;

    public CustomDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
    }

    @Override
    public String toString() {

        Log.i(TAG, "toString: ");
        return super.toString();
    }

    @Override
    public void setAlpha(int alpha) {
        Log.i(TAG, "setAlpha: ");
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        Log.i(TAG, "setColorFilter: ");
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        Log.i(TAG, "setBounds: right=" + right +"---left=" + left +"---top="+top+"---bottom="+bottom);
        //根据CustomDrawable的边界，设定边界，创建一个与当前CustomDrawable相同大小的bitmap
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap, right - left, bottom - top, true);
        //将调整边界后的bitmap作为shader
        mBitmapShader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
        mRectF = new RectF(left,top,right,bottom);


    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Log.i(TAG, "draw: ");
        //在mRectF指定的区域显示BitmapShader，带圆角
        canvas.drawRoundRect(mRectF, 20, 20, mPaint);
        //绘制圆形图片
//        canvas.drawOval(mRectF, mPaint);

    }

//    @Override
//    public int getIntrinsicHeight() {
//        Log.i(TAG, "getIntrinsicHeight: ");
//        return super.getIntrinsicHeight();
//    }
//
//    @Override
//    public int getIntrinsicWidth() {
//        Log.i(TAG, "getIntrinsicWidth: ");
//        return super.getIntrinsicWidth();
//    }
}
