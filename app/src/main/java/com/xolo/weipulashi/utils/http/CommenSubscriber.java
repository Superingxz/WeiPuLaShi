package com.xolo.weipulashi.utils.http;

import android.app.Activity;
import android.content.Context;

import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BaseCommenBean;
import com.xolo.weipulashi.ui.dialog.LoadingDialog;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/6.
 */

public abstract class CommenSubscriber<T extends BaseCommenBean> extends Subscriber<T>{
    static   boolean  loading;

    private Context mContext;
    private static LoadingDialog dialog;

    public CommenSubscriber(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.show(mContext);
        loading = true;
    }

    @Override
    public void onCompleted() {
        if (!loading){
            dialog.dismiss(mContext);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.i("失败");
        loading = false;
        dialog.dismiss(mContext);
        ToastUtil.showShort(mContext,"网络连接错误，请稍后重试");
    }

    @Override
    public void onNext(T t) {
        LogUtil.i("成功");

        loading = false;
        if (t.getRes().equals("0000")){
            onSuccess(t);
        }else {
            onError(t);
        }

    }

    public abstract   void  onSuccess(T t);

    public abstract   void  onError(T t);
}
