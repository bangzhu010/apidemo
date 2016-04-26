package com.example.liangwenchao.appdemo.ui.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liangwenchao.appdemo.ui.base.IBaseInit;

/**
 * Created by admin on 2016/3/11.
 */
public interface IBaseInitFragment extends IBaseInit {


    /**
     * 填充试图
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    public View inflateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState);

    /**
     * 初始化View
     */
    public void initView(View view);
}
