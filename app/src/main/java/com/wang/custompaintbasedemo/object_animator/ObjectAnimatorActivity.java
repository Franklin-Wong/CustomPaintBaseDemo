package com.wang.custompaintbasedemo.object_animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wang.custompaintbasedemo.R;

public class ObjectAnimatorActivity extends AppCompatActivity {
    private static final String TAG = "ObjectAnimatorActivity";
    private static final String ROTATION = "rotation";
    private static final String TRANSLATIONX = "translationX";
    private static final String SCALEY = "scaleY";
    private static final String SCALEX = "scaleX";

    private Button btStartAnim,btEndAnim;
    private TextView mTvAnim;
//    private ObjectAnimator animator;
    private FallingBallImageView mBallImageView;
    private CustomScaleTextView mCustomScaleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        initView();
        initEvent();

    }

    private void initView() {
        mTvAnim = findViewById(R.id.tvAnim);
        btStartAnim = findViewById(R.id.startAnim);
        btEndAnim = findViewById(R.id.endAnim);
        mBallImageView = findViewById(R.id.fallingBall);
        mCustomScaleTextView = findViewById(R.id.tvCustom);
    }

    private void initEvent() {
        btStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnimator();
            }
        });
        btEndAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAnimator().cancel();
                initAnimatorSet().cancel();
                initAnimatorSetBuilder().cancel();
            }
        });

        mTvAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initAnimatorSet();
                initAnimatorSetBuilder();

            }
        });
    }

    private AnimatorSet initAnimatorSetBuilder(){
        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofInt(mTvAnim, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mTvAnim, "translationY", 0, 300, 0);
        translationY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator translationY1 = ObjectAnimator.ofFloat(mCustomScaleTextView, "translationY", 0, 400, 0);
        translationY1.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet.Builder builder = animatorSet.play(translationY);
        builder.with(translationY1).after(backgroundColorAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
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
        animatorSet.setTarget(mBallImageView);
        animatorSet.setDuration(3000);
        animatorSet.setStartDelay(2000);
        animatorSet.start();
        return animatorSet;

    }

    private AnimatorSet initAnimatorSet(){
        ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofInt(mTvAnim, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);

//        backgroundColorAnimator.setStartDelay(2000);

        ObjectAnimator translationY = ObjectAnimator.ofFloat(mTvAnim, "translationY", 0, 300, 0);

//        translationY.setStartDelay(2000);
        translationY.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator translationY1 = ObjectAnimator.ofFloat(mCustomScaleTextView, "translationY", 0, 400, 0);
//        translationY1.setStartDelay(2000);
        translationY1.setRepeatCount(ValueAnimator.INFINITE);

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playSequentially(backgroundColorAnimator, translationY, translationY1);
        animatorSet.playTogether(backgroundColorAnimator, translationY, translationY1);
        animatorSet.setDuration(3000);
        animatorSet.start();

        return animatorSet;
    }

    /**
     * 启动动画
     */
    private ObjectAnimator initAnimator() {
//        animator = ObjectAnimator.ofFloat(mTvAnim, "rotation", 0, 100, 0, 100);

//        animator = ObjectAnimator.ofFloat(mTvAnim, SCALEX, 0, 5, 0, 5);

//         animator = ObjectAnimator.ofObject(mBallImageView, "FallingPos",
//                 new FallingBallEvaluator(), new Point(0,0), new Point(500, 500));

        ObjectAnimator animator = ObjectAnimator.ofFloat(mCustomScaleTextView, "ScaleSize", 5);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();

        return animator;
    }

    private void initXMLAnimator(){
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator);
        animator.setTarget(mBallImageView);
        animator.start();
    }
}
