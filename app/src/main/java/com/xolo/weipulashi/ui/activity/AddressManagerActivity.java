package com.xolo.weipulashi.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.AddressAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.Address;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.activity.Store.AddAddressActivity;
import com.xolo.weipulashi.ui.dialog.MsgDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 管理收货地址
 */
public class AddressManagerActivity extends BaseActivity implements AddressAdapter.AddressAdapterInterface{

    @BindView(R.id.titleview)
    TitleView titleview;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.add_address)
    Button addAddress;
    private boolean isEdit;

    private String memberId;
    private AddressAdapter addressAdapter;

    private MsgDialog closeDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_manager;
    }

    @Override
    public void initView() {
        initIntent();
        initListView();
        initTitle();

        getMemberTakeAddress();
    }

    private void initListView() {
        addressAdapter = new AddressAdapter(mContext);
        mListView.setAdapter(addressAdapter);

    }

    private void initTitle() {
        titleview.setRightText("管理");
        titleview.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addressAdapter.isEdit()) {
                    addressAdapter.setEdit(false);
                    titleview.setRightText("管理");
                    addAddress.setVisibility(View.GONE);
                } else {
                    addressAdapter.setEdit(true);
                    titleview.setRightText("完成");
                    addAddress.setVisibility(View.VISIBLE);
                }

                addressAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initIntent() {
        memberId = getIntent().getStringExtra(IntentConfig.MENBER_ID);
    }

    @OnClick(R.id.add_address)
    public void onClick() {
        Intent intent = new Intent(mContext, AddAddressActivity.class);
        intent.putExtra(IntentConfig.MENBER_ID,memberId);
        startActivityForResult(intent,99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            getMemberTakeAddress();
        }
    }

    /**
     * 3.9.13.获取会员收货地址接口
     */
    private void getMemberTakeAddress(){
        HashMap<String, String> map = new HashMap<>();
        map.put("MemberId",memberId);

        RetrofitManager.getApiInstant()
                .getAddress("GetMemberTakeAddress",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Address>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Address> addressBaseBean) {
                           addressAdapter.upDt(addressBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Address> addressBaseBean) {
                        ToastUtil.showLong(mContext,addressBaseBean.getMsg());
                    }
                });
    }


    @OnItemClick(R.id.mListView)
    public  void   onItemClick(int position){
        Address item = addressAdapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra(IntentConfig.ADDRESS,item);
        setResult(RESULT_OK,intent);
        finish();
    }

    /**
     * 3.9.15.删除会员收货地址接口
     */
    public void  deleteMemberTakeAddress(String takeId){
        HashMap<String, String> map = new HashMap<>();
        map.put("TakeId",takeId);


        RetrofitManager.getApiInstant()
                .getBaseBean("DeleteMemberTakeAddress",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean bean) {
                        ToastUtil.showLong(mContext,bean.getMsg());
                        getMemberTakeAddress();
                    }

                    @Override
                    public void onError(BaseBean bean) {
ToastUtil.showLong(mContext,bean.getMsg());
                    }
                });
    }

    /**
     * 3.9.16.设置会员收货地址接口
     */
    public void  setTakeAddressDefault(String takeId){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("TakeId",Integer.valueOf(takeId));
        map.put("MemberId",Integer.valueOf(memberId));

        String s = mGson.toJson(map);
        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getBaseBean("SetTakeAddressDefault",s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean bean) {
                        ToastUtil.showLong(mContext,bean.getMsg());
                        getMemberTakeAddress();
                    }

                    @Override
                    public void onError(BaseBean bean) {
ToastUtil.showLong(mContext,bean.getMsg());
                        addressAdapter.notifyDataSetChanged();
                    }
                });
    }

}
