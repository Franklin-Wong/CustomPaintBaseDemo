package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by Wongerfeng on 2018/11/19.
 */

public class NumbersView extends SurfaceView {
    private static final String TAG = "NumbersView";
    private Paint mPaint;

    private ArrayList<Integer> mList = new ArrayList<>();

    public NumbersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
//                drawText(holder);
//                drawTextEachTime(holder);
//                drawTextEachThread(holder);
                drawTextEachBackground(holder);
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }
            @Override



            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void drawTextEachBackground(final SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true){
                    Rect dirtyRect = new Rect(0, 0, 1, 1);
//                    Canvas canvas = holder.lockCanvas(dirtyRect);
//                    Rect clipBounds = canvas.getClipBounds();
//                    if (getWidth() == clipBounds.width() && getHeight() == clipBounds.height()){
//                        canvas.drawColor(Color.LTGRAY);
//                        holder.unlockCanvasAndPost(canvas);
//                    }else {
//                        holder.unlockCanvasAndPost(canvas);
//                        break;
//                    }
//                }
                ///2 清空屏幕操作
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                Canvas canvas1 = holder.lockCanvas(dirtyRect);
                canvas1.drawPaint(mPaint);
                holder.unlockCanvasAndPost(canvas1);

                for (int i = 0; i < 10; i++){
                    //绘制方格背景
                    int itemWidth = 50;
                    int itemHeight = 50;
                    Rect rect = new Rect(i * itemWidth, 0, (i + 1) * itemWidth - 10, itemHeight);
                    //
                    Canvas canvas = holder.lockCanvas(rect);
                    if (canvas != null){
                        canvas.drawColor(Color.BLUE);
                        canvas.drawText(i + "", i * itemWidth + 10, itemHeight/2, mPaint );

                    }
                    holder.unlockCanvasAndPost(canvas);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }


    private void drawTextEachThread(final SurfaceHolder holder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 10; i++){
                    Canvas canvas = holder.lockCanvas();
                    mList.add(i);
                    if (canvas != null){
                        for (int num : mList){
                            canvas.drawText(num + "", num * 30, 50, mPaint);
                            Log.i(TAG, "run: num="+ num);
                        }
                    }
                    holder.unlockCanvasAndPost(canvas);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
    private void drawTextEachTime(SurfaceHolder holder) {
        for (int i = 0;i < 10; i++){
            Canvas canvas = holder.lockCanvas();
            mList.add(i);
            if (canvas != null){
                for (int num : mList){
                    canvas.drawText(num + "", num * 30, 50, mPaint);
                    Log.i(TAG, "run: num="+ num);
                }
            }
            holder.unlockCanvasAndPost(canvas);
        }

    }

    private void drawText(final SurfaceHolder holder) {

                for (int i = 0;i < 10; i++){
                    Canvas canvas = holder.lockCanvas();
                    if (canvas != null){
                        canvas.drawText(i + "", i * 30, 50, mPaint);
                    }
                    holder.unlockCanvasAndPost(canvas);
                    Log.i(TAG, "run: i="+ i);
                }
    }
}
