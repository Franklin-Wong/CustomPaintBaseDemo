package com.wang.custompaintbasedemo.gesture_detect;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wang.custompaintbasedemo.R;

import java.util.ArrayList;

import static android.graphics.PixelFormat.TRANSLUCENT;

public class RocketActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "RocketActivity";
    private Button mAddWndBtn, mRemoveWndBtn;
    private ImageView mImageView;
    private WindowManager.LayoutParams mLayoutParams;
    private WindowManager mWindowManager;

    private ArrayList<ImageView> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocket);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            startActivityForResult(intent, 100);
        }else {
            initView();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            initView();
        }

    }

    private void initView() {
        mAddWndBtn = findViewById(R.id.addBtn);
        mRemoveWndBtn = findViewById( R.id.removeBtn);
        mAddWndBtn.setOnClickListener(this);
        mRemoveWndBtn.setOnClickListener(this);

        mWindowManager = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                mImageView = new ImageView(this);
                mImageView.setImageResource(R.mipmap.ic_launcher);

                mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT, 2099,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED,
                        TRANSLUCENT);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                }else {
                    mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;

                }
                mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
                mLayoutParams.x = 0;
                mLayoutParams.y = 0;

                mImageView.setOnTouchListener(this);
                mWindowManager.addView(mImageView, mLayoutParams);
                Log.i(TAG, "onClick: x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);

                views.add(mImageView);

                break;
            case R.id.removeBtn:
                if (views != null && views.size() > 0){
                    Log.i(TAG, "onClick: views="+views.size());
                        mWindowManager.removeViewImmediate(views.get(0));
                        views.remove(0);
                }
                ///
//                mWindowManager.removeViewImmediate(mImageView);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ///当触摸事件到来，控件随手指移动
        //获取触摸点坐标
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();

        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                //实时更新坐标位置
                mLayoutParams.x = rawX;
                mLayoutParams.y = rawY;
                mWindowManager.updateViewLayout(mImageView, mLayoutParams);
                Log.i(TAG, "onTouch_ACTION_MOVE: x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                Toast.makeText(RocketActivity.this, "点击成功",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onTouch_ACTION_BUTTON_PRESS:x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);
                break;
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouch_ACTION_DOWN:x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);
                break;
            case MotionEvent.ACTION_UP:
                //发射火箭

//                mLayoutParams.x = 0;
//                mLayoutParams.y = 0;
//                mWindowManager.updateViewLayout(mImageView, mLayoutParams);
                Toast.makeText(RocketActivity.this, "发射火箭",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onTouch_ACTION_UP:x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);
                break;

            case MotionEvent.ACTION_BUTTON_RELEASE:
                Log.i(TAG, "onTouch_ACTION_BUTTON_RELEASE:x ="+ mLayoutParams.x +"---y="+mLayoutParams.y);
                break;
            default:
                break;

        }

        return false;
    }

}











