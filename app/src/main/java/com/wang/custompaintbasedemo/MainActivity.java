package com.wang.custompaintbasedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.wang.custompaintbasedemo.animator_set.MenuChooseActivity;
import com.wang.custompaintbasedemo.bitmap.BitmapActivity;
import com.wang.custompaintbasedemo.bitmap.BitmapCreateActivity;
import com.wang.custompaintbasedemo.bitmap.BitmapFactoryActivity;
import com.wang.custompaintbasedemo.bitmap.TelescopeActivity;
import com.wang.custompaintbasedemo.bitmap.surface.SurfaceActivity;
import com.wang.custompaintbasedemo.canvas_base.CanvasActivity;
import com.wang.custompaintbasedemo.custom_style.CustomStyleActivity;
import com.wang.custompaintbasedemo.custom_style.FlowActivity;
import com.wang.custompaintbasedemo.gesture_detect.GestureDetectActivity;
import com.wang.custompaintbasedemo.gesture_detect.RocketActivity;
import com.wang.custompaintbasedemo.matrix_11.MatrixActivity;
import com.wang.custompaintbasedemo.mix_mode.XfermodeActivity;
import com.wang.custompaintbasedemo.object_animator.ObjectAnimatorActivity;
import com.wang.custompaintbasedemo.painting_base.PaintBaseActivity;
import com.wang.custompaintbasedemo.path__measure.PathMeasureActivity;
import com.wang.custompaintbasedemo.path__measure.SVGActivity;
import com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder.KeyFrameActivity;
import com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder.PropertyAnimAdvanceActivity;
import com.wang.custompaintbasedemo.viewgroup_animator.ViewGroupAnimActivity;

import java.util.zip.GZIPInputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btStartCameraStretc,btStartScanner;
    private Button btStartObjectAnimator;

    private ZipFile mZipFile;
    private ZipInputStream mZipInputStream;
    private GZIPInputStream mGZIPInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///硬件加速禁止
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        btStartObjectAnimator = findViewById(R.id.btStartObjectAnimatorActivity);
        btStartCameraStretc = findViewById(R.id.startCameraStretchActivity);
        btStartScanner = findViewById(R.id.startScannerActivity);

        btStartScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScannerActivity.class));

            }
        });
        btStartCameraStretc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CameraStretchActivity.class));
            }
        });

        btStartObjectAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ObjectAnimatorActivity.class));
            }
        });
        findViewById(R.id.btStartMenuChooseActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MenuChooseActivity.class));
            }
        });

        findViewById(R.id.btStartKeyFrameActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KeyFrameActivity.class));
            }
        });
        findViewById(R.id.btStartPropertyAnimAdvanceActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PropertyAnimAdvanceActivity.class));
            }
        });
        findViewById(R.id.btStartPathMeasureActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PathMeasureActivity.class));
            }
        });
        findViewById(R.id.btStartViewGroupAnimActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewGroupAnimActivity.class));
            }
        });
        findViewById(R.id.btStartPaintBaseActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PaintBaseActivity.class));
            }
        });
        findViewById(R.id.btSVGActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SVGActivity.class));
            }
        });
        findViewById(R.id.btStartXfermodeActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, XfermodeActivity.class));
            }
        });
        findViewById(R.id.btStartCanvasActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
            }
        });
        findViewById(R.id.btStartBitmapActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BitmapActivity.class));
            }
        });

        findViewById(R.id.btStartTelescopeActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TelescopeActivity.class));
            }
        });
        findViewById(R.id.btStartBitmapFactoryActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BitmapFactoryActivity.class));
            }
        });
        findViewById(R.id.btStartBitmapCreateActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BitmapCreateActivity.class));
            }
        });
        findViewById(R.id.btStartSurfaceActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SurfaceActivity.class));
            }
        });
        findViewById(R.id.btStartGestureDetectActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GestureDetectActivity.class));
            }
        });
        findViewById(R.id.btStartRocketActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RocketActivity.class));
            }
        });
        findViewById(R.id.btStartCustomStyleActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomStyleActivity.class));
            }
        });
        findViewById(R.id.btStartMatrixActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MatrixActivity.class));
            }
        });

        findViewById(R.id.btStartFlowActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlowActivity.class));
            }
        });


//        ImageView image = findViewById(R.id.image);
//        Glide.with(this)
//                .load("")
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .placeholder(R.mipmap.rain_man)
//                .thumbnail(0.5f)
//                .into(image);






    }


}
