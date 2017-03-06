package com.xolo.weipulashi.ui.activity.Store;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.TrackInfoAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseCommenBean;
import com.xolo.weipulashi.bean.get.TrackInfo;
import com.xolo.weipulashi.ui.view.ScrollListView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.CommenSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 防窜货
 */
public class TrackInfoActivity extends BaseActivity {
    @BindView(R.id.et_barcode)
    EditText etBarcode;
    @BindView(R.id.tv_Code)
    TextView tvCode;
    @BindView(R.id.tv_State)
    TextView tvState;
    @BindView(R.id.tv_WName)
    TextView tvWName;
    @BindView(R.id.tv_ProductName)
    TextView tvProductName;
    @BindView(R.id.tv_ProductNo)
    TextView tvProductNo;
    @BindView(R.id.mListView)
    ScrollListView mListView;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.activity_track_info)
    LinearLayout activityTrackInfo;
    private TrackInfoAdapter trackInfoAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_track_info;
    }

    @Override
    public void initView() {
        initListView();
    }

    private void initListView() {
        trackInfoAdapter = new TrackInfoAdapter(mContext);
        mListView.setAdapter(trackInfoAdapter);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        if (TextUtils.isEmpty(getText(etBarcode))) {
            ToastUtil.showShort(mContext, "请输入条码");
            return;
        }
        getTrackInfo();
    }


    /**
     * 3.9.9.防窜货查询接口
     */
    private void getTrackInfo() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("BarCode", getText(etBarcode));

        RetrofitManager.getApiInstant()
                .getTrackInfo("GetTrackInfo", mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommenSubscriber<BaseCommenBean<TrackInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseCommenBean<TrackInfo> trackInfoBaseCommenBean) {
                        LogUtil.i("inter");
                        TrackInfo trackInfo = trackInfoBaseCommenBean.getT();
//                        LogUtil.i(trackInfo.getCode());
                        initData(trackInfo);

                        ll1.setVisibility(View.VISIBLE);
                        ll2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(BaseCommenBean<TrackInfo> trackInfoBaseCommenBean) {
                        ToastUtil.showLong(mContext, trackInfoBaseCommenBean.getMsg());
                        ll1.setVisibility(View.GONE);
                        ll2.setVisibility(View.GONE);
                    }
                });
    }

    private void initData(TrackInfo trackInfo) {
        tvCode.setText(trackInfo.getCode());
        tvState.setText(trackInfo.getState());
        tvProductName.setText(trackInfo.getTrackName());
        tvProductNo.setText(trackInfo.getProductNo());
        tvWName.setText(trackInfo.getWName());

        trackInfoAdapter.upDt(trackInfo.getTrackList());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
