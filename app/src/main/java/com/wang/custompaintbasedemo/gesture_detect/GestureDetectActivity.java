package com.wang.custompaintbasedemo.gesture_detect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.custompaintbasedemo.R;


public class GestureDetectActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "GestureDetectActivity";
    private TextView mTextView;

    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown: " + true);


            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "onShowPress: ");


        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(TAG, "onSingleTapUp: " + true);

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: " + true);


            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(TAG, "onLongPress: ");


        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: " + true);


            return true;
        }
    };
    private GestureDetector.OnDoubleTapListener mDoubleTapListener = new GestureDetector.OnDoubleTapListener(){
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed: " + true);
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: "+ true);
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(TAG, "onDoubleTapEvent: "+ true);
            return false;
        }

    };

    private class MyGestureListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(TAG, "onDown: " +e.getAction()+"---");


            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(TAG, "onShowPress: " +e.getAction()+"---");


        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(TAG, "onSingleTapUp: " +e.getAction()+"---");

            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(TAG, "onScroll: " + e1.getAction()+"---" + e2.getAction()+"---");


            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(TAG, "onLongPress: " +e.getAction());


        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(TAG, "onFling: ");


            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i(TAG, "onSingleTapConfirmed: " +e.getAction()+"---" + true);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i(TAG, "onDoubleTap: " +e.getAction()+"---"+ true);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i(TAG, "onDoubleTapEvent: " +e.getAction()+"---"+ true);
            return true;
        }
    }

    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener(){
        final int diatance = 100, velocity = 200;

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            ///当滑动距离大于diatance， 速度大于velocity
            if (e1.getX() - e2.getX() > diatance && Math.abs(velocityX) > velocity){
                Log.i(TAG, "onFling: left");
                Toast.makeText(GestureDetectActivity.this, "onFling: left",Toast.LENGTH_SHORT).show();

            }else if (e2.getX() - e1.getX() > diatance && Math.abs(velocityX) > velocity){
                Log.i(TAG, "onFling: right");
                Toast.makeText(GestureDetectActivity.this, "onFling: right",Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
    private GestureDetector mGestureDetector = new GestureDetector( mSimpleOnGestureListener);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detect);
        mTextView = findViewById(R.id.text);
        mTextView.setOnTouchListener(this);
        mTextView.setClickable(true);
        mTextView.setFocusable(true);
        mTextView.setLongClickable(true);

        mTextView.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View v, MotionEvent event) {
                Log.i(TAG, "onGenericMotion: ");

                return false;
            }
        });
        //监听双击
//        mGestureDetector.setOnDoubleTapListener(mDoubleTapListener);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }


}
