package com.example.liangwenchao.appdemo.ui.base.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiangWenchao on 2016/3/11.
 */
public abstract  class BaseFragment extends Fragment implements IBaseInitFragment {


    protected OnFragmentInteractionListener mActivity;


    /**
     * fragment传递的参数 如果fragment已经启动，再使用setArguments会报错 已启动。
     */
    private Bundle args;

    public Bundle getArgs() {
        return args;
    }

    public void setArgs(Bundle args) {

    }


    @Override
    public void setArguments(Bundle args) {
        Bundle bundle = getArgs();
        if (bundle != null) {
            bundle.putAll(args);
        } else {
            bundle = args;
        }
        setArgs(bundle);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflateView(inflater,container,savedInstanceState);
        initView(view);
        initData();
        initListener();
        return view;
    }

    @Override
    public void initListener() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mActivity = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mActivity != null) {
            mActivity.onFragmentInteraction(uri);
        }
    }


}
