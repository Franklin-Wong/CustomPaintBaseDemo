package com.wang.custompaintbasedemo;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.wang.custompaintbasedemo.evaluators.CharEvaluator;
import com.wang.custompaintbasedemo.evaluators.FallingBallEvaluator;
import com.wang.custompaintbasedemo.viewgroup_animator.ViewGroupAnimActivity;

/**
 *
 */
public class BasePaintingActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btStartAnim,btEndAnim;
    private TextView mTvAnim;

    private ValueAnimator mValueAnimator = ValueAnimator.ofObject(new CharEvaluator()),cloneAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvAnim = findViewById(R.id.tvAnim);
        btStartAnim = findViewById(R.id.startAnim);
        btEndAnim = findViewById(R.id.endAnim);

        btEndAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: endAnim");
//                mValueAnimator.cancel();
//                mValueAnimator.removeAllListeners();
//                mValueAnimator.removeAllUpdateListeners();

                cloneAnimator.removeAllUpdateListeners();
                cloneAnimator.cancel();

            }
        });
        btStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: btStartAnim");
                Animation scaleAnimation = AnimationUtils.loadAnimation(BasePaintingActivity.this, R.anim.scale);
                Animation alphaAnimation = AnimationUtils.loadAnimation(BasePaintingActivity.this, R.anim.alpha);
                final Animation rotateAnimation = AnimationUtils.loadAnimation(BasePaintingActivity.this, R.anim.rotate);
                Animation traslateAnimation = AnimationUtils.loadAnimation(BasePaintingActivity.this, R.anim.translate);

                Animation animation = AnimationUtils.loadAnimation(BasePaintingActivity.this, R.anim.set);
                ScaleAnimation scaleAnimation1 = getScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mTvAnim.startAnimation(rotateAnimation);

                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mValueAnimator = initValueAnimator();
                cloneAnimator = mValueAnimator.clone();
                cloneAnimator.setStartDelay(1500);
                cloneAnimator.start();

                rotateAnimation.setInterpolator(new CycleInterpolator(1f));
//                mTvAnim.startAnimation(traslateAnimation);
            }
        });

        mTvAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BasePaintingActivity.this, ViewGroupAnimActivity.class));
            }
        });
    }

    /**
     *
     */
    private ValueAnimator initValueAnimator() {
        /*移动轨迹的点*/
        /*
        mValueAnimator = ValueAnimator.ofInt(0, 400,50,300);
         */
        /*颜色变化
         */
        /*
        mValueAnimator = ValueAnimator.ofInt(0xffffff00, 0xff0000ff);
         */
        /*字符的变化
         */
        /*
        mValueAnimator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
         */

        ///动画
        mValueAnimator = ValueAnimator.ofObject(new FallingBallEvaluator(),
                new Point(0,0),
                new Point(500,500));


        mValueAnimator.setDuration(3000);
///        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
///        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        /*字符转换器*/
///        mValueAnimator.setEvaluator(new CharEvaluator());
///        mValueAnimator.setEvaluator(new CharEvaluator());
///        mValueAnimator.setEvaluator(new ArgbEvaluator());
        mValueAnimator.setEvaluator(new FallingBallEvaluator());

        mValueAnimator.setInterpolator(new LinearInterpolator());

        ///添加监听器
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                Integer value = (Integer) animation.getAnimatedValue();

                /*控件的位置变换*/
//                mTvAnim.layout(mTvAnim.getLeft(),value, value + mTvAnim.getWidth(), value + mTvAnim.getHeight());
                /*颜色的渐变 */
                /*
                mTvAnim.setBackgroundColor(value);

                */
                /*其他对象的渐变*/
                ///char value = (char) animation.getAnimatedValue();
                ///mTvAnim.setText(String.valueOf(value));

                ///根据点运动
                Point value = (Point) animation.getAnimatedValue();
                mTvAnim.layout(value.x,value.y, value.x + mTvAnim.getWidth(), value.y + mTvAnim.getHeight());


                Log.i(TAG, "onAnimationUpdate: value="+value);
            }
        });

        mValueAnimator.addListener(new Animator.AnimatorListener() {
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
        //可以通过克隆的对象来启动动画
//        mValueAnimator.start();

        return mValueAnimator;
    }

    private ScaleAnimation getScaleAnimation(float v, float v1, float v2, float v3, int relativeToSelf, float v4, int relativeToSelf1, float v5) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(v, v1, v2, v3, relativeToSelf, v4, relativeToSelf1, v5);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }
}
