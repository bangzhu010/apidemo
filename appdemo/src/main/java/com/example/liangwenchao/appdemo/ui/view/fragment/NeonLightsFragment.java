package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by admin on 2016/5/9.
 */
public class NeonLightsFragment extends BaseFragment {
    public final static String NEON_LIGHTS_FRAGMENT_TAG = "NeonLightsFragment";

    private final int[] viewIds = new int[]{
            R.id.view1,
            R.id.view2,
            R.id.view3,
            R.id.view4,
            R.id.view5,
            R.id.view6,
            R.id.view7
    };
    private final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6,
            R.color.color7
    };
    private View[] views = new View[viewIds.length];

    private int currentColor = 0;

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==START){
                for (int i = 0; i < viewIds.length; i++) {
                    views[i].setBackgroundResource(colors[(currentColor + i)%colors.length]);
                }
                currentColor++;
            }
        }
    };

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_neon_lights, null);
    }

    @Override
    public void initView(View view) {
        for (int i = 0; i < viewIds.length; i++) {
            views[i] = view.findViewById(viewIds[i]);
        }
    }
    public static final int START = 100;

    @Override
    public void initData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHander.sendEmptyMessage(START);
            }
        },0,1000);
//        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

    }
}
