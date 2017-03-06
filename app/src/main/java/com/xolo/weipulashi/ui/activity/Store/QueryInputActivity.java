package com.xolo.weipulashi.ui.activity.Store;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.OrderInputAdapter;
import com.xolo.weipulashi.adapter.OrderStateAdapter;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.LoadMoreActivity;
import com.xolo.weipulashi.bean.get.OrderInput;
import com.xolo.weipulashi.bean.get.OrderState;
import com.xolo.weipulashi.bean.json.JsGetPurchaseList;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.dialog.DataDialog;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 入库查询
 */
public class QueryInputActivity extends LoadMoreActivity {


    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_order)
    EditText tvOrder;
    @BindView(R.id.et_man)
    EditText etMan;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.mListView)
    ListView mListView;

    private Dialog startDataDialog;
    private Dialog endDataDialog;

    List<OrderState> orderStateList;
    private OrderStateAdapter orderStateAdapter;
    private ListDialog orderStateDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_query_input;
    }

    @Override
    public void initView() {
        initTitle();
        initDialog();
        initListView();
        loadFristPage();
    }

    private void initListView() {
        loadAdapter = new OrderInputAdapter(mContext);
        mListView.setAdapter(loadAdapter);
        mListView.setOnScrollListener(this);
    }

    private void initDialog() {
        startDataDialog = DataDialog.creatDateDialog(mContext, tvStartTime);
        endDataDialog = DataDialog.creatDateDialog(mContext, tvEndTime);

        initData();
        orderStateAdapter = new OrderStateAdapter(mContext);
        orderStateAdapter.upDt(orderStateList);
        orderStateDialog = new ListDialog(mContext, "选择单据状态", orderStateAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvState.setText(orderStateAdapter.getItem(orderStateAdapter.getSelectPosition()).getContent());
                orderStateDialog.dismiss();
            }
        });
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

    /**
     * 3.7.1.入库信息列表接口
     */
    @Override
    public void load() {
      String s =  mGson.toJson(new JsGetPurchaseList(mUser.getBranch_id(),getText(tvOrder),getText(etMan),getText(tvStartTime),getText(tvEndTime),10,String.valueOf(currentPage),TextUtils.isEmpty(getText(tvState))?"":String.valueOf(orderStateAdapter.getItem(orderStateAdapter.getSelectPosition()).getId())));

        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getOrderInput("GetPurchaseList",s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<OrderInput>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<OrderInput> orderInputBaseBean) {
                        loadAdapter.addAll(orderInputBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<OrderInput> orderInputBaseBean) {
                        check(orderInputBaseBean);
//                        ArrayList<OrderInput> orderInputs = new ArrayList<>();
//                        orderInputs.add(new OrderInput(2605,"S17010418492900","2017-01-04","安宝乐总部","在线入库","完成采集"));
//                        loadAdapter.addAll(orderInputs);
                    }
                });
    }

    private void initData() {
        orderStateList = new ArrayList<>();
        orderStateList.add(new OrderState(-1, "审核不通过"));
        orderStateList.add(new OrderState(0, "待审核"));
        orderStateList.add(new OrderState(1, "等待采集"));
        orderStateList.add(new OrderState(2, "正在采集"));
        orderStateList.add(new OrderState(3, "完成采集"));
        orderStateList.add(new OrderState(4, "签收入库"));
    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.tv_state})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_time:
                startDataDialog.show();
                break;
            case R.id.tv_end_time:
                endDataDialog.show();
                break;
            case R.id.tv_state:
                orderStateDialog.show();
                break;
        }
    }


    @OnItemClick(R.id.mListView)
    public  void onItemClick(int position){
        Intent intent = new Intent(mContext, InputInfoActivity.class);
        intent.putExtra(IntentConfig.SALEID, (((OrderInputAdapter) loadAdapter).getItem(position).getID()));
        startActivity(intent);
    }
}
