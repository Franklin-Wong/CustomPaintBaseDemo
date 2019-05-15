package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/10/30.
 *
 */

public class RegionShapeView extends View {
    private static final String TAG = "RegionShapeView";
    private ShapeDrawable mShapeDrawable;

    private Rect mRect1, mRect2;
    private Region mRegion1, mRegion2;

    public RegionShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        setLayerType(LAYER_TYPE_NONE, null);

        mRect1 = new Rect(50,0,90,150);
        mRect2 = new Rect(0,50,250,100);

        mRegion1 = new Region(mRect1);
        mRegion2 = new Region(mRect2);

        mRegion1.op(mRect2, Region.Op.XOR);
        mShapeDrawable = new ShapeDrawable(new RegionShape(mRegion1));

        mShapeDrawable.setBounds(0,0,250,150);
        //获取自带的画笔，设置颜色
        mShapeDrawable.getPaint().setColor(Color.YELLOW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在当前控件ShapeView上绘制颜色，而不是mShapeDrawable本身
        mShapeDrawable.draw(canvas);

    }
}
