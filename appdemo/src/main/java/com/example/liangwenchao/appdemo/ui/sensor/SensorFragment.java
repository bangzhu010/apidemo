package com.example.liangwenchao.appdemo.ui.sensor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/4/22.
 */
public class SensorFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView sensorListView;
    private SensorAdapter mAdapter;
    private static final SensorInfo[] sensors = new SensorInfo[]{
            new SensorInfo("light Sensor", LightSensorActivity.class),
            new SensorInfo("orientation sensor",OrientationSensorActivity.class)
    };
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sensor, null);
    }

    @Override
    public void initView(View view) {
        sensorListView = (ListView) view.findViewById(R.id.lv_sensor);
        mAdapter = new SensorAdapter();
        sensorListView.setAdapter(mAdapter);
        sensorListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(),sensors[position].clz));
    }

    class SensorAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return sensors.length;
        }

        @Override
        public Object getItem(int position) {
            return sensors[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView item;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.sensor_listview_item, null);
                AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                convertView.setLayoutParams(params);
            }
            item = (TextView) convertView.findViewById(R.id.sensors_item);
            item.setText(sensors[position].name);
            item.setTextColor(Color.RED);

            return convertView;
        }
    }


}
