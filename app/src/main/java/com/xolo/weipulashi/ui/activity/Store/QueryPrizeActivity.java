package com.xolo.weipulashi.ui.activity.Store;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.DuiJiangRecordAdapter;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.LoadMoreActivity;
import com.xolo.weipulashi.bean.get.DuiJiangRecord;
import com.xolo.weipulashi.bean.json.JsGetAssistDuijiangList;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.dialog.DataDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 协助会员兑换记录
 */
public class QueryPrizeActivity extends LoadMoreActivity {

    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.mListView)
    ListView mListView;

    private Dialog startDataDialog;
    private Dialog endDataDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_query_prize;
    }

    @Override
    public void initView() {
        initTitle();
        initDialog();
        initListView();

        loadFristPage();
    }

    private void initListView() {
        loadAdapter = new DuiJiangRecordAdapter(mContext);
        mListView.setAdapter(loadAdapter);
        mListView.setOnScrollListener(this);
    }

    private void initDialog() {
        startDataDialog = DataDialog.creatDateDialog(mContext, tvStartTime);
        endDataDialog = DataDialog.creatDateDialog(mContext, tvEndTime);
    }

    private void initTitle() {
        mTitleview.setRightBg(R.mipmap.search);
        mTitleview.setRightIvClick(new View.OnClickListener() {
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
                startDataDialog.show();
                break;
            case R.id.tv_end_time:
                endDataDialog.show();
                break;
        }
    }

    /**
     * 3.9.5.协助兑换订单记录列表接口
     */
    @Override
    public void load() {
        String s = mGson.toJson(new JsGetAssistDuijiangList(mUser.getBranch_id(), getText(etPhone), getText(tvName), getText(tvStartTime), getText(tvEndTime), "10", String.valueOf(currentPage)));

        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getDuiJiangRecord("GetAssistDuijiangList",s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<DuiJiangRecord>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<DuiJiangRecord> duiJiangRecordBaseBean) {
                        loadAdapter.addAll(duiJiangRecordBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<DuiJiangRecord> duiJiangRecordBaseBean) {
                                      check(duiJiangRecordBaseBean);
//                        ArrayList<DuiJiangRecord> duiJiangRecords = new ArrayList<>();
//                        duiJiangRecords.add(new DuiJiangRecord(10,"安宝乐总部","胡晓云","13112297627","2016-09-24",200));
//                        loadAdapter.addAll(duiJiangRecords);
                    }
                });
    }

    @OnItemClick(R.id.mListView)
    public  void onItemClick(int position){
        Intent intent = new Intent(mContext, QueryPrizeInfoActivity.class);
        intent.putExtra(IntentConfig.DUIJIANGRECORD, ((DuiJiangRecord) loadAdapter.getItem(position)));
        startActivity(intent);
    }
}
