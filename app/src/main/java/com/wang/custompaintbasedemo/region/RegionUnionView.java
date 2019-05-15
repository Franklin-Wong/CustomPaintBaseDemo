package com.wang.custompaintbasedemo.region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Wongerfeng on 2018/9/28.
 */

public class RegionUnionView extends View {
    private Paint mPaint;
    private RectF rectF;
    private Path mPath;
    private Rect mRect;

    private Path ovalPath;
    private Region mRegion;
    private Region smallRegion;
    private RectF mOvalRectF;

    public RegionUnionView(Context context) {
        super(context);

    }

    public RegionUnionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        rectF = new RectF(0, 0, 400, 600);
        mPath = new Path();
        mRect = new Rect(0, 0, 400, 800);
        mRegion = new Region(mRect);
        ovalPath = new Path();
        //设置一个小的矩形区域
        smallRegion = new Region();
        mOvalRectF = new RectF(0, 0, 400, 600);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.LTGRAY);
        canvas.translate(100,100);
        /*** 构造region */
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
//        drawRegion(canvas, mRegion, mPaint);

        /**构造区域交集*/
        ovalPath.addOval(mOvalRectF, Path.Direction.CCW);
//        canvas.drawPath(ovalPath, mPaint);


        smallRegion.setPath(ovalPath, new Region(0, 0, 400, 300));

        drawRegion(canvas, smallRegion, mPaint);


//        Region region1 = new Region(100, 900, 400, 500);
//        region1.union(new Rect(100, 900, 200, 1200));
//        drawRegion(canvas, region1, mPaint);


    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator iterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (iterator.next(rect)){
            canvas.drawRect(rect, paint);
        }
    }
}
