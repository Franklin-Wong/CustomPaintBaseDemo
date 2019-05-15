package com.wang.custompaintbasedemo.property_advance_anim.keyframe.propertyvaluesholder;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wang.custompaintbasedemo.R;

/**
 *
 */
public class PropertyAnimAdvanceActivity extends AppCompatActivity {
    private static final String TAG = "PropertyAnimAdvanceActivity";
    private CustomCharacterTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim_advance);
        mTextView = findViewById(R.id.tvAnim);

        initAnimator();


    }

    private void initAnimator() {
        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofFloat("rotation",
                60f, -60f, 40f, -40f, -20f, 20f, 10f, -10f, 0f);

        PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0.1f, 1f, 0.1f, 1f);

        PropertyValuesHolder charHolder = PropertyValuesHolder.ofObject("CharText",
                new CharEvaluator(),'A', 'Z');

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTextView, charHolder, rotationHolder, alphaHolder);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTextView, rotationHolder, alphaHolder);

        objectAnimator.setDuration(13000);
        objectAnimator.start();


    }
}
