package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/25.
 * 镜像倒影 合成图片控件
 */

public class InvertedImageView_SRCIN extends View {
    private static final String TAG = "InvertedImageView_SRCIN";
    private Paint mPaint;
    private Bitmap dstBmp, srcBmp, revertBmp;
    private PorterDuffXfermode mXfermode;
    private Matrix mMatrix;

    private RectF mRectF;

    public InvertedImageView_SRCIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        //创建镜像图像的阴影效果 的bitmap
        dstBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //创建正向的源图像的bitmap
        srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        ///Matrix 设置图像模式为镜像
        mMatrix = new Matrix();
        mMatrix.setScale(1f, -1f);
        ///创建与源图像尺寸一致的翻转倒置图片的bitmap
        revertBmp = Bitmap.createBitmap(srcBmp, 0,0, srcBmp.getWidth(), srcBmp.getHeight(), mMatrix, true);
        Log.i(TAG, "InvertedImageView_SRCIN: getWidth() = " + getWidth() +"-----getHeight() = "+ getHeight());
//        mRectF = new RectF(0,0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取屏幕宽度 取 1/2
        int width = getWidth()/2;
        ///
        int height = width * dstBmp.getHeight() / dstBmp.getWidth();


        //绘制源图像图片
        canvas.drawBitmap(srcBmp, null, new RectF(0,0, width, height), mPaint);
        //画布下移，
        int id = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0, height);
        //绘制镜像图像的阴影效果
        canvas.drawBitmap(dstBmp, null, new RectF(0,0,width, height), mPaint);
        mPaint.setXfermode(mXfermode);
        //绘制倒影的镜像图像
        canvas.drawBitmap(revertBmp, null, new RectF(0,0, width, height), mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(id);

        Log.i(TAG, "onDraw: getWidth() = " + getWidth() +"-----getHeight() = "+ getHeight());

        Log.i(TAG, "onDraw: dstBmp.getHeight() ="+ dstBmp.getHeight() +"----dstBmp.getWidth() ="+ dstBmp.getWidth());

        Log.i(TAG, "onDraw: width = " + width +"---height = "+ height);

    }
}
