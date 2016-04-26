package com.haihong.testdemo.looper;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/**
 * Created by Administrator on 2016/1/27.
 */
public class MessengerService extends Service {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };

    final private Messenger messenger = new Messenger(mHandler);


    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

}
