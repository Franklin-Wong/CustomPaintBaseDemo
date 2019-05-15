package com.wang.custompaintbasedemo.canvas_base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/30.
 */

public class RestoreToCountView extends View {
    private static final String TAG = "RestoreToCountView";
    private Paint mPaint;

    public RestoreToCountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int idOne = canvas.save();
        canvas.clipRect(0,0,800,800);
        canvas.drawColor(Color.RED);
        Log.i(TAG, "onDraw: count = " + canvas.getSaveCount()+"-----idOne = " + idOne);

        int id2 = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(100,100,700,700);
        canvas.drawColor(Color.GREEN);
        Log.i(TAG, "onDraw: count = " + canvas.getSaveCount()+"-----id2 = " + id2);

        int id3 = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(200,200,600,600);
        canvas.drawColor(Color.YELLOW);
        Log.i(TAG, "onDraw: count = " + canvas.getSaveCount()+"-----id3 = " + id3);

        int id4 = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);


        canvas.clipRect(300,300, 500,500);
        canvas.drawColor(Color.BLUE);
        Log.i(TAG, "onDraw: count = " + canvas.getSaveCount()+"-----id4 = " + id4);

        //提取画布3
//        canvas.restoreToCount(3);
//        canvas.drawColor(Color.GRAY);
//        Log.i(TAG, "onDraw: count = " + canvas.getSaveCount());
    }
}
