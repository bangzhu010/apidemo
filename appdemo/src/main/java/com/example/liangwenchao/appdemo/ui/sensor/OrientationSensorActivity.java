package com.example.liangwenchao.appdemo.ui.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;

/**
 * Created by admin on 2016/4/25.
 */
public class OrientationSensorActivity extends BaseActivity {

    private Sensor accelerometerSensor;

    private Sensor magnetSensor;
    private SensorManager manager;

    private SensorEventListener listener = new SensorEventListener() {
        float[] accelerometerValues = new float[3];
        float[] magneticValues = new float[3];

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values.clone();
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticValues = event.values.clone();
            }

            float[] values = new float[3];
            float[] R = new float[9];

            SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
            SensorManager.getOrientation(R, values);

            Log.i("lwc", "Values[0] is " + Math.toDegrees(values[0]));
//            Log.i("lwc","Values[1] is " + Math.toDegrees(values[1]));
//            Log.i("lwc","Values[2] is " + Math.toDegrees(values[2]));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_orientation_sensor);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetSensor = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        manager.registerListener(listener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(listener, magnetSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (manager != null) {
            manager.unregisterListener(listener);
        }
    }
}
