package com.wang.custompaintbasedemo.path__measure;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/19.
 */

public class GetPosTanVIew extends View {

    private static final String TAG = "GetPosTanVIew";

    private Path mCirclePath, mDstPath;
    private PathMeasure mPathMeasure;
    private Paint mPaint;
    private float mCurValue;
    private Bitmap mArrayBmp;
    private Matrix mMatrix;

    public GetPosTanVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        //加载资源图片
        mArrayBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        mMatrix = new Matrix();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        mCirclePath = new Path();
        mDstPath = new Path();

        mCirclePath.addCircle(100, 100, 50, Path.Direction.CW);

        mPathMeasure = new PathMeasure(mCirclePath, true);
        ///设置动画
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurValue = (float) animation.getAnimatedValue();
                invalidate();
                Log.i(TAG, "onAnimationUpdate: value = "+ mCurValue);
            }
        });
        animator.setDuration(4000);
        animator.start();


    }

    private float[] pos = new float[2];
    private float[] tan = new float[2];

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float length = mPathMeasure.getLength();
        float stop = length * mCurValue;

        mPathMeasure.getPosTan(stop, pos, tan);
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180 / Math.PI);

        /* getPosTan 函数实现动画*/
//        mMatrix.postRotate(degrees, mArrayBmp.getWidth() / 2, mArrayBmp.getHeight() / 2);
//        mMatrix.postTranslate(pos[0], pos[1]);

        /* */
        mPathMeasure.getMatrix(stop, mMatrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        mMatrix.preTranslate(- mArrayBmp.getWidth() / 2, - mArrayBmp.getHeight() / 2);

        canvas.drawBitmap(mArrayBmp, mMatrix, mPaint);


    }
}
