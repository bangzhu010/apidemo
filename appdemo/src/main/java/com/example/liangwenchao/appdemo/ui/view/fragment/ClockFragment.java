package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/11.
 */
public class ClockFragment extends BaseFragment {
    public static final String CLOCK_FRAGMENT_TAG = "ClockFragment";

    private AnalogClock analogClock;

    private Chronometer chronometer;
    private Button start;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clock, null);
    }

    @Override
    public void initView(View view) {
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);
        start = (Button) view.findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                start.setEnabled(false);
            }
        });
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime()-chronometer.getBase() >20*1000){
                    chronometer.stop();
                    start.setEnabled(true);
                }
            }
        });

    }

    @Override
    public void initData() {

    }
}
