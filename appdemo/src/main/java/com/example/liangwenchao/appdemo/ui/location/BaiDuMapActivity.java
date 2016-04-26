package com.example.liangwenchao.appdemo.ui.location;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;

/**
 * Created by admin on 2016/4/22.
 */
public class BaiDuMapActivity extends BaseActivity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    @Override
    public void setContentView() {
      setContentView(R.layout.activity_baidu_map);
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        // activity 暂停时同时暂停地图控件
        mMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // activity 恢复时同时恢复地图控件
        mMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // activity 销毁时同时销毁地图控件
        mMapView.onDestroy();
    }
}
