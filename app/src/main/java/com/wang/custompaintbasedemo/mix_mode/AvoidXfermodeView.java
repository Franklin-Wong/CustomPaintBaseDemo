package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/24.
 */

public class AvoidXfermodeView extends View {
    private static final String TAG = "AvoidXfermodeView";

    private Paint mPaint;
    private Bitmap mBitmap;

    public AvoidXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ///使用AvoidXfermode要禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ///使用离屏绘制

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = width * mBitmap.getHeight()/mBitmap.getWidth();

        //新建图层
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //绘制核心代码
        canvas.drawBitmap(mBitmap, null, new Rect(0,0, width, height), mPaint);
//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE, 100, AvoidXfermode.MODE.TARGET));


        //还原图层
        canvas.restoreToCount(layerId);



    }
}
