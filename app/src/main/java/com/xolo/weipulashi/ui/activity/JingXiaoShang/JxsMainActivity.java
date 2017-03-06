package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.JxsMainAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.Menu;
import com.xolo.weipulashi.bean.get.SyInfo;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.activity.LoginActivity;
import com.xolo.weipulashi.ui.activity.ProduceSignActivity;
import com.xolo.weipulashi.ui.activity.SuoYuanActivity;
import com.xolo.weipulashi.ui.dialog.MsgDialog;
import com.xolo.weipulashi.utils.ScreenUtils;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;
import com.xolo.weipulashi.zxing.camera.CameraActivityCapture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 经销商首页
 */
public class JxsMainActivity extends BaseActivity implements CommenRecycleAdapter.OnItemClickLitener {

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv_left)
    ImageView ivLeft;

    private List<Menu> menuList = new ArrayList<>();
    private JxsMainAdapter jxsMainAdapter;

    private long mExitTime;

    private MsgDialog closeDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jxs_main;
    }

    @Override
    public void initView() {
        initBanner();
        initRv();
        initData();

        closeDialog = new MsgDialog(mContext, "提示", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
                finish();
            }
        });
        closeDialog.setMsg("是否退出登录？");
    }

    private void initRv() {
        jxsMainAdapter = new JxsMainAdapter(mContext);
        jxsMainAdapter.setOnItemClickLitener(this);

        rv.setAdapter(jxsMainAdapter);
        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    private void initData() {
        menuList.add(new Menu(R.mipmap.jxs_sign, "产品签收入库", ProduceSignActivity.class));
        menuList.add(new Menu(R.mipmap.jxs_head_out, "销售出库扫码", JsOutputScanActivity.class));
        menuList.add(new Menu(R.mipmap.jxs_query_out, "销售出库明细查询", QueryOutActivity.class));
        menuList.add(new Menu(R.mipmap.jxs_return, "门店退回扫码入库", ReturnScanActivity.class));
        menuList.add(new Menu(R.mipmap.jxs_query_return, "门店退货明细查询", QueryReturnActivity.class));
        menuList.add(new Menu(R.mipmap.jxs_ku_cun, "产品库存查看", JxsStockNumActivity.class));
        jxsMainAdapter.upDt(menuList);
    }

    private void initBanner() {
        ViewGroup.LayoutParams params = ivBanner.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth(mContext) * 0.416);
        ivBanner.setLayoutParams(params);
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(mContext, menuList.get(position).getC()));
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 3.9.8.溯源信息接口
     * {'Code':'8677290767'}
     */
    private void GetTrace(String code) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("Code", code);

        RetrofitManager.getApiInstant()
                .getSyInfo("GetTrace", mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<SyInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<SyInfo> syInfoBaseBean) {
                        SyInfo syInfo = syInfoBaseBean.getDt().get(0);
                        Intent intent = new Intent(mContext, SuoYuanActivity.class);
                        intent.putExtra(IntentConfig.SYINFO, syInfo);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(BaseBean<SyInfo> syInfoBaseBean) {
                        ToastUtil.showLong(mContext, syInfoBaseBean.getMsg());
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == RESULT_OK) {
            String barcode = data.getStringExtra(CameraActivityCapture.RESULT);
            GetTrace(barcode);
        }
    }

    @OnClick({R.id.iv_right, R.id.iv_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
               closeDialog.show();
                break;
            case R.id.iv_left:
                startActivityForResult(new Intent(mContext, CameraActivityCapture.class), 99);
                break;
        }
    }

}
