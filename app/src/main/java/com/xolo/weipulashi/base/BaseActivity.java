package com.xolo.weipulashi.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xolo.weipulashi.bean.get.User;
import com.xolo.weipulashi.configuration.SpConfig;
import com.xolo.weipulashi.utils.InstantUtils;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.SPUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/3.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    public Activity mContext;

    public P mPresenter;

    protected Gson mGson;

    protected static User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        mGson = new Gson();
        ButterKnife.bind(this);
        mContext = this;
        initUser();
        initView();
        if (this instanceof BaseView) {
            try {
                mPresenter = InstantUtils.getInstance(this, 0);
                mPresenter.setmContext(mContext);
                mPresenter.setView(this);
                if (mUser!=null){
                    mPresenter.setmUser(mUser);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public String getText(TextView tv){
        return  tv.getText().toString().trim();
    }

    private void  initUser(){
        if (mUser == null) {
            String jsUser = (String) SPUtils.get(mContext, SpConfig.USER_JSON, "");
            if (!TextUtils.isEmpty(jsUser)) {
                try {
                    mUser = mGson.fromJson(jsUser, User.class);
                    LogUtil.i(mUser.toString());
                } catch (JsonSyntaxException e) {
                    LogUtil.i("用户信息序列化失败");
                }
            }
        }
    }

    public static void setmUser(User mUser) {
        BaseActivity.mUser = mUser;
    }

    public abstract int getLayoutId();

    public abstract void initView();

}
