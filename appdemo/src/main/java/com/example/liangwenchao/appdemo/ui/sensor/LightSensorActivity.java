package com.example.liangwenchao.appdemo.ui.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;
import com.example.liangwenchao.appdemo.utils.SensorUtils;

import java.util.List;

/**
 * Created by admin on 2016/4/25.
 */
public class LightSensorActivity extends BaseActivity {
    private SensorManager manager;
    private Sensor lightSensor;
    private Sensor accSensor;
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float lightValue = event.values[0];
            tv_sensor_info.setText("current light values is " + lightValue + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            tv_sensor_acc.setText("current light values accuracy is " + accuracy);

        }
    };
    private SensorEventListener accListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float xValue = Math.abs(event.values[0]);
            float yValue = Math.abs(event.values[1]);
            float zValue = Math.abs(event.values[2]);

            tv_sensor_acc.setText("x = " + event.values[0] + "y = " + event.values[1]+"z = " + event.values[2]);

            if (xValue > 15 || yValue > 15 || zValue > 15) {
                Toast.makeText(LightSensorActivity.this,"摇一摇",Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private TextView tv_sensor_info;
    private TextView tv_sensor_acc;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_sensor);
    }

    @Override
    public void initView() {
        tv_sensor_info = (TextView) findViewById(R.id.tv_sensor_info);
        tv_sensor_acc = (TextView) findViewById(R.id.tv_sensor_acc);
    }

    @Override
    public void initData() {
        List<Sensor> sensors = manager.getSensorList(SensorManager.SENSOR_ALL);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = SensorUtils.getLightSensor(this);
        manager.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        accSensor = SensorUtils.getAccelerometerSensor(this);
        manager.registerListener(accListener,accSensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.unregisterListener(listener);
            manager.unregisterListener(accListener);
        }
    }

}
