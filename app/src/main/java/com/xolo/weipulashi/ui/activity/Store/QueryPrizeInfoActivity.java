package com.xolo.weipulashi.ui.activity.Store;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xolo.weipulashi.R;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.DuiJiangRecord;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.HashMap;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 会员兑换记录详情
 */
public class QueryPrizeInfoActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_jiGou)
    TextView tvJiGou;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_ToTalIntegralValue)
    TextView tvToTalIntegralValue;
    @BindView(R.id.tv_number)
    ImageView iv;
    @BindView(R.id.tv_ProductCode)
    TextView tvProductCode;
    @BindView(R.id.tv_ProductName)
    TextView tvProductName;
    @BindView(R.id.tv_Integral)
    TextView tvIntegral;
    @BindView(R.id.tv_qty)
    TextView tvQty;
    private DuiJiangRecord duijiangRecord;

    @Override
    public int getLayoutId() {
        return R.layout.activity_query_prize_info;
    }

    @Override
    public void initView() {
        initIntent();
        GetAssistProductInfo();
    }

    private void initIntent() {
        Intent intent = getIntent();
        duijiangRecord = (DuiJiangRecord) intent.getParcelableExtra(IntentConfig.DUIJIANGRECORD);

        tvName.setText(duijiangRecord.getRealName());
        tvJiGou.setText(duijiangRecord.getName());
        tvPhone.setText(duijiangRecord.getPhone());
        tvWorkDate.setText(duijiangRecord.getWorkDate());
        tvToTalIntegralValue.setText(String.valueOf(duijiangRecord.getIntegralValue()));
    }

    /**
     * 3.9.7.协助兑换订单产品详情接口
     */
    private void  GetAssistProductInfo(){
        final HashMap<String, String> map = new HashMap<>();
        map.put("AssistId", String.valueOf(duijiangRecord.getID()));

        RetrofitManager.getApiInstant()
                .getProduce("GetAssistProductInfo",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Produce> produceBaseBean) {
                        Produce produce = produceBaseBean.getDt().get(0);
                        tvProductCode.setText(produce.getProductCode());
                        tvProductName.setText(produce.getProductName());
                        tvQty.setText(produce.getQty());
                        tvIntegral.setText(produce.getIntegral());

                        Picasso.with(mContext).load(produce.getImgPath()).into(iv);
                    }

                    @Override
                    public void onError(BaseBean<Produce> produceBaseBean) {
                        ToastUtil.showLong(mContext,produceBaseBean.getMsg());
                    }
                });
    }

}
