package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/24.
 */
public class AdapterViewFragment extends BaseFragment{
    public static final String ADAPTER_VIEW_FRAGMENT_TAG = "AdapterViewFragment";
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auto_complete_textview,null);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
