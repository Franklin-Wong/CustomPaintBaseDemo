package com.wang.custompaintbasedemo.animators;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/11.
 */

public class LoadingImageView extends ImageView {
    private static final String TAG = "LoadingImageView";

    private int mTop;
    private int mCurrentImgIndex = 0;
    private static int mImgCount = 3;

    public LoadingImageView(Context context) {
        super(context);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100, 0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        //设置插值器
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                /* 移动控件的顶端位置 */
                setTop(mTop - value);
//                Log.i(TAG, "onAnimationUpdate: setTop = "+ (mTop-value));
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "onAnimationStart: ");
                //在动画开始时，添加资源图片
                setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher, null));

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
//                Log.i(TAG, "onAnimationRepeat: mCurrentImgIndex = "+mCurrentImgIndex);

                //没重复一次 更换一张图片
                mCurrentImgIndex++;
                switch (mCurrentImgIndex % mImgCount){
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher, null));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher, null));
                        break;

                    case 2:
                        setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher, null));
                        break;

                    default:
                        break;
                }

            }
        });

        valueAnimator.start();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout: top = "+ top);
        mTop = top;

    }
}
