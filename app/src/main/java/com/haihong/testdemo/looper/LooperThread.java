package com.haihong.testdemo.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


/**
 * Created by LiangWenchao on 2016/1/26.
 */
public class LooperThread extends Thread {

    private static final String TAG = "LooperThread";
    private Handler mHander;
    @Override
    public void run() {
        super.run();

        Looper.prepare();

        mHander = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG,"handlMessage ");
            }
        };

        mHander.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        Message msg = Message.obtain();
        mHander.sendMessage(msg);


        Looper.loop();
    }
}
