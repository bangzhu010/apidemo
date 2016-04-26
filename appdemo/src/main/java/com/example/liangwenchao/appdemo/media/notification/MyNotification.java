package com.example.liangwenchao.appdemo.media.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by admin on 2016/4/18.
 */
public class MyNotification {

    public static void createNotification(Context context){
        //2015-12-25 11:50:32 修改为 Android5.0原生的通知特性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        //通知栏显示消息
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify((int) System.currentTimeMillis(), builder.build());

    }
}
