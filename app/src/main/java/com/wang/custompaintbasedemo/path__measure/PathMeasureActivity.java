package com.wang.custompaintbasedemo.path__measure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wang.custompaintbasedemo.R;

/**
 *
 */
public class PathMeasureActivity extends AppCompatActivity {

    private PathView mPathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_measure);

        findViewById(R.id.pathView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PathMeasureActivity.this, SVGActivity.class));
            }
        });


    }
}
