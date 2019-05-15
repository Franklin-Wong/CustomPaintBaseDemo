package com.wang.custompaintbasedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by Wongerfeng on 2018/10/9.
 */

public class ScannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        ///硬件加速禁止
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        final ImageView icon1 = findViewById(R.id.icon1);
        final ImageView icon2 = findViewById(R.id.icon2);
        final ImageView icon3 = findViewById(R.id.icon3);
        final ImageView icon4 = findViewById(R.id.icon4);

        final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);
        final Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);
        final Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim);
        findViewById(R.id.startScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icon1.startAnimation(animation1);

                animation2.setStartOffset(600);
                icon2.startAnimation(animation2);

                animation3.setStartOffset(1200);
                icon3.startAnimation(animation3);

                animation4.setStartOffset(1800);
                icon4.startAnimation(animation4);

            }
        });

    }
}
