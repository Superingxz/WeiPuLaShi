package com.xolo.weipulashi.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xolo.weipulashi.utils.InstantUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/10.
 */

public abstract  class BaseFragment<P extends BasePresenter> extends Fragment {

    public View inflate;

    public P mPresenter;

    public Activity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, inflate);
        mContext = getActivity();
        initView();

        if (this instanceof BaseView) {
            try {
                mPresenter = InstantUtils.getInstance(this, 0);
                mPresenter.setmContext(mContext);
                mPresenter.setView(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
        return inflate;
    }

    public abstract void initView();

    public abstract int getLayoutId();
}
