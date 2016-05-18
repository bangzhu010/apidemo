package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/11.
 */
public class QuickContactBadageFragment extends BaseFragment {
    public static final String QUICK_CONTACT_BADAGE_TAG = "QuickContactBadageFragment";
    private QuickContactBadge quickContactBadge;
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quick_contact_badage,null);
    }

    @Override
    public void initView(View view) {
        quickContactBadge = (QuickContactBadge)view.findViewById(R.id.quick_contact_badge);
        quickContactBadge.assignContactFromPhone("18612197087",true);
    }

    @Override
    public void initData() {

    }
}
