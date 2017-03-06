package com.xolo.weipulashi.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.SyInfo;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.dialog.CertificateDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.ScreenUtils;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;
import com.xolo.weipulashi.zxing.camera.CameraActivityCapture;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 索源
 */
public class SuoYuanActivity extends BaseActivity {

    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.iv_6)
    ImageView iv6;
    @BindView(R.id.iv_7)
    ImageView iv7;
    @BindView(R.id.iv_8)
    ImageView iv8;
    @BindView(R.id.iv_9)
    ImageView iv9;
    @BindView(R.id.iv_10)
    ImageView iv10;
    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.tv_pici)
    TextView tvPici;
    @BindView(R.id.tv_data_xiaosou)
    TextView tvDataXiaosou;
    @BindView(R.id.tv_data_caiji)
    TextView tvDataCaiji;
    @BindView(R.id.tv_data_produce)
    TextView tvDataProduce;
    @BindView(R.id.tv_data_ojc)
    TextView tvDataOjc;
    @BindView(R.id.tv_yunsong)
    TextView tvYunsong;
    @BindView(R.id.tv_data_cjc)
    TextView tvDataCjc;
    private CertificateDialog certificateDialog1;
    private CertificateDialog certificateDialog2;
    private CertificateDialog certificateDialog3;
    private CertificateDialog certificateDialog4;


    @Override
    public int getLayoutId() {
        return R.layout.activity_suo_yuan;
    }

    @Override
    public void initView() {
        Picasso.with(mContext).load(R.mipmap.sy_1).into(iv1);

        initIvHeight(iv1, R.mipmap.sy_1);
        initIvHeight(iv2, R.mipmap.sy_2);
        initIvHeight(iv3, R.mipmap.sy_3);
        initIvHeight(iv4, R.mipmap.sy_4);
        initIvHeight(iv5, R.mipmap.sy_5);
        initIvHeight(iv6, R.mipmap.sy_6);
        initIvHeight(iv7, R.mipmap.sy_7);
        initIvHeight(iv8, R.mipmap.sy_8);
        initIvHeight(iv9, R.mipmap.sy_9);
        initIvHeight(iv10, R.mipmap.sy_10);

        certificateDialog1 = new CertificateDialog(mContext);
        certificateDialog2 = new CertificateDialog(mContext);
        certificateDialog3 = new CertificateDialog(mContext);
        certificateDialog4 = new CertificateDialog(mContext);

        initTitle();
        initIntent();
    }

    private void initIntent() {
        SyInfo syInfo = (SyInfo) getIntent().getSerializableExtra(IntentConfig.SYINFO);

        tvPici.setText("你所查询的产品批次是：" + syInfo.getProductName() + "(" + syInfo.getBatchNO() + ")");
        tvDataXiaosou.setText("改批次产品已于" + syInfo.getOutDate() + "进入市场销售");
        tvDataCaiji.setText(syInfo.getFomilkDate());
        tvDataProduce.setText(syInfo.getFinishProduceDate());
        tvDataOjc.setText(syInfo.getAZCheckDate());
        tvYunsong.setText(syInfo.getTestDate());
        tvDataCjc.setText(syInfo.getZGCheckDate());

        certificateDialog1.setSrc(syInfo.getProductionCertificate());
        certificateDialog2.setSrc(syInfo.getAZTestCertificate());
        certificateDialog3.setSrc(syInfo.getEnterCertificate());
        certificateDialog4.setSrc(syInfo.getZGTestCertificate());
    }

    private void initTitle() {
        mTitleview.setRightBg(R.mipmap.scan);
        mTitleview.setRightIvClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(mContext, CameraActivityCapture.class), 99);
            }
        });
    }

    private int initIvHeight(View v, int res) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        BitmapFactory.decodeResource(getResources(), res, options);
        int width = options.outWidth;
        int height = options.outHeight;

        double size = height / (double) width;

        ViewGroup.LayoutParams params = v.getLayoutParams();
        params.height = (int) (ScreenUtils.getScreenWidth(mContext) * size);
        v.setLayoutParams(params);
        return params.height;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == RESULT_OK) {
            String barcode = data.getStringExtra(CameraActivityCapture.RESULT);
        GetTrace(barcode);
        }
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
                        tvPici.setText("你所查询的产品批次是：" + syInfo.getProductName() + "(" + syInfo.getBatchNO() + ")");
                        tvDataXiaosou.setText("改批次产品已于" + syInfo.getOutDate() + "进入市场销售");
                        tvDataCaiji.setText(syInfo.getFomilkDate());
                        tvDataProduce.setText(syInfo.getFinishProduceDate());
                        tvDataOjc.setText(syInfo.getAZCheckDate());
                        tvYunsong.setText(syInfo.getTestDate());
                        tvDataCjc.setText(syInfo.getZGCheckDate());

                        certificateDialog1.setSrc(syInfo.getProductionCertificate());
                        certificateDialog2.setSrc(syInfo.getAZTestCertificate());
                        certificateDialog3.setSrc(syInfo.getEnterCertificate());
                        certificateDialog4.setSrc(syInfo.getZGTestCertificate());
                    }

                    @Override
                    public void onError(BaseBean<SyInfo> syInfoBaseBean) {
                        ToastUtil.showLong(mContext, syInfoBaseBean.getMsg());
                    }
                });
    }

    @OnClick({R.id.btn_show_1, R.id.btn_show_2, R.id.btn_show_3, R.id.btn_show_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show_1:
                certificateDialog1.show();
                break;
            case R.id.btn_show_2:
                certificateDialog2.show();
                break;
            case R.id.btn_show_3:
                certificateDialog3.show();
                break;
            case R.id.btn_show_4:
                certificateDialog4.show();
                break;
        }
    }

}
