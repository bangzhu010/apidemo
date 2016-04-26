package com.example.liangwenchao.appdemo.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by admin on 2016/4/20.
 */
public class ServiceActivity extends AppCompatActivity {

    private MyService.MyBinder myBinder;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);





        Intent intent = new Intent(this,MyService.class);


        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder)service;
                myBinder.startDownload();
                myBinder.getProgress();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

}
