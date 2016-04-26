package com.example.waimai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waimai.view.CustomAdapter;
import com.example.waimai.view.PinnedHeaderListView;

public class FristFrament extends Fragment {
	private ListView lv_Left;
	private PinnedHeaderListView lv_right;
	private List<String> list;
	private LayoutInflater inflater;
	private List<Food> listData;
	//保存左右对应的坐标
	private HashMap<Integer, Integer> hash=new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> hash1=new HashMap<Integer, Integer>();
	private View view;
	/**
	 * 由于viewPager切换时会重新创建view，当前的listView对象不是当前的了所以保存listview
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.inflater = inflater;
		if(view==null){
			view=inflater.inflate(R.layout.first_frament, null);
		}else{
			((ViewGroup)view.getParent()).removeView(view);
		}
		Log.i("huoying", "onCreateView");
		return view;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.i("huoying", "onDestroyView");

		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("huoying", "onDestroy");

		super.onDestroy();
	}
	/**
	 * 由于左边的条目和右边的是对应的所以
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		list = new ArrayList<String>();
		listData = new ArrayList<Food>();
		getData();
		lv_Left = (ListView) getView().findViewById(R.id.lv_Left);
		lv_right = (PinnedHeaderListView) getView().findViewById(R.id.lv_right);
		CustomAdapter rightAdapter=new CustomAdapter(getActivity(), listData);
		lv_right.setAdapter(rightAdapter);
		View HeaderView = inflater.inflate(R.layout.listview_item_header,
				lv_right, false);
		lv_right.setPinnedHeader(HeaderView);
		lv_right.setOnScrollListener(rightAdapter);
		rightAdapter.setLeftListView(lv_Left,hash1);
		lv_Left.setAdapter(new BaseLeft());
		lv_Left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
									int position, long arg3) {
				/**
				 * 改变左侧栏的颜色
				 */
				for (int i = 0; i < lv_Left.getChildCount(); i++)
				{
					if (i == position)
					{
						lv_Left.getChildAt(i).setBackgroundColor(Color.parseColor("#dcdcdc"));
					} else
					{
						lv_Left.getChildAt(i).setBackgroundColor(Color.WHITE);
					}
				}
				lv_right.setSelection(hash.get(position));
			}

		});
		lv_right.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "美团外卖", 1).show();

			}
		});

	}

	/**
	 * 获取数据
	 */
	private void getData() {
		// TODO Auto-generated method stub
		list.add("套餐系列");
		list.add("风味披萨");
		list.add("意面系列");
		list.add("特色焗饭");
		list.add("咖啡系列");
		list.add("加饭区");
		list.add("小吃");
		list.add("加餐区");
		for (int i = 1; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				Food food = new Food();
				food.setType(i);
				hash.put(i-1, (i-1)*10);
				hash1.put((i-1)*10, i);
				switch (i) {
					case 1:
						food.setTittle("套餐系列");
						break;

					case 2:
						food.setTittle("风味披萨");

						break;
					case 3:
						food.setTittle("意面系列");

						break;
					case 4:
						food.setTittle("特色焗饭");

						break;
					case 5:
						food.setTittle("咖啡系列");

						break;
					case 6:
						food.setTittle("加饭区");

						break;

					case 7:
						food.setTittle("小吃");

						break;
					case 8:
						food.setTittle("加餐区");

						break;

				}

				listData.add(food);
			}
		}

	}

	class BaseLeft extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.left_baseadapter, null);
			}
			RelativeLayout re= (RelativeLayout) convertView.findViewById(R.id.re);
			if(position==0){
				re.setBackgroundColor(Color.parseColor("#dcdcdc"));
			}
			TextView textView = (TextView) convertView.findViewById(R.id.txt);
			textView.setText(list.get(position));
			return convertView;
		}

	}

	class BaseRight extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.right_baseadapter, null);
			}
			return convertView;
		}
	}

}
