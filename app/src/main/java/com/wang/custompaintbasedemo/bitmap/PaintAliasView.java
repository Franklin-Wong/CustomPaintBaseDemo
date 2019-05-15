package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/11/15.
 */

public class PaintAliasView extends View {
    private static final String TAG = "PaintAliasView";
    private Paint mPaintAnti;
    private Canvas mCanvas;
    private int mLeft, mTop;
    private Paint mPaint;
    private Bitmap mBitmap;

    public PaintAliasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaintAnti = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintAnti.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);

        //先在bitmap上面绘制
        mBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas.drawColor(Color.LTGRAY);
        mCanvas.drawCircle(mLeft + 100, mTop + 100, 100, mPaint);
        canvas.drawBitmap(mBitmap, mLeft, mTop, mPaint);
//        canvas.drawCircle(mLeft + 200, mTop + 150, 100, mPaintAnti);
        canvas.drawCircle(mLeft + 600, mTop + 150, 100, mPaint);


    }
}
