package com.wang.custompaintbasedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Wongerfeng on 2018/10/9.
 */

public class CameraStretchActivity extends AppCompatActivity {


    private ImageView mImageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_stretch_activity);
        mImageView = findViewById(R.id.img);
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);
        mImageView.startAnimation(scaleAnimation);

    }
}
