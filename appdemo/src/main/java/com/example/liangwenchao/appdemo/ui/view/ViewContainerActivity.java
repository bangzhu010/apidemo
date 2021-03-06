package com.example.liangwenchao.appdemo.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.activity.BaseActivity;
import com.example.liangwenchao.appdemo.ui.view.fragment.AdapterViewFilpperFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.AdapterViewFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.AutoCompleteTextViewFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.CalculatorFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.ClockFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.DrawBallFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.ImageBrowserFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.NeonLightsFragment;
import com.example.liangwenchao.appdemo.ui.view.fragment.QuickContactBadageFragment;

/**
 * Created by admin on 2016/5/9.
 */
public class ViewContainerActivity extends BaseActivity {

    private String currentFragmentTag = "";
    private String title;

    private TextView titleTextView;

    @Override
    public void setContentView() {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_view_container);

        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
                case QuickContactBadageFragment.QUICK_CONTACT_BADAGE_TAG:
                    fragment = new QuickContactBadageFragment();
                    break;
                case AdapterViewFragment.ADAPTER_VIEW_FRAGMENT_TAG:
                    fragment = new AdapterViewFragment();
                    break;
                case AutoCompleteTextViewFragment.AUTO_COMPLETE_TEXTVIEW_FRAGMENT_TAG:
                    fragment = new AutoCompleteTextViewFragment();
                    break;
                case AdapterViewFilpperFragment.ADAPTER_VIEW_FILPPER_FRAGMENT_TAG:
                    fragment = new AdapterViewFilpperFragment();
                    break;
            }
            return fragment;
        }
    };



}
