package com.wang.custompaintbasedemo.painting_base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/23.
 */

public class CustomTextView extends View{
    private static final String TAG = "CustomTextView";
    private Paint mPaint;
    private Rect mMinRect;
    private String mString;


    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setTextSize(120);
        mMinRect = new Rect();
        mString = "CustomTextViewjgpq";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int baseLineX = 500;
        int baseLineY = 200;

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setFakeBoldText(false);

        canvas.drawLine(baseLineX, 0, baseLineX, 600, mPaint);
        ///画一条横线，只在X方向延伸
        canvas.drawLine(0, baseLineY, 1000, baseLineY,mPaint );
        mPaint.setTextAlign(Paint.Align.LEFT);
        ///在 横线的相同起点绘制文字
        canvas.drawText("my text ", baseLineX, baseLineY, mPaint);

        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("my text ", baseLineX, 400, mPaint);

        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("my text", baseLineX, 500, mPaint);


        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        ///计算各线Y坐标
        float ascent = baseLineY + fontMetrics.ascent;
        float descent = baseLineY + fontMetrics.descent;
        float top = baseLineY + fontMetrics.top;
        float bottom = baseLineY + fontMetrics.bottom;

        canvas.drawLine(0, top, 1000, top, mPaint);
        canvas.drawLine(0, ascent, 1000, ascent, mPaint);
        canvas.drawLine(0, descent, 1000, descent, mPaint);
        canvas.drawLine(0, bottom, 1000, bottom, mPaint);

        ///获取字符宽度
        mPaint.measureText("my text");

        mPaint.getTextBounds("my text", 0, "my text".length(), mMinRect);
        Log.i(TAG, "onDraw: rect = " + mMinRect.toShortString());
        /* rect =  [8,-80][390,26] */


        /**根据定点写字*/
        mPaint.setColor(Color.RED);
        //设置绘制的开始点
        mPaint.setTextAlign(Paint.Align.LEFT);
        //粗体字
        mPaint.setFakeBoldText(true);
        //删除效果
        mPaint.setStrikeThruText(true);
        //设置下划线
        mPaint.setUnderlineText(true);
        //设置水平拉伸
        mPaint.setTextScaleX(0.6f);
        //水平倾向度
        mPaint.setTextSkewX(-0.3f);
        //设置字体样式
        mPaint.setTypeface(Typeface.SANS_SERIF);

//        mPaint.setShader(new BitmapShader());

        int topPoint = 800;
        int baseX = 0;
        ///画top线
        canvas.drawLine(baseX, topPoint, 2000, topPoint, mPaint);
        ///计算baseLine
        Paint.FontMetricsInt fontMetrics1 = mPaint.getFontMetricsInt();
        int baseY = topPoint - fontMetrics1.top;
        ///绘制baseLine
        canvas.drawLine(baseX, baseY, 2000, baseY, mPaint);

        canvas.drawText(mString, baseX, baseY, mPaint);

        ///重置画笔
//        mPaint.reset();
        mPaint.setStrokeMiter(90);
        mPaint.setAntiAlias(true);
///        mPaint.setPathEffect(new DashPathEffect());
        mPaint.setStrokeWidth(38);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 1000, 500, 1000, mPaint);
    }


}
