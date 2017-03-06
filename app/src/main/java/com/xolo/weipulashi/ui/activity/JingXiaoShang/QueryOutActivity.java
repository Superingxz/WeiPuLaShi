package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.QueryOutAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.LoadMoreActivity;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.json.JsGetSaleList;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.dialog.DataDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 销售出库明细查询
 */
public class QueryOutActivity extends LoadMoreActivity {
    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.et_saleCode)
    EditText etSaleCode;
    @BindView(R.id.et_StoreNam)
    EditText etStoreNam;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;

    private Dialog  startTimeDialog;
    private Dialog  endTimeDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_query_out;
    }

    @Override
    public void initView() {
        initTitle();
        initListView();
        startTimeDialog = DataDialog.creatDateDialog(mContext,tvStartTime);
        endTimeDialog = DataDialog.creatDateDialog(mContext,tvEndTime);

        loadFristPage();
    }

    private void initListView() {
        loadAdapter = new QueryOutAdapter(mContext);
        mListView.setAdapter(loadAdapter);
        mListView.setOnScrollListener(this);
    }

    private void initTitle() {
        mTitleview.setRightBg(R.mipmap.search);
        mTitleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAdapter.clearAll();
           loadFristPage();
            }
        });
    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                startTimeDialog.show();
                break;
            case R.id.tv_end_time:
                endTimeDialog.show();
                break;
        }
    }

    @OnItemClick(R.id.mListView)
    public void  onItemClick(int position){
        Intent intent = new Intent(mContext, XsOutDetailActivity.class);
        intent.putExtra(IntentConfig.SALEID,String.valueOf(((OutOrderInfo)loadAdapter.getItem(position)).getID()));
        startActivity(intent);
    }

    @Override
    public void load() {
        RetrofitManager.getApiInstant()
                .getOutOrederInfo("GetSaleList", mGson.toJson(new JsGetSaleList(mUser.getBranch_id(), getText(etSaleCode), getText(etStoreNam), getText(tvStartTime),getText(tvEndTime),10,String.valueOf(currentPage))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<OutOrderInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
                        loadAdapter.addAll(outOrderInfoBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
//                        ArrayList<OutOrderInfo> outOrderInfos = new ArrayList<>();
//                        outOrderInfos.add(new OutOrderInfo(2605,"2016-9-16","怡宝","已发货","S170104184929"));
//                        loadAdapter.addAll(outOrderInfos);
                        check(outOrderInfoBaseBean);
                    }
                });
    }

}
