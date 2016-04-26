package com.example.liangwenchao.appdemo.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * Created by admin on 2016/4/25.
 */
public class SensorUtils {
    public static Sensor getSensor(Context context, int type) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        return manager.getDefaultSensor(type);
    }

    public static Sensor getLightSensor(Context context) {
        return getSensor(context, Sensor.TYPE_LIGHT);
    }
    public static Sensor getAccelerometerSensor(Context context){
        return getSensor(context,Sensor.TYPE_ACCELEROMETER);
    }
    public static Sensor getOrientationsensor(Context context){
        return getSensor(context,Sensor.TYPE_ORIENTATION);
    }

}
