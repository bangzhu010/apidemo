package com.example.waimai;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.waimai.view.SecondFramentLiner;
import com.example.waimai.view.SecondFramentLiner.ButtonHuiDiao;
import com.example.waimai.view.XListView;
import com.example.waimai.view.XListView.IXListViewListener;

public class SecondFrament extends Fragment implements ButtonHuiDiao,
		IXListViewListener {
	private XListView list;
	private SecondFramentLiner secondFramentLiner;
	private LayoutInflater inflater;
	private BaseAdapterXlist baseAdapterXlist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.inflater = inflater;
		return inflater.inflate(R.layout.second_frament, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		list = (XListView) getView().findViewById(R.id.list);
		list.setPullLoadEnable(true);
		list.setPullRefreshEnable(false);
		list.setXListViewListener(this);
		secondFramentLiner = (SecondFramentLiner) getView().findViewById(
				R.id.secondFramentLiner);
		secondFramentLiner.setButtonhuidiao(this);
		getdata();
		baseAdapterXlist = new BaseAdapterXlist();
		list.setAdapter(baseAdapterXlist);

	}

	private int count = 10;
	private int type;

	class BaseAdapterXlist extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return count;
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
				convertView = inflater.inflate(R.layout.listview_item, null);
			}
			if (type == 1) {
				TextView txt = (TextView) convertView.findViewById(R.id.txt);
				txt.setText("改变了聊亲，让我们一起来摇滚，欧耶耶耶耶耶有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义,改变了聊亲，让我们一起来摇滚，欧耶耶耶耶耶有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义有意义");
			} else if (type == 0) {
				TextView txt = (TextView) convertView.findViewById(R.id.txt);
				txt.setText("太慢了这味道不错攒了哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
			}
			return convertView;
		}
	}

	private void getdata() {
		// TODO Auto-generated method stub

	}

	@Override
	public void text1() {
		// TODO Auto-generated method stub
		count = 10;
		type = 0;
		baseAdapterXlist.notifyDataSetChanged();

	}

	@Override
	public void text2() {
		// TODO Auto-generated method stub
		count = 10;
		type = 1;
		baseAdapterXlist.notifyDataSetChanged();
	}

	@Override
	public void text3() {
		// TODO Auto-generated method stub

	}

	@Override
	public void text4() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
					count += 10;
					getActivity().getWindow().getDecorView()
							.post(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									baseAdapterXlist.notifyDataSetChanged();
									list.stopLoadMore();
								}
							});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}
}
