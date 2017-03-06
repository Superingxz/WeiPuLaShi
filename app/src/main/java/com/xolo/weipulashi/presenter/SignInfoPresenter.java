package com.xolo.weipulashi.presenter;

import android.app.Activity;

import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BasePresenter;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.bean.json.JsSaleId;
import com.xolo.weipulashi.bean.json.JsQSScanCheckAll;
import com.xolo.weipulashi.bean.json.JsUserInfo;
import com.xolo.weipulashi.interfaceView.SignInfoView;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/7.
 */

public class SignInfoPresenter extends BasePresenter<SignInfoView> {

    private OutOrderInfo   outOrderInfo;
    private WareHouse  wareHouse;

    @Override
    public void onAttached() {
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
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
                        outOrderInfo = outOrderInfoBaseBean.getDt().get(0);
                        mView.setOrderInfo(outOrderInfo);

                        getSaleProductList(saleId);
                    }

                    @Override
                    public void onError(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
                        ToastUtil.showLong(mContext,outOrderInfoBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.2.2.仓库信息列表接口
     * 942
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

    /**
     * 3.4.4.出库单产品明细列表接口
     * @param saleId
     */
    public void  getSaleProductList(String saleId){
        RetrofitManager.getApiInstant()
                .getProduce("GetSaleProductList",mGson.toJson(new JsSaleId(saleId)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Produce> outProduceBaseBean) {
                           mView.setLvDt(outProduceBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Produce> outProduceBaseBean) {
                        ToastUtil.showLong(mContext,outProduceBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.3.4.扫码签收接口（扫一个码签收整张单）
     */
   public void qSScanCheckAll(String barCode){
       if (wareHouse == null){
           ToastUtil.showLong(mContext,"请选择仓库");
           return;
       }
       RetrofitManager.getApiInstant()
               .qSScanCheckAll("QSScanCheckAll",mGson.toJson(new JsQSScanCheckAll(String.valueOf(outOrderInfo.getID()),barCode,wareHouse.getId(),mUser.getEmployeeID())))
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .subscribe(new ExSubscriber<BaseBean>(mContext) {
                   @Override
                   public void onSuccess(BaseBean baseBean) {
                         ToastUtil.showLong(mContext,"签收成功");
                       mView.setResult();
                   }

                   @Override
                   public void onError(BaseBean baseBean) {
                       ToastUtil.showLong(mContext,baseBean.getMsg());
//                       mView.setResult();
                   }
               });
   }

}
