package com.wang.custompaintbasedemo.bitmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapCreateActivity extends AppCompatActivity {

    private static final String TAG = "BitmapCreateActivity";
    private ImageView mImageView, mImageView2;
    private Bitmap mResourceBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_create);
        mImageView = findViewById(R.id.imageCut);
        mImageView2 = findViewById(R.id.imageCut2);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

/**
 * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
 */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }

        //图片剪裁
        getBitmapCut();

        getScaledBitmap();

        getExtractAlpha();

        getExtractAlphaMethod();

//        density();

//        pixel();

        compress();

        saveBmp();

//        getBitmap();

        getWaterBitmap();
    }

    private void getWaterBitmap() {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.mipmap.fire_human);
        Bitmap ic = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Bitmap waterBitmap = createWaterBitmap(src, ic);
        mImageView.setImageBitmap(waterBitmap);


    }

    private Bitmap createWaterBitmap(Bitmap src, Bitmap waterMark) {

        if (src == null){
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int ww = waterMark.getWidth();
        int wh = waterMark.getHeight();

        Bitmap newSrc = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(newSrc);
        //在newSrc上面，画
        c.drawBitmap(src, 0,0, null);
        c.drawBitmap(waterMark, w / 2, h / 2, null);
//        c.drawBitmap(waterMark, w - ww +5, h - wh + 5, null);

        return newSrc;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {

            }
        }
    }
    private void getBitmap() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/photo.webp";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        mImageView.setImageBitmap(bitmap);
        Log.i(TAG, "getBitmap: "+ bitmap + "---path="+path);

    }

    /**
     * 保存到
     */
    private void saveBmp() {
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.fire_human);
        File directory = Environment.getExternalStorageDirectory();
        //创建路径
        String path = directory.getAbsolutePath() + "/photo.webp";
        Log.i(TAG, "saveBmp: path="+path);
        //创建路径对应的文件
        File file = new File(path);
        //生成压缩资源的输出流，保存在文件中
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            mResourceBmp.compress(Bitmap.CompressFormat.WEBP, 10, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 压缩位图
     */
    private void compress() {
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mImageView.setImageBitmap(mResourceBmp);

        //压缩图像
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mResourceBmp.compress(Bitmap.CompressFormat.JPEG, 1, stream);
        byte[] bytes = stream.toByteArray();
        Bitmap compressedBmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        mImageView2.setImageBitmap(compressedBmp);


    }

    /**
     * 产生滤镜效果
     */
    private void pixel() {
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mImageView.setImageBitmap(mResourceBmp);
        //复制一个可改变像素的位图对象
        Bitmap desBmp = mResourceBmp.copy(Bitmap.Config.ARGB_8888, true);
        for (int h = 0; h < mResourceBmp.getHeight(); h++){
            for (int w = 0; w< mResourceBmp.getWidth(); w++){
                //Returns the {@link Color} at the specified location.
                int pixel = mResourceBmp.getPixel(w, h);
                //Return the red component of a color int.
                int red = Color.red(pixel);
                int alpha = Color.alpha(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                //只针对当green小于200的通道+30
                if (green < 200){
                    green +=30;
                }
                //将更改的颜色重新设置回去
                desBmp.setPixel(w, h, Color.argb(alpha, red, green, blue));
            }
        }
        mImageView2.setImageBitmap(desBmp);


    }

    public void density(){
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mImageView.setImageBitmap(mResourceBmp);

        int density = mResourceBmp.getDensity();
        Log.i(TAG, "density="+ density +"---width="+ mResourceBmp.getWidth() +"--realHeight="+ mResourceBmp.getHeight() +"---内存="+ mResourceBmp.getByteCount());

        int scaleDensity = density * 2;
        mResourceBmp.setDensity(scaleDensity);
        Log.i(TAG, "scaleDensity="+ scaleDensity +"---width="+ mResourceBmp.getWidth() +"--realHeight="+ mResourceBmp.getHeight() +"---内存="+ mResourceBmp.getByteCount());

        mImageView2.setImageBitmap(mResourceBmp);
    }
    /**
     *
     * @param bitmap
     * @return
     */
    private int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){
            return bitmap.getByteCount();
        }
        //
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    private void getExtractAlphaMethod() {
        Paint alphaPaint = new Paint();
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(6, BlurMaskFilter.Blur.NORMAL);
        alphaPaint.setMaskFilter(blurMaskFilter);
        int[] offsetsXY = new int[2];
        //提取透明位图
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Bitmap alphaBitmap = mResourceBmp.extractAlpha(alphaPaint, offsetsXY);
        //创建与alphaBitmap相同尺寸的bitmap
        Bitmap bitmap = Bitmap.createBitmap(alphaBitmap.getWidth(), alphaBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint p = new Paint();
        p.setColor(Color.CYAN);
        canvas.drawBitmap(alphaBitmap, 0,0, p);
        //绘制源图像
        canvas.drawBitmap(mResourceBmp, -offsetsXY[0], -offsetsXY[1], null);
        //设置图片，并回收资源
        mImageView.setImageBitmap(bitmap);
        mResourceBmp.recycle();
        Log.i(TAG, "getExtractAlphaMethod: bitmap="+ bitmap.getWidth() +"--realHeight="+bitmap.getHeight() +"---内存="+ bitmap.getByteCount());

    }

    private void getExtractAlpha() {
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Bitmap bitmap = Bitmap.createBitmap(mResourceBmp.getWidth(), mResourceBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.CYAN);

        canvas.drawBitmap(mResourceBmp.extractAlpha(), 0, 0, paint);

//        mImageView.setImageBitmap(bitmap);
        mResourceBmp.recycle();
    }

    private void getScaledBitmap() {
        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(mResourceBmp, 300, 200, true);
        Bitmap copyBmp = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true);
//        mImageView.setImageBitmap(copyBmp);
        mResourceBmp.recycle();
    }

    private void getBitmapCut() {
        //设置矩阵
        Matrix matrix = new Matrix();
        matrix.setScale(2,1);

        mResourceBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Bitmap cutBmp = Bitmap.createBitmap(mResourceBmp, mResourceBmp.getWidth() / 3, mResourceBmp.getHeight() / 3,
                mResourceBmp.getWidth() / 3, mResourceBmp.getHeight() / 3, matrix, true);
//        mImageView.setImageBitmap(cutBmp);
        mResourceBmp.recycle();
        Log.i(TAG, "getBitmapCut: cutBmp="+ cutBmp.getWidth() +"--realHeight="+cutBmp.getHeight() +"---内存="+ cutBmp.getByteCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mResourceBmp != null && !mResourceBmp.isRecycled()){
            mResourceBmp.recycle();
            mResourceBmp = null;
            System.gc();
        }
    }
}
