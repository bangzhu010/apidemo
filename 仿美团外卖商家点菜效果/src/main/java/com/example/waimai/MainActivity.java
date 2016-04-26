package com.example.waimai;

import java.util.ArrayList;
import java.util.List;

import com.example.waimai.view.RootScroller;
import com.nineoldandroids.view.ViewHelper;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends FragmentActivity {
	private ViewPager viewPager;
	private EngineLinearLayout engine;
	private List<Fragment> listFrament;
	private ScrollerLiner scrollerLiner;
	private RootScroller rootliner;
	private RelativeLayout ttileRelayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_h_category1);
		ttileRelayout = (RelativeLayout) findViewById(R.id.ttileRelayout);
		ViewHelper.setAlpha(ttileRelayout, 0);
		rootliner = (RootScroller) findViewById(R.id.rootliner);
		rootliner.setTittleView(ttileRelayout);
		scrollerLiner = (ScrollerLiner) findViewById(R.id.scrollerLiner);
		scrollerLiner.setHeader(rootliner);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		engine = (EngineLinearLayout) findViewById(R.id.engine);
		engine.setPager(viewPager);
		listFrament = new ArrayList<Fragment>();
		listFrament.add(new FristFrament());
		listFrament.add(new SecondFrament());
		listFrament.add(new ThreeFrament());

		viewPager.setAdapter(new PagerFramentAdapter(
				getSupportFragmentManager()));
	}

	class PagerFramentAdapter extends FragmentPagerAdapter {

		public PagerFramentAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return listFrament.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listFrament.size();
		}
	}

}
