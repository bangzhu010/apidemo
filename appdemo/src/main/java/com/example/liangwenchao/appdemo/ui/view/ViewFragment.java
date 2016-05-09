package com.example.liangwenchao.appdemo.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;
import com.example.liangwenchao.appdemo.ui.view.activity.DrawBallViewActivity;

/**
 * Created by admin on 2016/3/11.
 */
public class ViewFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView viewListView;

    private String[] viewList = new String[]{
            "随手指移动的小球"
    };
    private Class[] activityClz = new Class[]{
            DrawBallViewActivity.class
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        float xdpi = getActivity().getResources().getDisplayMetrics().xdpi;
        float ydpi = getActivity().getResources().getDisplayMetrics().ydpi;

        return inflater.inflate(R.layout.fragment_view, null, false);

    }

    private BaseAdapter mAdaper;

    @Override
    public void initView(View view) {
        view.setFocusableInTouchMode(false);//设置防止焦点穿透
        viewListView = (ListView) view.findViewById(R.id.view_list);
        mAdaper = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, viewList);
        viewListView.setAdapter(mAdaper);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        viewListView.setOnItemClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getActivity(),activityClz[position]));
    }
}
