package com.example.waimai.view;

import com.example.waimai.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class XListViewFooter extends LinearLayout {
    public boolean isTheLast;
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;

    private Context mContext;

    private View mContentView;
    public View mProgressBar;
    private TextView lastitem;
    private TextView txttittle;

    // private TextView mHintView;

    public XListViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XListViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setState(int state) {
        mProgressBar.setVisibility(View.INVISIBLE);
        if (state == STATE_LOADING) {// 当加载的时候
            mProgressBar.setVisibility(View.VISIBLE);// 加载进度条显示
        }
    }

    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        return lp.bottomMargin;
    }

    /**
     * normal status
     */
    public void normal() {
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * loading status
     */
    public void loading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 当禁用拉加载更多隐藏底部视图
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.height = 0;
        mContentView.setLayoutParams(lp);
    }

    /**
     * 显示底部视图
     */
    public void show() {
        LayoutParams lp = (LayoutParams) mContentView
                .getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mContentView.setLayoutParams(lp);
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext)
                .inflate(R.layout.xlistview_footer, null);
        addView(moreView);
        moreView.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        lastitem = (TextView) moreView.findViewById(R.id.lastitem);
        txttittle = (TextView) moreView.findViewById(R.id.xlistview_footer_hint_textview);
        mContentView = moreView.findViewById(R.id.xlistview_footer_content);
        mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
    }

    public void theLastItem(int mark) {
        if (mark == 1) {
            isTheLast = true;
            lastitem.setVisibility(ViewGroup.VISIBLE);
            txttittle.setVisibility(ViewGroup.GONE);
        } else {
            isTheLast = false;
            lastitem.setVisibility(ViewGroup.GONE);
            txttittle.setVisibility(ViewGroup.VISIBLE);
        }

    }
}
