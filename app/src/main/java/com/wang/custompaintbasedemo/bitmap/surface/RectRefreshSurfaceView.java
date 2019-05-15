package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Wongerfeng on 2018/11/22.
 */

public class RectRefreshSurfaceView extends SurfaceView {
    private static final String TAG = "RectRefreshSurfaceView";
    private Paint mPaint;
    private Rect mRect;
    private Rect mBigRect, mMidRect, mSmallRect;
    private Rect mCircleRect, mTextRect;

    public RectRefreshSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mRect = new Rect(0, 0, 1, 1);
        mBigRect = new Rect(10, 10, 600, 600);
        mMidRect = new Rect(30, 30, 570, 570);
        mSmallRect = new Rect(60, 60, 540, 540);
        mCircleRect = new Rect(200, 200, 400, 400);
        mTextRect = new Rect(250, 250, 350, 350);

        mPaint = new Paint();
        mPaint.setColor(Color.argb(0x1f, 0xff , 0xff, 0xff));
        mPaint.setTextSize(30);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawText(holder);
                Log.i(TAG, "surfaceCreated: ");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.i(TAG, "surfaceDestroyed: ");
            }
        });
    }

    private void drawText(final SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ///1 清空屏幕操作
                while (true){
                    Canvas canvas = holder.lockCanvas(mRect);
                    //获取边界范围
                    Rect canvasClipBounds = canvas.getClipBounds();
                    //在边界范围内涂色
                    if (getWidth() == canvasClipBounds.width() && getHeight() == canvasClipBounds.height()){
                        canvas.drawColor(Color.LTGRAY);
                        holder.unlockCanvasAndPost(canvas);
                    }else {
                        //Finish editing pixels in the surface.
                        holder.unlockCanvasAndPost(canvas);
                        //退出循环
                        break;
                    }
                }

                ///2 清空屏幕操作
//                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//                Canvas canvas1 = holder.lockCanvas(mRect);
//                dumpCanvasRect(canvas1);
//                canvas1.drawPaint(mPaint);
//                holder.unlockCanvasAndPost(canvas1);

                for (int i = 0; i < 10; i++){
                    Log.i(TAG, "run: i="+i);
                    if (i == 0){
                        Canvas canvas = holder.lockCanvas(mBigRect);
                        dumpCanvasRect(canvas);
                        canvas.drawColor(Color.RED);
                        holder.unlockCanvasAndPost(canvas);
                        Log.i(TAG, "run: mBigRect="+mBigRect.width() +"---"+mBigRect.height());
                    }
                    if (i == 1){
                        Canvas canvas = holder.lockCanvas(mMidRect);
                        dumpCanvasRect(canvas);
                        canvas.drawColor(Color.GREEN);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 2){
                        Canvas canvas = holder.lockCanvas(mSmallRect);
                        dumpCanvasRect(canvas);
                        canvas.drawColor(Color.BLUE);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    if (i == 3){
                        Canvas canvas = holder.lockCanvas(mCircleRect);
                        dumpCanvasRect(canvas);
                        mPaint.setColor(Color.argb(0x3f, 0xff , 0xff, 0xff));
                        canvas.drawCircle(300, 300, 100, mPaint);
                        holder.unlockCanvasAndPost(canvas);
                    }
                    if (i == 4){
                        Canvas canvas = holder.lockCanvas(mTextRect);
                        dumpCanvasRect(canvas);
                        mPaint.setColor(Color.RED);
                        canvas.drawText(i + "", 300,300, mPaint);
                        holder.unlockCanvasAndPost(canvas);
                    }

                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    private void dumpCanvasRect(Canvas canvas){
        if (canvas != null){
            Rect clipBounds = canvas.getClipBounds();
            Log.i(TAG, "dumpCanvasRect: left="+clipBounds.left+"-----right="+clipBounds.right+"---top="+clipBounds.top+"---bottom="+clipBounds.bottom);

        }

    }

}
