package com.wang.custompaintbasedemo.path__measure;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

public class SVGActivity extends AppCompatActivity {

    private ImageView mImageView;

    static {
        ///兼容性
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);

        mImageView = findViewById(R.id.image);
        mImageView.setImageResource(R.drawable.svg_view);

        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(SVGActivity.this, R.drawable.animated_vector);
        mImageView.setImageDrawable(animatedVectorDrawableCompat);
        ((Animatable)mImageView.getDrawable()).start();

        initSearchBar();



    }

    private void initSearchBar() {
        final ImageView imageVIew = findViewById(R.id.anim_img);
        imageVIew.setFocusable(true);
        imageVIew.setFocusableInTouchMode(true);
        imageVIew.requestFocus();
        imageVIew.requestFocusFromTouch();

        findViewById(R.id.edit_query).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(
                            SVGActivity.this, R.drawable.animated_vector_search
                    );
                    imageVIew.setImageDrawable(animatedVectorDrawableCompat);
                    ((Animatable)imageVIew.getDrawable()).start();
                }
            }
        });

    }
}
