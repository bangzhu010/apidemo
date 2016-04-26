package com.example.liangwenchao.appdemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiangWenchao on 2016/4/12.
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        if(!activities.contains(activity)){
            activities.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAllActivity(){
        for (Activity activity :
                activities) {
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}

