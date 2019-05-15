package com.wang.custompaintbasedemo.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wang.custompaintbasedemo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 *
 */
public class BitmapActivity extends AppCompatActivity {
    private static final String TAG = "BitmapActivity";
    private TextView mTextView;
    private ImageView mImageView;
    private TextView mButton;
    private Bitmap bitmap;
    private Bitmap netBitmap;

    private String path = "http://pic28.photophoto.cn/20130818/0020033143720852_b.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mTextView = findViewById(R.id.tvShape);
        mButton = findViewById(R.id.addShapeCorner);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = (GradientDrawable) mTextView.getBackground();
                drawable.setCornerRadius(20);

            }
        });

        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.city);
        Drawable drawable = new CustomDrawable(bitmap);

        mButton.setBackground(drawable);

        mImageView = findViewById(R.id.image);
//        mImageView.setImageDrawable(drawable);

        requestImageFromServer();


    }

    private void requestImageFromServer() {

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
