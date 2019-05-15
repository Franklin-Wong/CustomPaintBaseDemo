package com.wang.custompaintbasedemo.animator_set;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wang.custompaintbasedemo.R;

/**
 * 多选择菜单
 */
public class MenuChooseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MenuChooseActivity";
    private boolean mIsMenuOpen = false;
    private Button mMenuButton, mItemButton1,mItemButton2,mItemButton3,mItemButton4,mItemButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choose);
        initView();
        initEvent();

    }

    private void initView() {
        mMenuButton = findViewById(R.id.menu);
        mItemButton1 = findViewById(R.id.item1);
        mItemButton2 = findViewById(R.id.item2);
        mItemButton3 = findViewById(R.id.item3);
        mItemButton4 = findViewById(R.id.item4);
        mItemButton5 = findViewById(R.id.item5);
    }

    private void initEvent() {
        mMenuButton.setOnClickListener(this);
        mItemButton1.setOnClickListener(this);
        mItemButton2.setOnClickListener(this);
        mItemButton3.setOnClickListener(this);
        mItemButton4.setOnClickListener(this);
        mItemButton5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.menu:
                if (!mIsMenuOpen){
                    mIsMenuOpen = true;
                    openMenu();
                }else {
                    mIsMenuOpen = false;
                    Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                    closeMenu();
                }
                break;
            case R.id.item1:
                Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                break;
            case R.id.item5:
                Toast.makeText(this, "你点击了"+ v, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    private void closeMenu() {
        doAnimateClose(mItemButton1, 0, 5, 300);
        doAnimateClose(mItemButton2, 2, 5, 300);
        doAnimateClose(mItemButton3, 3, 5, 300);
        doAnimateClose(mItemButton4, 4, 5, 300);
        doAnimateClose(mItemButton5, 5, 5, 300);

    }

    /**
     *
     * @param view
     * @param index
     * @param total
     * @param radius
     */
    private void doAnimateClose(final View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total -1) * 2);
        int translationX = - (int) (radius * Math.sin(degree));
        int translationY = - (int) (radius * Math.cos(degree));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX",1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY",1f, 0.1f),
                ObjectAnimator.ofFloat(view, "alpha",1f, 0.1f)
        );

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
               /* view.setScaleX(1f);
                view.setScaleY(1f);
*/
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.setDuration(500).start();
    }

    private void openMenu() {
        doAnimateOpen(mItemButton1, 0,5,300);
        doAnimateOpen(mItemButton2, 1,5,300);
        doAnimateOpen(mItemButton3, 2,5,300);
        doAnimateOpen(mItemButton4, 3,5,300);
        doAnimateOpen(mItemButton5, 4,5,300);

    }

    /**
     * 用于实现每个按钮从初始位置移动到对应的结束位置
     * @param view
     * @param index
     * @param total
     * @param radius
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        /*获取按钮的角度*/
        double degree = Math.toRadians(90)/(total - 1) * index;
        int translationX = - (int) (radius * Math.sin(degree));
        int translationY = - (int) (radius * Math.cos(degree));

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX",0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY",0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha",0f, 1f)

        );
        animatorSet.setDuration(500).start();
    }
}
