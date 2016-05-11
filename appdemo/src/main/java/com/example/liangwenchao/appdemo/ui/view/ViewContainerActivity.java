package com.example.liangwenchao.appdemo.ui.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;
import com.example.liangwenchao.appdemo.ui.view.fragment.CalculatorFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.ClockFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.DrawBallFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.ImageBrowserFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.NeonLightsFragment;

/**
 * Created by admin on 2016/5/9.
 */
public class ViewContainerActivity extends BaseActivity {

    private String currentFragmentTag = "";
    private String title;

    private TextView titleTextView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_view_container);
    }

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        currentFragmentTag = intent.getStringExtra("fragmentTag");
        title = intent.getStringExtra("title");
        titleTextView.setText(title);
        switchFragment(currentFragmentTag, R.id.view_container, null, lisener);

        View view = findViewById(R.id.view_container);
        Log.i("lwc", "view = " + view);
    }

    @Override
    public void initView() {
        titleTextView = (TextView) findViewById(R.id.title);
    }

    private SwitchFragmentLisener lisener = new SwitchFragmentLisener() {
        @Override
        public Fragment createFragment(String tag) {
            Fragment fragment = null;
            switch (tag) {
                case DrawBallFragment.DRAW_BALL_FRAGMENT_TAG:
                    fragment = new DrawBallFragment();
                    break;
                case NeonLightsFragment.NEON_LIGHTS_FRAGMENT_TAG:
                    fragment = new NeonLightsFragment();
                    break;
                case CalculatorFragment.CALCULATOR_FRAGMENT_TAG:
                    fragment = new CalculatorFragment();
                    break;
                case ClockFragment.CLOCK_FRAGMENT_TAG:
                    fragment = new ClockFragment();
                    break;
                case ImageBrowserFragment.IMAGE_BROWSER_FRAGMENY_TAG:
                    fragment = new ImageBrowserFragment();
                    break;
            }
            return fragment;
        }
    };

}
