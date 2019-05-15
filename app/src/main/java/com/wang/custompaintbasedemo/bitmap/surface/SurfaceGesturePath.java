package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Wongerfeng on 2018/11/19.
 */

public class SurfaceGesturePath extends SurfaceView {
    private static final String TAG = "SurfaceView";

    private Paint mPaint;
    private Path mPath;
    
    public SurfaceGesturePath(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ///需要调用 onDraw 重新绘制 false
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
        
        mPath = new Path();
        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //移动到路径的起始点
            mPath.moveTo(x, y);
            Log.i(TAG, "onTouchEvent: ACTION_DOWN");
            //消费事件
            return true;
            
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            //绘制路径轨迹
            mPath.lineTo(x, y);
            Log.i(TAG, "onTouchEvent: ACTION_MOVE");
        }

        drawCanvas();
//        postInvalidate();
//        Log.i(TAG, "onTouchEvent: postInvalidate");

        return super.onTouchEvent(event);
    }

    private void drawCanvas() {
        //如果使绘制过程不占用主线程资源，就在子线程中绘制
        new Thread(new Runnable() {
            @Override
            public void run() {
                SurfaceHolder surfaceHolder = getHolder();
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawPath(mPath, mPaint);

                Log.i(TAG, "run: ");
                surfaceHolder.addCallback(new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        Log.i(TAG, "surfaceCreated: ");
                    }
                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                        Log.i(TAG, "surfaceChanged: ");
                    }
                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                        Log.i(TAG, "surfaceDestroyed: ");
                    }
                });
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }).start();
        Log.i(TAG, "drawCanvas: ");
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawPath(mPath, mPaint);
//        Log.i(TAG, "onDraw: ");
//    }
}
