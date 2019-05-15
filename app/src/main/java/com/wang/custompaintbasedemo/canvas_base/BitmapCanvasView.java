package com.wang.custompaintbasedemo.canvas_base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/29.
 */

public class BitmapCanvasView extends View {
    private static final String TAG = "BitmapCanvasView";
    private Bitmap mBitmap;
    private Paint mPaint;
    private Canvas mBmpCanvas;

    public BitmapCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        //创建空白的bitmap
        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        //创建对应的画布
        mBmpCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setTextSize(50);
        mBmpCanvas.drawText("是偶的数据", 20, 100, mPaint);
        //在画布上绘制图层
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }
}
