package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.json.JsSaleId;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 销售出库明细详情
 */
public class XsOutDetailActivity extends BaseActivity {

    @BindView(R.id.tv_SaleCode)
    TextView tvSaleCode;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_SalesName)
    TextView tvSalesName;
    @BindView(R.id.tv_Warehouse)
    TextView tvWarehouse;
    @BindView(R.id.tv_ToBranchName)
    TextView tvToBranchName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_CheckEmpName)
    TextView tvCheckEmpName;
    @BindView(R.id.tv_CheckDate)
    TextView tvCheckDate;
    @BindView(R.id.tv_ScanEmpName)
    TextView tvScanEmpName;
    @BindView(R.id.tv_ScanStatDate)
    TextView tvScanStatDate;
    @BindView(R.id.mListView)
    ListView mListView;

    private ProduceAdapter outProduceAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xs_out_detail;
    }

    @Override
    public void initView() {
        initListView();

        String saleID = getIntent().getStringExtra(IntentConfig.SALEID);
        getSaleInfo(saleID);
    }

    private void initListView() {
        outProduceAdapter = new ProduceAdapter(mContext);
        mListView.setAdapter(outProduceAdapter);
    }

    /**
     * 3.4.4.出库单产品明细列表接口
     * @param saleId
     */
    public void  getSaleProductList(final String saleId){
        RetrofitManager.getApiInstant()
                .getProduce("GetSaleProductList",mGson.toJson(new JsSaleId(saleId)))
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

    /**
     * 3.4.3.出库单详情接口
     */
    public void getSaleInfo(final String saleId){
        RetrofitManager.getApiInstant()
                .getOutOrederInfo("GetSaleInfo",mGson.toJson(new JsSaleId(saleId)))
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

    public void setOrderInfo(OutOrderInfo info) {
        tvCheckEmpName.setText(info.getCheckEmpName());
        tvCheckDate.setText(info.getCheckDate());
        tvScanEmpName.setText(info.getScanEmpName());
        tvScanStatDate.setText(info.getScanStatDate());
        tvSaleCode.setText(info.getSaleCode());
        tvWorkDate.setText(info.getWorkDate());
        tvSalesName.setText(info.getSalesName());
        tvWarehouse.setText(info.getWarehouse());
        tvToBranchName.setText(info.getToBranchName());
        tvState.setText(info.getState());
    }
}
