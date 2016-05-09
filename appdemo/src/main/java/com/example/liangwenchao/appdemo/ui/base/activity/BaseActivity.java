package com.example.liangwenchao.appdemo.ui.base.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;
import com.example.liangwenchao.appdemo.utils.ActivityCollector;

import java.util.List;

/**
 * Created by admin on 2016/3/24.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseInitActivity {

    public String mPageName = this.getClass().getSimpleName();

    protected Context mContext;

    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        setContentView();

        Toast.makeText(this, "BaseActivity onCreate() " + mPageName, Toast.LENGTH_LONG).show();

        initView();
        initData();
        initListener();

        Log.d("BaseActivity", getClass().getSimpleName());

        ActivityCollector.addActivity(this);
    }

    /**
     * 设置布局
     */

    public abstract void setContentView();

    /**
     * 初始化监听器
     */

    @Override
    public void initListener() {

    }

    /**
     * 初始化数据
     */

    @Override
    public void initData() {

    }

    /**
     * 初始化 ActionBar
     *
     * @param isCustomActionBar 是否使用自定义的ActionBar布局
     */

    public void initActionBar(boolean isCustomActionBar) {
        if (isCustomActionBar) {
            setActionBarLayout();
        }
    }

    /**
     * 自定义ActionBar题目
     */
    private TextView actionBarTitle;
    /**
     * 自定义ActionBar图标
     */
    private ImageView actionBarIcon;

    protected void setActionBarLayout() {
        mActionBar = getSupportActionBar();
        if (null != mActionBar) {
            //定义actionbar背景
            mActionBar.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.bottom_bar));
            // 由子类决定是否显示返回上一级指示符
            // actionBar.setDisplayHomeAsUpEnabled(true);
            //  开放自定义view
            mActionBar.setDisplayShowCustomEnabled(true);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v = inflater.inflate(R.layout.actionbar_layout, null);
            actionBarTitle = (TextView) v.findViewById(R.id.textView);
            actionBarIcon = (ImageView) v.findViewById(R.id.imageView);

            ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

            mActionBar.setCustomView(v, params);
        }
    }

    ;

    public TextView getActionBarTitle() {
        return actionBarTitle;
    }

    public ImageView getActionBarIcon() {
        return actionBarIcon;
    }

    public void setActionBarTitleVisibility(int visibility) {
        if (actionBarTitle != null)
            actionBarTitle.setVisibility(visibility);
    }


    public void setActionBarIconVisibility(int visibility) {
        if (actionBarIcon != null)
            actionBarIcon.setVisibility(visibility);
    }

    /**
     * 设置ActionBar标题
     */
    public void setActionBarTitle(String title) {
        // 设置自定义的ActionBar的title
        if (actionBarTitle != null)
            actionBarTitle.setText(title);
        else
            getSupportActionBar().setTitle(title);
    }

    public void setActionBarTitle(int resid) {
        if (actionBarTitle != null)
            actionBarTitle.setText(resid);
        else
            getSupportActionBar().setTitle(resid);
    }

    public void setActionBarIcon(Bitmap bitmap) {
        if (actionBarIcon != null)
            actionBarIcon.setImageBitmap(bitmap);
    }

    public void setActionBarIcon(int icon) {
        if (actionBarIcon != null)
            actionBarIcon.setImageResource(icon);
    }

    /**
     * activity的返回结果码
     */
    private int resultCode;
    /**
     * activity的返回结果数据
     */
    private Intent intentData;

    /**
     * 获得返回结果数据
     *
     * @return
     */
    public Intent getIntentData() {
        return intentData;
    }

    /**
     * 设置Activity返回结果
     *
     * @param intentData
     */
    public void setIntentData(Intent intentData) {
        this.intentData = intentData;
    }

    /**
     * 获得Activity返回结果码
     *
     * @return
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * 设置Activity返回结果码
     *
     * @param resultCode
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * 结束Activity
     */
    @Override
    public void finish() {
        super.finish();

        // 设置切换动画，从右边进入，右边退出
        this.overridePendingTransition(R.anim.slide_right_in,
                R.anim.slide_right_out);
    }

    /**
     * 结束Activity时，设置返回结果
     */
    public void finishWithResult() {
        this.setResult(getResultCode(), getIntentData());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    public interface SwitchFragmentLisener{
        public Fragment createFragment(String tag) ;
    }

    public void switchFragment(String tag, @IdRes int resouceId, Bundle args,SwitchFragmentLisener lisener) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment.isVisible()) {
                    transaction.hide(fragment);
                }
            }
        }
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment == null) {
            if(lisener ==null){
                throw new IllegalArgumentException("SwitchFragmentLisener Object can not null");
            }
            fragment = lisener.createFragment(tag);
            transaction.replace(resouceId, fragment, tag);
        } else {
            transaction.show(fragment);
        }

        ((BaseFragment) fragment).setArgs(args);
        transaction.commitAllowingStateLoss();
    }
}
