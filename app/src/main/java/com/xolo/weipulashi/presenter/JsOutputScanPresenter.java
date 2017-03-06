package com.xolo.weipulashi.presenter;

import android.text.TextUtils;

import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BasePresenter;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.json.JsKDSalesScan;
import com.xolo.weipulashi.bean.json.JsSaleId;
import com.xolo.weipulashi.bean.json.JsSalesCodeCancel;
import com.xolo.weipulashi.bean.json.JsUserInfo;
import com.xolo.weipulashi.interfaceView.JsOutputScanView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/7.
 */

public class JsOutputScanPresenter extends BasePresenter<JsOutputScanView> {

    private Order orderSelect;
    private OutOrderInfo   outOrderInfo;

    @Override
    public void onAttached() {
    }

    public void setOrderSelect(Order orderSelect) {
        this.orderSelect = orderSelect;
        getSaleInfo(String.valueOf(orderSelect.getID()));
    }

    /**
     * 3.4.1.获取出库单单号接口
     */
    public  void  getSaleCodeList(){
        RetrofitManager.getApiInstant()
                .getOrder("GetSaleCodeList",mGson.toJson(new JsUserInfo(String.valueOf(mUser.getBranch_id()))))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExSubscriber<BaseBean<Order>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Order> orderBaseBean) {
                        mView.setOrderDialogDt(orderBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Order> orderBaseBean) {
//                        ArrayList<Order> orders = new ArrayList<>();
//                        orders.add(new Order("S654789221",2605));
//                        mView.setOrderDialogDt(orders);
                        ToastUtil.showLong(mContext,orderBaseBean.getMsg());
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
                        outOrderInfo = outOrderInfoBaseBean.getDt().get(0);
                        mView.setOrderInfo(outOrderInfo);
                    }

                    @Override
                    public void onError(BaseBean<OutOrderInfo> outOrderInfoBaseBean) {
                        ToastUtil.showLong(mContext,outOrderInfoBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.4.5.出库扫码接口
     */
    public void kDSalesScan(String barcode){
        if (orderSelect == null){
            ToastUtil.showShort(mContext,"请选择单号");
            return;
        }

          RetrofitManager.getApiInstant()
                  .getProduce("KDSalesScan",mGson.toJson(new JsKDSalesScan(orderSelect.getID(),barcode,mUser.getEmployeeID())))
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                      @Override
                      public void onSuccess(BaseBean<Produce> produceBaseBean) {
                                 mView.setProduceDt(produceBaseBean.getDt());
                      }

                      @Override
                      public void onError(BaseBean<Produce> produceBaseBean) {
                             ToastUtil.showLong(mContext,produceBaseBean.getMsg());
                      }
                  });
    }

    /**
     * 3.4.6.完成发货接口
     */
    public void  salesSend(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Sale_ID",String.valueOf(orderSelect.getID()));

        RetrofitManager.getApiInstant()
                .getBaseBean("SalesSend",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        ToastUtil.showShort(mContext,"发货成功");
                        mView.finish();
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }
                });
    }

    /**
     * 3.4.7.扫码撤消出库条码接口
     */
    public void salesCodeCancel(final String barCode){
        RetrofitManager.getApiInstant()
                .getBaseBean("SalesCodeCancel",mGson.toJson(new JsSalesCodeCancel(orderSelect.getID(),barCode,mUser.getEmployeeID())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        getSaleProductList();
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }
                });
    }


    /**
     * 3.4.4.出库单产品明细列表接口
     */
    public void  getSaleProductList(){
        RetrofitManager.getApiInstant()
                .getProduce("GetSaleProductList",mGson.toJson(new JsSaleId(String.valueOf(orderSelect.getID()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Produce> outProduceBaseBean) {
                               mView.setProduceDt(outProduceBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Produce> outProduceBaseBean) {
                        ToastUtil.showShort(mContext,outProduceBaseBean.getMsg());
                    }
                });
    }

}
