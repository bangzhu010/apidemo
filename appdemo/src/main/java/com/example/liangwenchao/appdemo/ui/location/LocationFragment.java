package com.example.liangwenchao.appdemo.ui.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/4/21.
 */
public class LocationFragment extends BaseFragment implements View.OnClickListener {

    private Button btn_location;
    private TextView tv_position;

    private MapView mMapView;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 介绍如何使用个性化地图，需在MapView 构造前设置个性化地图文件路径
        // 注: 设置个性化地图，将会影响所有地图实例。
        // MapView.setCustomMapStylePath("个性化地图config绝对路径");
        super.onCreate(savedInstanceState);


        return inflater.inflate(R.layout.fragment_location, null);
    }

    @Override
    public void initView(View view) {
        btn_location = (Button) view.findViewById(R.id.btn_location);
        btn_location.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_location:
                startActivity(new Intent(getActivity(), BaiDuMapActivity.class));
                break;
        }
    }
}
