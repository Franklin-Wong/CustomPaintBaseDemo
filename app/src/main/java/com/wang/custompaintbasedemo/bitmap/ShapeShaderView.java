package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/30.
 */

public class ShapeShaderView extends View {

    private ShapeDrawable mShapeDrawable;

    public ShapeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mShapeDrawable = new ShapeDrawable(new RectShape());
        mShapeDrawable.setBounds(new Rect(100,100,300,300));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rain_man);

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mShapeDrawable.getPaint().setShader(bitmapShader);
        mShapeDrawable.setAlpha(100);
        mShapeDrawable.setPadding(new Rect());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShapeDrawable.draw(canvas);

    }
}
