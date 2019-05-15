package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/11/16.
 */

public class PaintAliasUpdateView extends View {

    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private int mLeft, mTop;


    public PaintAliasUpdateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        //先在bitmap上面绘制
        mBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清空bitmap
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mCanvas.drawCircle(mLeft + 100, mTop + 100, 100, mPaint);

        canvas.drawBitmap(mBitmap, mLeft, mTop, mPaint);

    }
}
