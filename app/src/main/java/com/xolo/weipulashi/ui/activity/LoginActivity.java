package com.xolo.weipulashi.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.User;
import com.xolo.weipulashi.bean.json.JsUser;
import com.xolo.weipulashi.configuration.SpConfig;
import com.xolo.weipulashi.runtimepermissions.PermissionsManager;
import com.xolo.weipulashi.runtimepermissions.PermissionsResultAction;
import com.xolo.weipulashi.ui.activity.JingXiaoShang.JxsMainActivity;
import com.xolo.weipulashi.ui.activity.Store.StroeMainActivity;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.SPUtils;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_remenber_password)
    CheckBox cbRemenberPassword;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        boolean is_remeber = (boolean) SPUtils.get(mContext, SpConfig.IS_REMEBER_PASSWORD,false);
        if (is_remeber){
            String user= (String) SPUtils.get(mContext, SpConfig.USER, "");
            String password= (String) SPUtils.get(mContext, SpConfig.PASSWORD, "");

            cbRemenberPassword.setChecked(true);
            etUser.setText(user);
            etPassword.setText(password);
        }

            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions();
            }
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        RetrofitManager.getApiInstant()
                .getUser("UserLogin",mGson.toJson(new JsUser(getText(etUser),getText(etPassword))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<User>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<User> userBaseBean) {
                        User user = userBaseBean.getDt().get(0);
                        save(user);

                        if (user.getBType() == 1){
                             startActivity(new Intent(mContext,JxsMainActivity.class));
                        }else {
                            startActivity(new Intent(mContext,StroeMainActivity.class));
                        }
                        finish();
                    }

                    @Override
                    public void onError(BaseBean<User> userBaseBean) {
                        ToastUtil.showLong(mContext,userBaseBean.getMsg());
                    }
                });
    }

public  void  save(User user){
    SPUtils.put(mContext,SpConfig.USER_JSON,mGson.toJson(user));
    setmUser(user);

    boolean checked = cbRemenberPassword.isChecked();
    SPUtils.put(mContext,SpConfig.IS_REMEBER_PASSWORD,checked);
    if (checked){
        SPUtils.put(mContext,SpConfig.USER,getText(etUser));
        SPUtils.put(mContext,SpConfig.PASSWORD,getText(etPassword));
    }
}


    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
            }

            @Override
            public void onDenied(String permission) {
                Toast.makeText(mContext, "缺少部分权限会导致程序无法正常使用", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
