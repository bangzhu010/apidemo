package com.example.liangwenchao.appdemo.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;

import com.example.liangwenchao.appdemo.utils.ActivityCollector;

/**
 * Created by admin on 2016/4/14.
 */
public class BroadCastTest extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("下线通知");
        builder.setMessage("被顶下线，请重新登录");
        //防止back键取消
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ActivityCollector.finishAllActivity();

                Intent intent = new Intent();

                //广播接收器里启动Activity，必须加这句
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                dialog.dismiss();

            }
        });
        dialog.show();

    }
}
