package com.example.liangwenchao.appdemo.media.message;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by admin on 2016/4/18.
 */
public class MessageReceived extends BroadcastReceiver {
    //action: android.provider.Telephony.SMS_RECEIVED
    //permission android.permission.RECEIVE_SMS
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = getMessagesFromIntent(intent);
        String address = messages[0].getOriginatingAddress();
        String content = "";

        for (SmsMessage message :
                messages) {
            content += message.getMessageBody();
        }
//        abortBroadcast();//拦截广播

        SmsManager m = SmsManager.getDefault();

        Intent i = new Intent("SMS_STATs");
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);

        m.sendTextMessage("110",null,"给110发短信",pi,null);
    }

    private SmsMessage[] getMessagesFromIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("puds");//提取短信消息
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < messages.length; i++) {
            messages[i] =SmsMessage.createFromPdu((byte[]) pdus[i]);
        }
       return messages;
    }



}
