package com.example.liangwenchao.appdemo.ui.base.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liangwenchao.appdemo.ui.view.fragment.DrawBallFregment;

import java.util.List;

/**
 * Created by LiangWenchao on 2016/3/11.
 */
public abstract class BaseFragment extends Fragment implements IBaseInitFragment {

    private Context mContext;
    private static FragmentActivity mActivity;

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
        View view = inflateView(inflater, container, savedInstanceState);
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
        mContext = context;
        mActivity = getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    public static void switchFragment(int index, @IdRes int resouceId, Bundle args, String tag) {
        if (mActivity != null) {
            FragmentManager manager = mActivity.getSupportFragmentManager();
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
                switch (index) {
                    case DrawBallFregment.DRAWBALLFRAGMENTINDEX:
                        fragment = new DrawBallFregment();
                        break;
                }

                transaction.replace(resouceId, fragment, tag);

            } else {
                transaction.show(fragment);
            }

            ((BaseFragment) fragment).setArgs(args);

            transaction.commitAllowingStateLoss();

        }
    }


}
