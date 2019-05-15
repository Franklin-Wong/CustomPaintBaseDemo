package com.wang.custompaintbasedemo.frames;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

/**
 * Created by Wongerfeng on 2018/10/9.
 */

public class FrameXMLActivity extends AppCompatActivity {

    private ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_anim_layout);

        image = findViewById(R.id.frame_image);
        AnimationDrawable anim = (AnimationDrawable) image.getBackground();
        anim.start();

//        anim.stop();

        ImageView imageView = addFrames();


    }

    private ImageView addFrames() {
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 0;i <= 10;i++){
            int id = getResources().getIdentifier("list_icon_gif" + i, "drawable", getPackageName());
            Drawable drawable = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                drawable = getResources().getDrawable(id, null);
            }
            anim.addFrame(drawable, 60);

        }

        anim.setOneShot(false);
        image.setBackground(anim);

        return image;

    }
}
