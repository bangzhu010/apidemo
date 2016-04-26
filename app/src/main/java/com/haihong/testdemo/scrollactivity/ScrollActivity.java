package com.haihong.testdemo.scrollactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.haihong.testdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> mArrayList;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
    }

    private void initView() {

        mListView = (ListView) findViewById(R.id.listview);

        mArrayList = new ArrayList<String>();

        for (int i = 0; i < 50; i++) {
            mArrayList.add("我是第" + i +"个条目");
        }
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,  mArrayList);

        mListView.setAdapter(mAdapter);

    }

}
