package com.xolo.weipulashi.base;

import android.app.Activity;

import com.google.gson.Gson;
import com.xolo.weipulashi.bean.get.User;

/**
 * Created by baixiaokang on 16/4/22.
 */
public abstract class BasePresenter<V> {
    public Activity mContext;
    public V mView;
    protected Gson mGson;
    protected User mUser;

    public void setView(V v) {
        mGson = new Gson();
        this.mView = v;
        this.onAttached();
    }

    public void setmContext(Activity mContext) {

        this.mContext = mContext;
    }

    public void setmUser(User mUser) {
        this.mUser = mUser;
    }

    public abstract void onAttached();

    public void onDetached() {
    }
}
