package com.xolo.weipulashi.ui.activity.Store;

import android.text.TextUtils;
import android.widget.EditText;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.json.JsMRegisterByStore;
import com.xolo.weipulashi.bean.json.JsMScoreByStore;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 协助会员积分
 */
public class MdXzJfActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_jf)
    EditText etJf;

    @Override
    public int getLayoutId() {
        return R.layout.activity__md_xzjf;
    }

    @Override
    public void initView() {}

    @OnClick(R.id.btn_registered)
    public void onClick() {
        mRegisterByStore();
    }

    /**
     * 3.6.1.协助注册接口
     */
    private void  mRegisterByStore(){
        if (TextUtils.isEmpty(getText(etPhone))){
            ToastUtil.showShort(mContext,"请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(getText(etJf))){
            ToastUtil.showShort(mContext,"请输入积分数码");
            return;
        }

        String toJson = mGson.toJson(new JsMScoreByStore(mUser.getBranch_id(), getText(etPhone), getText(etJf),mUser.getEmployeeID()));
        LogUtil.i(toJson);

        RetrofitManager.getApiInstant()
                .getBaseBean("MScoreByStore",toJson)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                        finish();
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }
                });
    }
}
