package com.example.liangwenchao.appdemo.ui.view;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;

/**
 * Created by admin on 2016/5/9.
 */
public class ViewContainerActivity extends BaseActivity {

    private int index = -1;
    @Override
    public void setContentView() {
        setContentView(R.layout.activity_view_container);
    }

    @Override
    public void initData() {
        super.initData();
        index = getIntent().getIntExtra("index",0);

    }

    @Override
    public void initView() {

    }
}
