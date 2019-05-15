package com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.wang.custompaintbasedemo.R;

/**
 *
 */
public class KeyFrameActivity extends AppCompatActivity {
    private static final String TAG = "KeyFrameActivity";

    private TextView mTextView;

    LayoutAnimationController mLayoutAnimationController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_frame);
        mTextView = findViewById(R.id.tvAnim);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTelephoneKeyframe();
//                initKeyFrame();
//                initCharKeyframe();
            }
        });
        mTextView.animate().y(100).y(200).scaleX(2).scaleY(2).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "onAnimationStart: ");
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }
            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i(TAG, "onAnimationCancel: ");
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });

    }

    /**
     * 字符变换
     */
    private void initCharKeyframe(){
        Keyframe keyframe0 = Keyframe.ofObject(0f, new Character('A'));
        Keyframe keyframe1 = Keyframe.ofObject(0.1f, new Character('L'));
        Keyframe keyframe2 = Keyframe.ofObject(1f, new Character('Z'));
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("CharText", keyframe0, keyframe1, keyframe2);
        ///使用了 ofObject方法，必须设置Evaluator，不然回报空指针
        frameHolder.setEvaluator(new CharEvaluator());
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, frameHolder);
        animator.setDuration(3000);
        animator.start();


    }

    private void initTelephoneKeyframe(){

        Keyframe keyframe = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.1f, -20);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe keyframe3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe keyframe4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe keyframe5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe keyframe6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe keyframe7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe keyframe8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe keyframe9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe keyframe10 = Keyframe.ofFloat(1f, 0f);

        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe, keyframe1, keyframe2, keyframe3,
                keyframe4, keyframe5, keyframe6, keyframe7, keyframe8,keyframe9,keyframe10);

        Keyframe scaleXFrame = Keyframe.ofFloat(0f, 1);
        Keyframe scaleXFrame1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXFrame9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXFrame10 = Keyframe.ofFloat(1f, 1f);

        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofKeyframe("scaleX", scaleXFrame, scaleXFrame1, scaleXFrame9, scaleXFrame10);

        Keyframe scaleYFrame = Keyframe.ofFloat(0f, 1);
        Keyframe scaleYFrame1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYFrame9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYFrame10 = Keyframe.ofFloat(1f, 1f);

        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofKeyframe("scaleY", scaleYFrame, scaleYFrame1, scaleYFrame9, scaleYFrame10);

        /**
         * 使用ofPropertyValuesHolder函数，避开繁琐的组合动画的构造工程
         */
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, valuesHolder, scaleXHolder, scaleYHolder);
        animator.setDuration(1000);
        animator.start();


    }

    /**
     * 摆动
     */
    private void initKeyFrame() {
        Keyframe keyframe0 = Keyframe.ofFloat(0f, 0);
        Keyframe keyframe1 = Keyframe.ofFloat(0.5f, 100f);
//        keyframe1.setInterpolator(new BounceInterpolator());
        Keyframe keyframe2 = Keyframe.ofFloat(1);
        keyframe2.setInterpolator(new BounceInterpolator());
        keyframe2.setValue(0f);

        PropertyValuesHolder keyframeHolder = PropertyValuesHolder.ofKeyframe("rotation", keyframe0, keyframe1, keyframe2);

        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTextView, keyframeHolder);
        animator.setDuration(3000);
        animator.start();
    }




 }
