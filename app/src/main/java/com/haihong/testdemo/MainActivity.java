package com.haihong.testdemo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.haihong.testdemo.scrollactivity.ScrollActivity;
import com.haihong.testdemo.view.scroll.MyScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    private void initView() {


//        myScrollView.smoothScrollTo(500,500);//view边缘-内容边缘
//
//        TranslateAnimation animation = new TranslateAnimation(0,500,0,500);
//        animation.setDuration(2000);
//        myScrollView.startAnimation(animation);

//        ObjectAnimator.ofFloat(myScrollView, "translationX", 0, 500).setDuration(2000).start();//使用Int不能滑动
//        ObjectAnimator.ofFloat(mCustomView,"translationX",0,300).setDuration(1000).start();

//        PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat("translationX",0,500);
//        ObjectAnimator.ofPropertyValuesHolder(myScrollView,pvh).setDuration(2000).start();

//        mHandler.sendEmptyMessage(MESSAGE_SCROLL_TO);

        myScrollView = (MyScrollView) findViewById(R.id.scrollview222);


        myScrollView.setOnClickListener(this);


    }

    MyScrollView myScrollView;

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 60;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if(mCount<=FRAME_COUNT){
                        float fraction = mCount/(float)FRAME_COUNT;
//                        ((View)(myScrollView.getParent())).scrollTo(-(int)(fraction*500),0);

//                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) myScrollView.getLayoutParams();
//                        params.leftMargin =  (int) (fraction*500);
//                        Log.i("lwcaaa","params.leftMargin = " + params.leftMargin+", fraction = " + fraction);
//                        myScrollView.setLayoutParams(params);
                        ObjectAnimator.ofFloat(myScrollView,"translationX",fraction*500).setDuration(DELAYED_TIME).start();
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);

                    }
                    break;
            }
        }
    };





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scrollview222:

                Log.i("lwc1111","scrollView");
                Intent i = new Intent(this, ScrollActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
