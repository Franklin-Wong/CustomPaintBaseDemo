package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Wongerfeng on 2018/11/19.
 */

public class DoubleBufferingView extends SurfaceView {
    private static final String TAG = "DoubleBufferingView";
    private Paint mPaint;


    public DoubleBufferingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(30);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawText(holder);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void drawText(final SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 10; i++){
                    Canvas canvas = holder.lockCanvas();
                    if (canvas != null){
                        canvas.drawText(i + "", i * 30, 50, mPaint);

                    }
                    holder.unlockCanvasAndPost(canvas);
                    Log.i(TAG, "run: i="+ i);
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
