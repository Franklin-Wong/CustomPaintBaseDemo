package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/30.
 * 放大镜效果
 */

public class TelescopeShapeView extends View {
    private static final String TAG = "TelescopeShapeView";
    private Bitmap mBitmap;
    private ShapeDrawable mShapeDrawable;

    /**放大镜的半径*/
    private static final int RADIUS = 200;
    /**放大倍数*/
    private static final int FACTOR = 3;

    private final Matrix mMatrix = new Matrix();


    public TelescopeShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "TelescopeShapeView: ");
        init();

    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        //这个位置表示的是绘制shader的起始位置
        mMatrix.setTranslate(RADIUS - x*FACTOR, RADIUS - y*FACTOR);
        //为画笔设置Matrix
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        //以当前手指触点的中心画一个圆
        mShapeDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
        //每次位置更新之后，重绘
        invalidate();
        Log.i(TAG, "onTouchEvent: x="+x +"---y="+y);
        //返回TRUE ，表示本方法拦截了所有的touch事件
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: changed = "+ changed +"---left="+ left +"---top="+top +"---right="+"--bottom="+bottom);
        Log.i(TAG, "onLayout: getWidth()="+getWidth()+"---getHeight() ="+getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");
        if (mBitmap == null){
            //开始画布的初始化
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
            //根据源图像，生成一个指定宽高的bitmap，与当前的TelescopeShapeView相同的宽高
            mBitmap = Bitmap.createScaledBitmap(bmp, getWidth(), getHeight(), false);
            //生成一个放大的bitmap
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * FACTOR, mBitmap.getHeight() * FACTOR, true);
            //在Shader中显示
            BitmapShader shader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //创建圆形的ShapeDrawable
            mShapeDrawable = new ShapeDrawable(new OvalShape());
            //设置ShapeDrawable的阴影
            mShapeDrawable.getPaint().setShader(shader);

            //设置圆形的边界
            mShapeDrawable.setBounds(0,0, RADIUS * 2, RADIUS * 2);

        }
        //
        canvas.drawBitmap(mBitmap, 0,0, null);
        mShapeDrawable.draw(canvas);



    }
}
