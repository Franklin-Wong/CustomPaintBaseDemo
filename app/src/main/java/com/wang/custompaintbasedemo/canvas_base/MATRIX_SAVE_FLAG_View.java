package com.wang.custompaintbasedemo.canvas_base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/29.
 *
 * 位置矩阵Matrix，
 */

public class MATRIX_SAVE_FLAG_View extends View {

    private Paint mPaint;

    public MATRIX_SAVE_FLAG_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置保存模式
//        canvas.save();
//        canvas.rotate(40);
        canvas.drawColor(Color.RED);
        canvas.clipRect(100, 0, 200, 100);
//        canvas.drawRect(100, 0, 200, 100, mPaint);
//        canvas.drawColor(Color.GRAY);
        canvas.restore();

        mPaint.setColor(Color.BLACK);
        canvas.drawRect(100,0,200,100, mPaint);
//        canvas.drawColor(Color.BLACK);
    }
}
