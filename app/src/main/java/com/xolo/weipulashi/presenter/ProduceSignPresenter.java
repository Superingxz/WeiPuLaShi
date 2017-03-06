package com.xolo.weipulashi.presenter;

import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BasePresenter;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.bean.json.JsCheckSaleAll;
import com.xolo.weipulashi.bean.json.JsSaleCheckList;
import com.xolo.weipulashi.bean.json.JsUserInfo;
import com.xolo.weipulashi.interfaceView.ProduceSignView;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/6.
 */

public class ProduceSignPresenter extends BasePresenter<ProduceSignView> {

    @Override
    public void onAttached() {
    }

    /**
     * 3.3.1.需要签收的出库单号列表（签收）信息接口
     * GetCheckSaleCodeList
     */
    public void  getCheckSaleCodeList(){
        RetrofitManager.getApiInstant()
                .getOrder("GetCheckSaleCodeList",mGson.toJson(new JsUserInfo(String.valueOf(mUser.getBranch_id()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Order>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Order> orderBaseBean) {
                        mView.setOrderDialogDt(orderBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Order> orderBaseBean) {
                        ToastUtil.showLong(mContext,orderBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.3.2.获取签收信息列表接口
     */
    public void getSaleCheckList(String saleCode ,String startDate,String EndDate){
        RetrofitManager.getApiInstant()
                .getSignCheck("GetSaleCheckList",mGson.toJson(new JsSaleCheckList(String.valueOf(mUser.getBranch_id()),saleCode,startDate,EndDate,"100","1")))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<SignCheck>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<SignCheck> signCheckBaseBean) {
                        mView.setRecycleviewDt(signCheckBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<SignCheck> signCheckBaseBean) {
//                        ArrayList<SignCheck> signChecks = new ArrayList<>();
//                        signChecks.add(new SignCheck(2605,"S17010418492900","2017-01-04","安宝乐总部","已发货"));
//                        mView.setRecycleviewDt(signChecks);
                        ToastUtil.showLong(mContext,signCheckBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.3.3.一键签收接口
     */
    public void checkSalesALL(String wareHouseId){
        RetrofitManager.getApiInstant()
                .getBaseBean("CheckSalesALL",mGson.toJson(new JsCheckSaleAll(String.valueOf(mUser.getBranch_id()),wareHouseId,String.valueOf(mUser.getEmployeeID()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                      ToastUtil.showLong(mContext,"签收成功");
                        mContext.finish();
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }
                });
    }

    /**
     * 3.2.2.仓库信息列表接口
     */
    public void getWareHouse(){
        RetrofitManager.getApiInstant()
                .getWareHouse("GetWareHouseList",mGson.toJson(new JsUserInfo(String.valueOf(mUser.getBranch_id()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<WareHouse>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<WareHouse> wareHouseBaseBean) {
                           mView.setWareHouseDialogDt(wareHouseBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<WareHouse> wareHouseBaseBean) {
                        ToastUtil.showLong(mContext,wareHouseBaseBean.getMsg());
                    }
                });
    }

}
