package com.wang.custompaintbasedemo.bitmap.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/11/19.
 */

public class MovingSurfaceView extends SurfaceView {
    private static final String TAG = "MovingSurfaceView";

    private int mSurfaceWidth, mSurfaceHeight, mWindth;
    private Bitmap mBgBmp;
    ///开始绘制的坐标
    private int mBitposX;
    //移动的步伐
    private final  int BITMAP_STEP = 1;

    private Canvas mCanvas;
    private SurfaceHolder surfaceHolder;
    private Thread mThread;
    private boolean flag;


    public MovingSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.i(TAG, "surfaceCreated: ");
                flag = true;
                startAnimation();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.i(TAG, "surfaceChanged: ");
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                flag = false;
                Log.i(TAG, "surfaceDestroyed: ");
            }
        });
    }

    private void startAnimation() {
        mSurfaceWidth = getWidth();
        mSurfaceHeight = getHeight();

        mWindth = (int) (mSurfaceWidth * 3 / 2);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rain_man);
        mBgBmp = Bitmap.createScaledBitmap(bitmap, mWindth, mSurfaceHeight, true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    mCanvas = surfaceHolder.lockCanvas();
                    drawView();
                    surfaceHolder.unlockCanvasAndPost(mCanvas);

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    protected void drawView(){
        //清空屏幕
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        //绘制屏幕背景
        mCanvas.drawBitmap(mBgBmp, mBitposX, 0, null);

        /**滚动效果 **/
        switch (state){
            case LEFT:
                mBitposX -= BITMAP_STEP;
                break;
            case RIGHT:
                mBitposX += BITMAP_STEP;
                break;
            default:
                break;

        }

        if (mBitposX <= -mSurfaceWidth / 2){
            state = State.RIGHT;
        }
        if (mBitposX >= 0){
            state = State.LEFT;
        }

    }

    private State state = State.LEFT;

    private enum State{
        LEFT, RIGHT
    }
}
