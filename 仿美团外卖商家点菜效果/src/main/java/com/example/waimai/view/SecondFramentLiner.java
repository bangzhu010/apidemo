package com.example.waimai.view;

import com.example.waimai.R;

import android.app.SearchManager.OnCancelListener;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondFramentLiner extends LinearLayout {
	private TextView txt1;
	private TextView txt2;
	private TextView txt3;
	private TextView txt4;
	private ButtonHuiDiao buttonhuidiao;

	public void setButtonhuidiao(ButtonHuiDiao buttonhuidiao) {
		this.buttonhuidiao = buttonhuidiao;
	}

	public SecondFramentLiner(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public interface ButtonHuiDiao {
		void text1();

		void text2();

		void text3();

		void text4();

	}

	private void init(Context context) {
		// TODO Auto-generated method stub
		setOrientation(LinearLayout.HORIZONTAL);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.second_frament_item, this);
		txt1 = (TextView) view.findViewById(R.id.txt1);
		txt1.setBackgroundColor(Color.RED);
		txt2 = (TextView) view.findViewById(R.id.txt2);
		txt3 = (TextView) view.findViewById(R.id.txt3);
		txt4 = (TextView) view.findViewById(R.id.txt4);
		txt1.setOnClickListener(clik);
		txt2.setOnClickListener(clik);
		txt3.setOnClickListener(clik);
		txt4.setOnClickListener(clik);

	}

	OnClickListener clik = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.txt1:
				txt1.setBackgroundColor(Color.RED);
				txt3.setBackgroundColor(Color.WHITE);
				txt2.setBackgroundColor(Color.WHITE);
				txt4.setBackgroundColor(Color.WHITE);
				if(buttonhuidiao!=null){
					buttonhuidiao.text1();
				}

				break;

			case R.id.txt2:
				txt1.setBackgroundColor(Color.WHITE);
				txt3.setBackgroundColor(Color.WHITE);
				txt2.setBackgroundColor(Color.RED);
				txt4.setBackgroundColor(Color.WHITE);
				if(buttonhuidiao!=null){
					buttonhuidiao.text2();
				}
				break;
			case R.id.txt3:
				txt1.setBackgroundColor(Color.WHITE);
				txt3.setBackgroundColor(Color.RED);
				txt2.setBackgroundColor(Color.WHITE);
				txt4.setBackgroundColor(Color.WHITE);
				if(buttonhuidiao!=null){
					buttonhuidiao.text3();
				}
				break;
			case R.id.txt4:
				txt1.setBackgroundColor(Color.WHITE);
				txt3.setBackgroundColor(Color.WHITE);
				txt2.setBackgroundColor(Color.WHITE);
				txt4.setBackgroundColor(Color.RED);
				if(buttonhuidiao!=null){
					buttonhuidiao.text4();
				}
				break;
			}

		}
	};

}
