package com.wang.custompaintbasedemo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/9/28.
 */

public class PaintBaseView extends View {

    private Paint mPaint;
    private RectF mRectF;
    private Path mPath;
    private Rect mRect;

    private Path ovalPath;
    private Region mRegion;

    public PaintBaseView(Context context) {
        super(context);
    }

    public PaintBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRectF = new RectF(500, 500, 800, 600);
        mPath = new Path();
        mRect = new Rect(100, 700, 400, 800);

        mRegion = new Region(mRect);
        ovalPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布背景绘制
//        canvas.drawColor(0xffff00ff);
//        canvas.drawARGB(0xff,0xff,0,0xff);
        canvas.drawRGB(255,0,255);

        /*设置画笔属性*/
        mPaint.setColor(Color.RED);
        //设置描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(50);
        //在画布上绘制
        canvas.drawCircle(190,200,150, mPaint);

        //修改属性
        mPaint.setColor(0x7effff00);
        canvas.drawCircle(190,200,100, mPaint);

        mPaint.setColor(0xff0f0000);
        canvas.drawCircle(190,200,50, mPaint);

        /* 绘制直线*/
        mPaint.setStrokeWidth(1);
        canvas.drawLine(100, 100, 300, 300, mPaint);

        /*绘制点*/
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(2);
        canvas.drawPoint(200, 400, mPaint);

        /*绘制矩形*/
        //直接构造矩形
        canvas.drawRect(100, 500, 400, 600,mPaint);
        //使用RECF构造
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mRectF, mPaint);

        /*绘制路径*/
        mPaint.setStrokeWidth(2);
        mPath.moveTo(500,100);
        mPath.lineTo(500, 200);
        mPath.lineTo(800, 200);
        mPath.close();

        canvas.drawPath(mPath, mPaint);

        /*绘制弧线路径*/
        mPaint.setStrokeWidth(10);
        mPath.moveTo(500, 250);
        mPath.arcTo(mRectF, 0, 90,false);
        //不与起始点连接
//        mPath.addArc(mRectF, 0,90);
        canvas.drawPath(mPath, mPaint);

    }


}
