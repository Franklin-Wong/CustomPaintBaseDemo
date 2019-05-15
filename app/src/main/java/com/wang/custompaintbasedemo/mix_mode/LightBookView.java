package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/25.
 */

public class LightBookView extends View {
    private static final String TAG = "LightBookView";
    private Paint mPaint;
    private Bitmap mDSTBmp, mSRCBmp;
    private PorterDuffXfermode mPorterDuffXfermode;
    public LightBookView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mDSTBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        mSRCBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mDSTBmp, 0,0, mPaint);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawBitmap(mSRCBmp, 0, 0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);

    }
}
