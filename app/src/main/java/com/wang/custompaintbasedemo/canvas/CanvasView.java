package com.wang.custompaintbasedemo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/8.
 * 画布与图层
 */

public class CanvasView extends View {

    private Paint mBulePaint;
    private Paint mGreenPaint;
    private RectF mRectF;


    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRectF = new RectF(0,0,400,200);
        mBulePaint = generatePaint(Color.BLUE, Paint.Style.FILL_AND_STROKE, 6);
        mGreenPaint = generatePaint(Color.GREEN, Paint.Style.STROKE, 6);
    }

    private Paint generatePaint(int color, Paint.Style style, int width) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(width);
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mBulePaint);
        //平移画布
        canvas.translate(100,100);
        canvas.drawRect(mRectF, mGreenPaint);

        canvas.save();
        /*剪裁画布之前，禁用硬件加速*/
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        //剪裁画布
        canvas.clipRect(mRectF);
        //画布背景涂色
        canvas.drawColor(Color.LTGRAY);

//        canvas.restore();
//        canvas.drawColor(Color.GRAY);

    }
}
