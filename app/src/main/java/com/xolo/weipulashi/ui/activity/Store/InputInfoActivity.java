package com.xolo.weipulashi.ui.activity.Store;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.json.JsPurchaseId;
import com.xolo.weipulashi.bean.json.JsSaleId;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.id;

public class InputInfoActivity extends BaseActivity {

    @BindView(R.id.tv_Code)
    TextView tvCode;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_CheckEmpName)
    TextView tvCheckEmpName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_EmpName)
    TextView tvEmpName;
    @BindView(R.id.tv_CheckDate)
    TextView tvCheckDate;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.mListView)
   ListView mListView;

    private  int id;

    private ProduceAdapter outProduceAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_info;
    }

    @Override
    public void initView() {
        InitIntent();
        initListView();

        getSaleInfo(id);
    }

    private void InitIntent() {
        Intent intent = getIntent();
       id = intent.getIntExtra(IntentConfig.SALEID, 0);
    }

    private void initListView() {
        outProduceAdapter = new ProduceAdapter(mContext);
        mListView.setAdapter(outProduceAdapter);
    }


    /**
     * 3.7.2.入库单详情接口
     */
    public void getSaleInfo(final int saleId){
        RetrofitManager.getApiInstant()
                .getOutOrederInfo("GetPurchaseInfo",mGson.toJson(new JsPurchaseId(saleId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<OutOrderInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
                        setOrderInfo(outOrderInfoBaseBean.getDt().get(0));

                        getSaleProductList(saleId);
                    }

                    @Override
                    public void onError(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
                        ToastUtil.showLong(mContext,outOrderInfoBaseBean.getMsg());
                    }
                });
    }


    /**
     * 3.7.3.入库单产品明细列表接口
     * @param saleId
     */
    public void  getSaleProductList(final int saleId){
        RetrofitManager.getApiInstant()
                .getProduce("GetPurchaseProductList",mGson.toJson(new JsPurchaseId(saleId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Produce> outProduceBaseBean) {
                        outProduceAdapter.upDt(outProduceBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Produce> outProduceBaseBean) {
                        ToastUtil.showLong(mContext,outProduceBaseBean.getMsg());
                    }
                });
    }

    public void setOrderInfo(OutOrderInfo info) {
        tvCheckEmpName.setText(info.getCheckEmpName());
        tvCheckDate.setText(info.getCheckDate());
        tvWorkDate.setText(info.getWorkDate());
        tvState.setText(info.getState());
        tvCode.setText(info.getPurchaseCode());
        tvRemark.setText(info.getRemark());
        tvEmpName.setText(info.getEmpName());
    }
}
