package com.haihong.testdemo.looper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

/**
 * Created by LiangWenchao on 2016/1/27.
 */
public class MessengerActivity extends Activity {
    Messenger messenger;
    View view;

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this,MessengerService.class),mServiceCon, Context.BIND_AUTO_CREATE);
    }
    ServiceConnection mServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Message message= Message.obtain();
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
