package com.example.admin.weather.util.net;

/**
 * Created by admin on 2016/4/27.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
