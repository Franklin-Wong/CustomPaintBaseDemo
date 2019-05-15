package com.wang.custompaintbasedemo.mix_mode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/25.
 * 可擦除view
 */

public class EraserView_SRCOUT extends View {
    private static final String TAG = "EraserView_SRCOUT";
    private Paint mPaint;
    private Bitmap dstBmp, srcBmp;
    private Bitmap textBmp;
    private PorterDuffXfermode mXfermode;
    private Path mPath;
    private float mPreX, mPreY;

    private Canvas c;

    public EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(45);
        mPaint.setStyle(Paint.Style.STROKE);
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;

        //带有画面内容的是源图像
        srcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);
        //绘制同样大小的目标图像，作为擦除后的显示图像
        dstBmp = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        ///创建有文字的图片
//        textBmp = BitmapFactory.decodeResource(getResources(), R.drawable.item_circle, null);

        mPath = new Path();
        c = new Canvas(dstBmp);

        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //移动到指定接触点
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();

                return true;
            case MotionEvent.ACTION_MOVE:
                //实时移动到新的接触点
                float endX = (mPreX + event.getX());
                float endY = (mPreY + event.getY());
                mPath.quadTo(mPreX, mPreY, endX, endY);
                //更新下次移动的起点
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            default:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画出底层的结果图片
//        canvas.drawBitmap(textBmp, null, new RectF(0, 0, dstBmp.getWidth(), dstBmp.getHeight()), mPaint);
        /*只要上层的图片被擦除，就一定会露出底层的所画内容，不一定是图片*/

        int id = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //把手势画在画布上
        c.drawPath(mPath, mPaint);

        //把空白的目标图像画到画布上
        canvas.drawBitmap(dstBmp, 0,0,mPaint);
        //计算源图像区域
        mPaint.setXfermode(mXfermode);
        //最后绘制有画面的源图像
        canvas.drawBitmap(srcBmp, 0,0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(id);

    }
}
