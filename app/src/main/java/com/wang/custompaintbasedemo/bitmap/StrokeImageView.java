package com.wang.custompaintbasedemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/11/12.
 */

public class StrokeImageView extends ImageView {
    private static final String TAG = "StrokeImageView";


    public StrokeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //向imageview添加背景
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        setStateDrawable(this, paint);

    }

    private void setStateDrawable(ImageView imageView, Paint paint) {
        //拿到源图像
        BitmapDrawable bg = (BitmapDrawable) imageView.getDrawable();
        Bitmap srcBmp = bg.getBitmap();
        //创建纯色背景
        Bitmap bitmap = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(srcBmp.extractAlpha(), 0,0, paint);
        //添加状态
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(getResources(), bitmap));

        imageView.setBackground(sld);
    }
}
