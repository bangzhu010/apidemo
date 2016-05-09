package com.example.liangwenchao.appdemo.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.DrawBallFragment;

/**
 * Created by admin on 2016/5/9.
 */
public class ViewFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView view_list;
    private BaseAdapter mAdapter;

    private String[] viewTitles = new String[]{
            "随手指移动的小球"
    };
    private String[] fragmentTags = new String[]{
            DrawBallFragment.DRAW_BALL_FRAGMENT_TAG
    };


    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view,null);
    }

    @Override
    public void initView(View view) {
        view_list = (ListView) view.findViewById(R.id.view_list);
        mAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,viewTitles);
        view_list.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        view_list.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),ViewContainerActivity.class);
        intent.putExtra("title",viewTitles[position]);
        intent.putExtra("fragmentTag", fragmentTags[position]);
        startActivity(intent);
    }
}
