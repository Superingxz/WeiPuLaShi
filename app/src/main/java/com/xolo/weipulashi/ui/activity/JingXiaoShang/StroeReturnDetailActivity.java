package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.ReturnInfo;
import com.xolo.weipulashi.bean.json.JsReturnId;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 门店退货明细详情
 */
public class StroeReturnDetailActivity extends BaseActivity {

    @BindView(R.id.tv_RerurnCode)
    TextView tvRerurnCode;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_RerurnName)
    TextView tvRerurnName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_CheckName)
    TextView tvCheckName;
    @BindView(R.id.tv_CheckDate)
    TextView tvCheckDate;
    @BindView(R.id.mListView)
    ListView mListView;

    private ProduceAdapter outProduceAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stroe_return_detail;
    }


    @Override
    public void initView() {
        initListView();

        String returnId = getIntent().getStringExtra(IntentConfig.RETURNID);
        getRerurnInfo(returnId);
    }

    private void initListView() {
        outProduceAdapter = new ProduceAdapter(mContext);
        mListView.setAdapter(outProduceAdapter);
    }

    /**
     * 3.4.4.出库单产品明细列表接口
     * @param saleId
     */
    public void  getRerurnProductList(final String saleId){
        RetrofitManager.getApiInstant()
                .getProduce("GetRerurnProductList",mGson.toJson(new JsReturnId(saleId)))
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
     * 3.5.3.退货单详情接口
     */
    public void getRerurnInfo(final String saleId){
        RetrofitManager.getApiInstant()
                .getReturnInfo("GetRerurnInfo",mGson.toJson(new JsReturnId(saleId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<ReturnInfo>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<ReturnInfo> returnInfoBaseBean) {
                        setOrderInfo(returnInfoBaseBean.getDt().get(0));

                        getRerurnProductList(saleId);
                    }

                    @Override
                    public void onError(BaseBean<ReturnInfo> returnInfoBaseBean) {
                        ToastUtil.showLong(mContext,returnInfoBaseBean.getMsg());
                    }
                });
    }

    public void setOrderInfo(ReturnInfo info) {
        tvRerurnCode.setText(info.getRerurnCode());
        tvRerurnName.setText(info.getRerurnName());
        tvCheckName.setText(info.getCheckName());
        tvCheckDate.setText(info.getCheckDate());
        tvWorkDate.setText(info.getWorkDate());
        tvState.setText(info.getState());
    }

}
