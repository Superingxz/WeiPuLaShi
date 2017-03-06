package com.xolo.weipulashi.ui.activity.Store;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.PrizeAdapter;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BaseCommenBean;
import com.xolo.weipulashi.base.LoadMoreActivity;
import com.xolo.weipulashi.bean.get.Address;
import com.xolo.weipulashi.bean.get.Duijiang;
import com.xolo.weipulashi.bean.get.Prize;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.activity.AddressManagerActivity;
import com.xolo.weipulashi.ui.dialog.PrizeDialog;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.CommenSubscriber;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.io.Serializable;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 门店协助兑换
 */
public class MdXzDhActivity extends LoadMoreActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.mListView)
    GridView mListView;
    @BindView(R.id.tv_LevelName)
    TextView tvLevelName;
    @BindView(R.id.tv_IntegralValue)
    TextView tvIntegralValue;
    @BindView(R.id.et_address)
    TextView etAddress;
    @BindView(R.id.activity_md_xz_store)
    LinearLayout activityMdXzStore;

    private PrizeDialog prizeDialog;

    private String currentPhone;

    private String menberID;

    private  Address mAddress;

    @Override
    public int getLayoutId() {
        return R.layout.activity_md_xz_store;
    }

    @Override
    public void initView() {
        loadAdapter = new PrizeAdapter(mContext);
        mListView.setAdapter(loadAdapter);
        mListView.setOnScrollListener(this);
    }

    /**
     * 3.9.4.兑换订单提交接口
     */
    private void getPrizeOrderSubmission(String prizeId, String orederQty, String integralValue,String addressId) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("PrizeId", prizeId);
        map.put("Phone", currentPhone);
        map.put("BranchId", String.valueOf(mUser.getBranch_id()));
        map.put("EmployeeID", String.valueOf(mUser.getEmployeeID()));
        map.put("OrderQty", orederQty);
        map.put("IntegralValue", integralValue);
        map.put("TakeId", addressId);

        String s = mGson.toJson(map);
        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getBaseBean("GetPrizeOrderSubmission", s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        ToastUtil.showLong(mContext, baseBean.getMsg());
                        loadFristPage();
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext, baseBean.getMsg());
                    }
                });
    }

    /**
     * 3.9.2.协助会员兑换奖品信息列表接口
     */
    @Override
    public void load() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("Phone", getText(etPhone));
        map.put("PageSize", String.valueOf(currentPage));
        map.put("PageIndex", "10");

        RetrofitManager.getApiInstant()
                .getDuijiang("AssistDuijiang", mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CommenSubscriber<BaseCommenBean<Duijiang>>(mContext) {
                    @Override
                    public void onSuccess(BaseCommenBean<Duijiang> duijiangBaseCommenBean) {
                        mAddress = null;
                        currentPhone = getText(etPhone);

                        Duijiang duijiang = duijiangBaseCommenBean.getT();
                        menberID = String.valueOf(duijiang.getId());
                        tvLevelName.setText(duijiang.getLevelName());
                        tvIntegralValue.setText(duijiang.getIntegralValue());

                        getTakeAddressDefault();

                        loadAdapter.addAll(duijiang.getPrize());

                    }

                    @Override
                    public void onError(BaseCommenBean<Duijiang> duijiangBaseCommenBean) {
                        if (duijiangBaseCommenBean.getRes().equals("0008")) {
                            isLoad = false;
                        }
                        ToastUtil.showLong(mContext, duijiangBaseCommenBean.getMsg());
                    }
                });
    }


    @OnItemClick(R.id.mListView)
    public void onItemClick(int position) {
        final Prize prize = (Prize) loadAdapter.getItem(position);

        prizeDialog = new PrizeDialog(mContext, prize.getImgPath(), prize.getPrizeName(), prize.getIntegralValue(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = prizeDialog.getQty();
                String address = prizeDialog.getAddress();
                if (TextUtils.isEmpty(address)){
                    ToastUtil.showLong(mContext,"请选择收货地址");
                    return;
                }

                getPrizeOrderSubmission(String.valueOf(prize.getId()), String.valueOf(qty), (int) Double.parseDouble(prize.getIntegralValue()) * qty + "", String.valueOf(mAddress.getID()));
                prizeDialog.dismiss();
            }
        });

        prizeDialog.show();

        if (mAddress !=null){
            prizeDialog.setAddress(mAddress.getAddress());
        }
    }

    @OnClick({R.id.et_address, R.id.btn_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_address:
                Intent intent = new Intent(mContext, AddressManagerActivity.class);
                intent.putExtra(IntentConfig.MENBER_ID,menberID);
                startActivityForResult(intent,99);
                break;
            case R.id.btn_query:
                if (TextUtils.isEmpty(getText(etPhone))) {
                    ToastUtil.showShort(mContext, "请输入会员电话号码");
                    return;
                }
                loadFristPage();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
          mAddress = (Address) data.getSerializableExtra(IntentConfig.ADDRESS);
//                  etAddress.setText(address.getAddress());
           
            prizeDialog.setAddress(mAddress.getAddress());
        }
    }

    /**
     * 3.9.17.获取会员默认收货地址接口
     */
    private void  getTakeAddressDefault(){
        HashMap<String, String> map = new HashMap<>();
        map.put("MemberId",menberID);

        RetrofitManager.getApiInstant()
                .getAddress("GetTakeAddressDefault",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Address>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Address> addressBaseBean) {
                        mAddress = addressBaseBean.getDt().get(0);
                    }

                    @Override
                    public void onError(BaseBean<Address> addressBaseBean) {

                    }
                });
    }

}
