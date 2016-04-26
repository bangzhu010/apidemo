package com.example.liangwenchao.appdemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by admin on 2016/4/22.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }
}
