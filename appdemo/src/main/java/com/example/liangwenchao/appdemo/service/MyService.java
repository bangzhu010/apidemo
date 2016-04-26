package com.example.liangwenchao.appdemo.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by admin on 2016/4/19.
 */
public class MyService extends Service {

    private MyBinder myBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(0);
        builder.setContentTitle("通知题目");
        builder.setContentText("通知内容");
        builder.setContentInfo("11112222");
        Notification notification = builder.build();

        startForeground(0,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class MyBinder extends Binder {

        public void startDownload() {

            Log.i("lwc","startDownload");
        }

        public int getProgress() {
            Log.i("lwc","getProgress");
            return 0;
        }

    }
}
