package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/30.
 *
 */

public class ShapeView extends View {
    private static final String TAG = "ShapeView";
    private ShapeDrawable mShapeDrawable;

    private float[] outerRadii;
    private float[] innerRadii;
    private RectF mRect;
    //PathShape
    private Path mPath;

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        setLayerType(LAYER_TYPE_NONE, null);
//        mShapeDrawable = new ShapeDrawable(new ArcShape(0, 270));
        mPath = new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(100, 0);
        mPath.lineTo(100,100);
        mPath.lineTo(0, 100);
        mPath.close();

        mShapeDrawable = new ShapeDrawable(new PathShape(mPath, 100, 200));

        //两个数据一组，一个是椭圆的X轴半径，一个是Y轴半径
//        outerRadii = new float[]{12,12,12,12,0,0,0,0};
//        innerRadii = new float[]{50,12,0,0,12,50,0,0};
//        mRect = new RectF(6,6,6,6);
//        mShapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, mRect ,innerRadii));
//

        mShapeDrawable.setBounds(0,0,250,150);
//        mShapeDrawable.setBounds(50,50,200,100);
//        //获取自带的画笔，设置颜色
        mShapeDrawable.getPaint().setColor(Color.YELLOW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在当前控件ShapeView上绘制颜色，而不是mShapeDrawable本身
        mShapeDrawable.draw(canvas);

    }
}
