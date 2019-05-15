package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/11/9.
 */

public class LinearGradientView extends View {
    private static final String TAG = "LinearGradientView";
    private Bitmap mDestBmp;
    private Paint mPaint;


    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();

        int width = 500;
        int height = 300;

        //创建一个空白的bitmap位图对象
        mDestBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //或者这个bitmap里面的内容
        Canvas canvas = new Canvas(mDestBmp);
        Paint p = new Paint();
        LinearGradient linearGradient = new LinearGradient(width / 2, 0, width / 2, height,
                0xffffffff, 0x00ffffff, Shader.TileMode.CLAMP);

        p.setShader(linearGradient);
        canvas.drawRect(0,0,width,height,p);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画出bitmap对象区域
        canvas.drawBitmap(mDestBmp, 0, 0, mPaint);

        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        //在区域周围，画一个框
        canvas.drawRect(0,0, mDestBmp.getWidth(), mDestBmp.getHeight(), mPaint);

    }
}
