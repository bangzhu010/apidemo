package com.example.liangwenchao.appdemo.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by admin on 2016/4/20.
 */
public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("lwc", "executed at " + new Date().toString());
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long anHour = 60 * 60 * 1000;

        long trigerAtTime = SystemClock.elapsedRealtime() + anHour;

        Intent i = new Intent(this,AlarmReceiver.class);

        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);

        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,trigerAtTime,pi);
//        manager.setExact();
        return super.onStartCommand(intent, flags, startId);
    }

    class AlarmReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Intent i = new Intent(context,LongRunningService.class);
            context.startService(i);
        }
    }
}
