package com.wang.custompaintbasedemo.canvas_base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/29.
 */

public class SaveLayorUseView extends View{
    private static final String TAG = "SaveLayorUseView";
    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mBmpCanvas;
    public SaveLayorUseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
//        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, null);

        mBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        mBmpCanvas = new Canvas(mBitmap);
        mBmpCanvas.drawColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

//        int id = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint, Canvas.ALL_SAVE_FLAG);
        int id = canvas.saveLayerAlpha(0, 0, 300, 200, 100, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.BLUE);
        canvas.skew(1.732f, 0);
        canvas.drawRect(0, 0, 150, 160, mPaint);
        canvas.restoreToCount(id);
        Log.i(TAG, "onDraw: getWidth() = "+ getWidth() +"---getHeight() = "+ getHeight());

    }
}
