package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/24.
 */
public class AutoCompleteTextViewFragment extends BaseFragment {
    public static final String AUTO_COMPLETE_TEXTVIEW_FRAGMENT_TAG = "AutoCompleteTextViewFragment";
    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private String[] books = new String[]{
            "疯狂java讲义",
            "疯狂Aja讲义",
            "疯狂Xml讲义",
            "疯狂Android讲义"
    };
    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_auto_complete_textview,null);
    }

    @Override
    public void initView(View view) {
        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.auto_comolete_text_view);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) view.findViewById(R.id.multi_auto_complete_text_view);

    }

    @Override
    public void initData() {
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_dropdown_item_1line,books);
        autoCompleteTextView.setAdapter(mAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());//
        multiAutoCompleteTextView.setAdapter(mAdapter);

    }
}
