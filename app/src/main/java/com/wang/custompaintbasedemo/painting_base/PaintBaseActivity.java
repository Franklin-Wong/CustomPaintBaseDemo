package com.wang.custompaintbasedemo.painting_base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wang.custompaintbasedemo.R;

/**
 *@author W
 */
public class PaintBaseActivity extends AppCompatActivity {
    private static final String TAG = "PaintBaseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_base);


    }
}
