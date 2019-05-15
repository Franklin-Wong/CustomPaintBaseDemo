package com.wang.custompaintbasedemo.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.wang.custompaintbasedemo.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 内存占用计算
 */
public class BitmapFactoryActivity extends AppCompatActivity {

    private static final String TAG = "BitmapFactoryActivity";
    private ImageView mImageView;

    private Bitmap mFdBitmap;
    private Object mFileBitmap;
    private Bitmap resourcesBitmap;
    private Bitmap netBitmap;

    private String path = "http://pic28.photophoto.cn/20130818/0020033143720852_b.jpg";
//    private String path = "http://www.photophoto.cn/pic/04544607.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_factory);
        mImageView = findViewById(R.id.image);

        resourcesBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Drawable drawable = new CustomDrawable(resourcesBitmap);



//        getByteArrayBitmap();
        getInputStreamBitmap();

        getFileDescriptorBitmap();

//        getOptions(null, 100,100);

//        inScaleParam();

//        inDensityParam();

        inPreferredConfigParam();
    }

    private void inPreferredConfigParam() {

        //默认设置ARGB_8888
        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Log.i(TAG, "inPreferredConfigParam: bitmapSrc="+ bitmapSrc.getWidth() +"--realHeight="+bitmapSrc.getHeight() +"---内存="+ bitmapSrc.getByteCount());
        //RGB_565,减少一半内存，适合不透明的、不需要彩色高度保真的
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap565 = BitmapFactory.decodeResource(getResources(), R.mipmap.city, options);
        Log.i(TAG, "inPreferredConfigParam: bitmap565="+ bitmap565.getWidth() +"--realHeight="+bitmap565.getHeight() +"---内存="+ bitmap565.getByteCount());


    }

    private void inDensityParam() {
        //内存占用增加到4倍
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDensity = 1;
        options.inTargetDensity = 2;
        //从资源目录加载
//        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.city, options);
//        Log.i(TAG, "inDensityParam: bitmapSrc="+ bitmapSrc.getWidth() +"--realHeight="+bitmapSrc.getHeight() +"---内存="+ bitmapSrc.getByteCount());

        //获取SD卡文件,并生成bitmap
        File file = Environment.getExternalStorageDirectory();
        String path = file.getAbsolutePath() + "/data/city.jpg";
        Bitmap bitmapSD = BitmapFactory.decodeFile(path, options);

        Log.i(TAG, "inDensityParam: path="+path);
        Log.i(TAG, "inDensityParam: bitmapSD="+ bitmapSD.getWidth() +"--realHeight="+bitmapSD.getHeight() +"---内存="+ bitmapSD.getByteCount());


    }

    private void inScaleParam() {
        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Log.i(TAG, "inScaleParam: bitmapSrc="+ bitmapSrc.getWidth() +"--realHeight="+bitmapSrc.getHeight() +"---内存="+ bitmapSrc.getByteCount());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap noScaleBmpSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.city, options);
        Log.i(TAG, "inScaleParam: noScaleBmpSrc="+ noScaleBmpSrc.getWidth() +"--realHeight="+noScaleBmpSrc.getHeight() +"---内存="+ noScaleBmpSrc.getByteCount());



    }

    private void getOptions(BitmapFactory.Options option, int dstWidth, int dstHeight ) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Log.i(TAG, "getOptions: bitmapSrc="+ bitmapSrc.getWidth() +"--realHeight="+bitmapSrc.getHeight() +"---内存="+ bitmapSrc.getByteCount());


        //计算宽高,并计算需要压缩的比例
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;
        int inSampleSize = 1;

        if (rawWidth > dstWidth || rawHeight > dstHeight){
            float ratioWidth = rawWidth / dstWidth;
            float ratioHeight = rawHeight/dstHeight;
            inSampleSize = (int) Math.min(ratioWidth, ratioHeight);
        }

        //设置压缩后的图片
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = inSampleSize;

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.city, options2);

        mImageView.setImageBitmap(bitmap2);
        Log.i(TAG, "getOptions: bitmapSrc ="+ bitmapSrc);

        Log.i(TAG, "getOptions: realWidth="+ options.outWidth +"--realHeight="+options.outHeight +"---mimeType="+ options.outMimeType);

        //获取SD卡文件,并生成bitmap
        File file = Environment.getExternalStorageDirectory();
        String path = file.getAbsolutePath() + "/data/city.jpg";
        Bitmap bitmapSD = BitmapFactory.decodeFile(path);

        Log.i(TAG, "getOptions: path="+path);
        Log.i(TAG, "getOptions: bitmapSD="+ bitmapSD.getWidth() +"--realHeight="+bitmapSD.getHeight() +"---内存="+ bitmapSD.getByteCount());


    }

    private void getInputStreamBitmap() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {     //请求网络资源

                    InputStream in = getInputStream(path);

                    netBitmap = BitmapFactory.decodeStream(in);

                    mImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            mImageView.setImageBitmap(netBitmap);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private InputStream getInputStream(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(6000);
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200){
            InputStream inputStream = connection.getInputStream();
            return inputStream;
        }
        return null;
    }
    /**
     * decodeFileDescriptor 比 decodeFile节省内存
     * @return
     */
    private Object getFileDescriptorBitmap() {
        String path = "data/data/";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);

            FileDescriptor fd = fileInputStream.getFD();

           mFdBitmap = BitmapFactory.decodeFileDescriptor(fd);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mFileBitmap;
    }


    private void getByteArrayBitmap() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {     //请求网络资源
                    byte[] data = new byte[0];

                    data = getImage(path);

                    int length = data.length;
                    netBitmap = BitmapFactory.decodeByteArray(data, 0, length);

///                    WallpaperManager.getInstance(BitmapActivity.this).setBitmap(netBitmap);

                    mImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            mImageView.setImageBitmap(netBitmap);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    private byte[] getImage(String path) throws Exception{
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(6000);
            InputStream in = null;
            //判断请求结果
            if (connection.getResponseCode() == 200){
                in = connection.getInputStream();
                //转换输入流对象
                byte[] result = readStream(in);
                in.close();
                return result;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 读取输入流,并转换输入流对象
     * @param in
     * @return
     */
    private byte[] readStream(InputStream in) {
        //创建输出流对象
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;

        try {
            while ((len = in.read(buffer)) != -1){
                //写入输出流
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }


}
