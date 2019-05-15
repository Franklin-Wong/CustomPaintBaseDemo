package com.wang.custompaintbasedemo.matrix_11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/12/17.
 */

public class MatrixView extends View {
    private static final String TAG = "MatrixView";
    private ColorMatrix mOldMatrix;

    private ColorMatrix mBlueColorMatrix;
    private ColorMatrix mReverseMatrix;
    private ColorMatrix mScaleMatrix;
    private Paint mPaint;
    private Bitmap mBitmap;
    private Rect mRect;
    private ColorMatrixColorFilter mColorFilter;

    private ColorMatrix mColorGreenMatrix;
    private ColorMatrix mBlackWhiteMatrix;
    private ColorMatrix mReverseColorMatrix;

    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        mRect = new Rect(0, 0, 500, 500 * mBitmap.getHeight() / mBitmap.getWidth());
        ///生成色彩变换矩阵————偏蓝
        mBlueColorMatrix = new ColorMatrix(new float[]{
                0,0,0,0,0,
                0,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0

        });
        mColorGreenMatrix = new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,0.5f,0

        });
        //色彩反转
        mReverseMatrix = new ColorMatrix(new float[]{
                -1,0,0,0,225,
                0,-1,0,0,225,
                0,0,-1,0,225,
                0,0,0,1,0

        });
        //放大倍数
        mScaleMatrix = new ColorMatrix(new float[]{
                1.2f,0,0,0,0,
                0,1.2f,0,0,50,
                0,0,1.2f,0,0,
                0,0,0,1.2f,0

        });
        //黑白照片
        mBlackWhiteMatrix = new ColorMatrix(new float[]{
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0.213f,0.715f,0.072f,0,0,
                0,0,0,1,0

        });
        //色彩反色
        mReverseColorMatrix = new ColorMatrix(new float[]{
                0,1,0,0,0,
                1,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0

        });
        //照片变旧
        mOldMatrix = new ColorMatrix(new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0

        });


        mColorFilter = new ColorMatrixColorFilter(mOldMatrix);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ///绘制原始位图
        canvas.drawBitmap(mBitmap, null,mRect, mPaint);

        canvas.translate(510,0);

        mPaint.setColorFilter(mColorFilter);
        canvas.drawBitmap(mBitmap, null, mRect, mPaint);



    }
}
