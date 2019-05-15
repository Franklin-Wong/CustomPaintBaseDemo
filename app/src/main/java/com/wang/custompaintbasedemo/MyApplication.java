package com.wang.custompaintbasedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Wongerfeng on 2018/12/17.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i(TAG, "onActivityCreated: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivityStarted(Activity activity) {
                Log.i(TAG, "onActivityStarted: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivityResumed(Activity activity) {
                Log.i(TAG, "onActivityResumed: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivityPaused(Activity activity) {
                Log.i(TAG, "onActivityPaused: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivityStopped(Activity activity) {
                Log.i(TAG, "onActivityStopped: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.i(TAG, "onActivitySaveInstanceState: "+activity.getClass().getSimpleName());
            }
            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.i(TAG, "onActivityDestroyed: "+activity.getClass().getSimpleName());
            }
        });
    }




}
