package com.wang.custompaintbasedemo.matrix_11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.wang.custompaintbasedemo.R;

public class MatrixActivity extends AppCompatActivity {

    private static final String TAG = "MatrixActivity";
    private ImageView mImageView;
    private SeekBar mSeekBar;
    private Bitmap mOriginBitmap, mTempBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        initView();
        initEvent();

    }
    private void initView() {
        mSeekBar = findViewById(R.id.mSeekBar);
        mImageView = findViewById(R.id.ivCity);
        mOriginBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        mTempBitmap = Bitmap.createBitmap(mOriginBitmap.getWidth(), mOriginBitmap.getHeight(), Bitmap.Config.ARGB_8888);

    }
    private void initEvent() {
        mSeekBar.setMax(99);
        mSeekBar.setProgress(1);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Bitmap bitmap = handleColorMatrixBmp(progress);
                mImageView.setImageBitmap(bitmap);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * 根据饱和度生成新的图像
     * @return
     */
    private Bitmap handleColorMatrixBmp(int progress) {
        //创建一个相同尺寸的位图区，去画调色后的图片
        Canvas canvas = new Canvas(mTempBitmap);
        Paint paint = new Paint();

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(progress);
        ///色彩放大绿色
//        saturationMatrix.setScale(1,1.3f,1,1);
        //色彩旋转,在-180至180之间旋转
//        saturationMatrix.setRotate(0, progress - 180);

        //设置色彩变换效果
        paint.setColorFilter(new ColorMatrixColorFilter(saturationMatrix));
        //将色彩变换后的图片输出到新创建的位图区
        canvas.drawBitmap(mOriginBitmap, 0,0, paint);
        //返回位图
        return mTempBitmap;
    }


}
