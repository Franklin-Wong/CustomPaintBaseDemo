package com.wang.custompaintbasedemo.viewgroup_animator;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.wang.custompaintbasedemo.R;

/**
 *
 */
public class ViewGroupAnimActivity extends AppCompatActivity {
    private static final String TAG = "ViewGroupAnimActivity";
    private LinearLayout mLinearLayout;
    private Button mBtAdd, mBtRemove;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_anim);
        mLinearLayout = findViewById(R.id.llContainer);

        mBtAdd = findViewById(R.id.btAdd);
        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButtonView();
                initViewHelper();
            }
        });
        mBtRemove = findViewById(R.id.btRemove);
        mBtRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeButtonView();
                initViewHelperAnimator();
            }
        });

//        initLayoutTransition();

        initTransitionChanging();
    }

    /**
     * NineOldAndroids 提供的ViewHelper
     */
    private void initViewHelper(){
        ViewPropertyAnimator.animate(mBtAdd).translationX(-50);

    }
    private void initViewHelperAnimator(){
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float cur = (Float) animation.getAnimatedValue();
                ViewHelper.setTranslationX(mBtRemove, cur);

            }
        });

        animator.setDuration(2000);
        animator.start();
    }

    private void initTransitionChanging(){
        LayoutTransition transition = new LayoutTransition();
        /*其他控件需要的变化*/
        /*构造动画使用ofInt、ofFloat 函数时，参数中的第一个值和最后一个值必须相同*//* 如果参数中是所有值都相同，动画不会有效果*/
        PropertyValuesHolder left = PropertyValuesHolder.ofInt("left", 0, 0);
        PropertyValuesHolder top = PropertyValuesHolder.ofInt("top", 0, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mLinearLayout, left, top, scaleX);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator);

        mLinearLayout.setLayoutTransition(transition);
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i(TAG, "startTransition: transition:"+ transition + "----container:"+container.getChildCount()
                        + "----view:"+ view.getClass().getName() +"---transitionType:"+ transitionType);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i(TAG, "endTransition: transition:"+ transition + "----container:"+container.getChildCount()
                        + "----view:"+ view.getClass().getName() +"---transitionType:"+ transitionType);
            }
        });

    }

    private void initLayoutTransition(){
        LayoutTransition transition = new LayoutTransition();

        ObjectAnimator animatorIn = ObjectAnimator.ofFloat(null, "rotationY", 0f, 360f, 0f);
        transition.setAnimator(LayoutTransition.APPEARING, animatorIn);

        ObjectAnimator animatorOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);
        transition.setAnimator(LayoutTransition.DISAPPEARING, animatorOut);

        mLinearLayout.setLayoutTransition(transition);
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i(TAG, "startTransition: transition:"+ transition + "----container:"+container.getChildCount()
                        + "----view:"+ view.getClass().getName() +"---transitionType:"+ transitionType);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                Log.i(TAG, "endTransition: transition:"+ transition + "----container:"+container.getChildCount()
                        + "----view:"+ view.getClass().getName() +"---transitionType:"+ transitionType);
            }
        });

    }

    private void removeButtonView() {
        if (i > 0){
            mLinearLayout.removeViewAt(0);
            i--;
        }
    }

    private void addButtonView() {
        i++;
        Button button = new Button(this);
        button.setTextColor(Color.WHITE);
        button.setText("按钮"+ i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        /*添加控件到首位*/
        mLinearLayout.addView(button, 0);
        /*添加控件到最后, 将不能实现动画，不能重绘*/
//        mLinearLayout.addView(button);

    }
}
