package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/10.
 */
public class CalculatorFragment extends BaseFragment {

    private GridLayout gridLayout;

    private String[] chars = new String[]{
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "3", "2", "1", "-",
            ".", "0", "=", "+"
    };

    public final static String CALCULATOR_FRAGMENT_TAG = "CalculatorFragment";

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, null);
    }

    @Override
    public void initView(View view) {
        gridLayout = (GridLayout) view.findViewById(R.id.root);
        for (int i = 0; i < chars.length; i++) {
            Button button = new Button(getActivity());

            button.setText(chars[i]);
            button.setTextSize(40);
            button.setPadding(5, 35, 5, 35);

            GridLayout.Spec rowSpec = GridLayout.spec(i / 4 + 2);
            GridLayout.Spec columnSpec = GridLayout.spec(i % 4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
            if(chars[i].equals("8")){

            }
            params.setGravity(Gravity.FILL_HORIZONTAL);
            gridLayout.addView(button,params);
        }
    }

    @Override
    public void initData() {

    }
}
