package com.example.liangwenchao.appdemo.ui.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.broadcast.BroadCastTest;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;
import com.example.liangwenchao.appdemo.ui.view.activity._01CustomTitleViewActivity;
import com.example.liangwenchao.appdemo.ui.view.activity._02CuttomImageViewActivity;
import com.example.liangwenchao.appdemo.ui.view.activity._03CustomProgressActivity;

/**
 * Created by admin on 2016/3/11.
 */
public class ViewFragment extends BaseFragment implements View.OnClickListener {
    private int index = 0;

    private Button btn_custom_title_view;
    private Button btn_custom_iamge_view;
    private Button btn_custom_progress;

    private IntentFilter intentFilter;
    private BroadCastTest broadCastTest;
    private LocalBroadcastManager manager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        manager = LocalBroadcastManager.getInstance(getActivity());

        intentFilter = new IntentFilter();

        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANNGE");

        broadCastTest = new BroadCastTest();

        manager.registerReceiver(broadCastTest,intentFilter);


        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        float xdpi = getActivity().getResources().getDisplayMetrics().xdpi;
        float ydpi = getActivity().getResources().getDisplayMetrics().ydpi;

        Log.i("lwc","xdpi = " + xdpi);
        Log.i("lwc","ydpi = " + ydpi);

        return inflater.inflate(R.layout.fragment_view,null,false);

    }

    @Override
    public void initView(View view) {
        view.setFocusableInTouchMode(false);//设置防止焦点穿透
        btn_custom_title_view = (Button) view.findViewById(R.id.btn_custom_title_view);
        btn_custom_iamge_view = (Button) view.findViewById(R.id.btn_custom_iamge_view);
        btn_custom_progress = (Button) view.findViewById(R.id.btn_custom_progress);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btn_custom_title_view.setOnClickListener(this);
        btn_custom_iamge_view.setOnClickListener(this);
        btn_custom_progress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_custom_title_view:
                Intent intent = new Intent(getActivity(), _01CustomTitleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_custom_iamge_view:
                startActivity(new Intent(getActivity(),_02CuttomImageViewActivity.class));
                break;
            case R.id.btn_custom_progress:
                startActivity(new Intent(getActivity(), _03CustomProgressActivity.class));

//                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
//                BitmapDrawable draw = (BitmapDrawable) getActivity().getResources().getDrawable(R.drawable.icon_logol);
//                Bitmap m = draw.getBitmap();
//                builder.setContentTitle(getActivity().getResources().getString(R.string.app_name))
//                        .setLargeIcon(m)
//                                //NotificationCompat.Builder实例化我们的通知后，最终需要将各种图标，参数配置，应用到通知视图上面。可以看到如果我们只设置smallIcon而不设置largeIcon也是可以的，此时直接将small作为大图标设置给左侧的id为R.id.icon的ImageView。要注意的事一般情况下都不可以不设置smallIcon，否则通知无法正常显示出来。
//                                // processSmallIconAsLarge方法里面负责将我们设置的smallIcon二次处理，也就是这里会改变我们最终看到的通知图标，包括顶部状态栏和下拉显示的小图标。
//                                //操作会导致通知的图标统统变白色。
//                        .setContentText("1233444")
////				    .setNumber(number)//显示数量
////                .setTicker(appMessage.getAlert())//通知首次出现在通知栏，带上升动画效果的
//                        .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
//                        .setPriority(Notification.PRIORITY_MAX)//设置该通知优先级
//                        .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
//                        .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
//                        .setDefaults(Notification.DEFAULT_ALL)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
//                                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
//                        .setSmallIcon(R.drawable.icon_white_144_2)
//                        .setVisibility(Notification.VISIBILITY_PRIVATE)//设置通知在锁屏界面显示的内容5.0
////                .setContentInfo("内容信息")
//                        .setPriority(Notification.PRIORITY_MAX);
//
//                NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//                mNotificationManager.notify((int) System.currentTimeMillis(), builder.build());


                break;
        }
    }

    @Override
    public void onDestroyView() {

        manager.unregisterReceiver(broadCastTest);
        super.onDestroyView();


    }
}
