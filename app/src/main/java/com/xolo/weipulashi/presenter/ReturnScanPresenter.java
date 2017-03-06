package com.xolo.weipulashi.presenter;

import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.BasePresenter;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.ReturnInfo;
import com.xolo.weipulashi.bean.json.JsKDSalesScan;
import com.xolo.weipulashi.bean.json.JsReturnId;
import com.xolo.weipulashi.bean.json.JsReturnScan;
import com.xolo.weipulashi.bean.json.JsSaleId;
import com.xolo.weipulashi.bean.json.JsSalesCodeCancel;
import com.xolo.weipulashi.bean.json.JsUserInfo;
import com.xolo.weipulashi.interfaceView.JsOutputScanView;
import com.xolo.weipulashi.interfaceView.ReturnScanView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ReturnScanPresenter extends BasePresenter<ReturnScanView> {

    private Order orderSelect;


    @Override
    public void onAttached() {

    }

    public void setOrderSelect(Order orderSelect) {
        this.orderSelect = orderSelect;
        getRerurnInfo(String.valueOf(orderSelect.getID()));
    }

    /**
     * 3.5.1.获取退货单单号接口
     */
    public  void  getSaleCodeList(){
        RetrofitManager.getApiInstant()
                .getOrder("GetRerurnNo",mGson.toJson(new JsUserInfo(String.valueOf(mUser.getBranch_id()))))
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
//                        orders.add(new Order("S654789221",49));
//                       mView.setOrderDialogDt(orders);
                        ToastUtil.showLong(mContext,orderBaseBean.getMsg());
                    }
                });
    }


    /**
     * 3.5.5.退货扫码
     */
    public void kDSalesScan(String barcode){
        if (orderSelect == null){
            ToastUtil.showShort(mContext,"请选择单号");
            return;
        }

          RetrofitManager.getApiInstant()
                  .getProduce("ReturnScan",mGson.toJson(new JsReturnScan(orderSelect.getID(),barcode,mUser.getEmployeeID())))
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                      @Override
                      public void onSuccess(BaseBean<Produce> outProduceBaseBean) {
                                  mView.setProduceDt(outProduceBaseBean.getDt());
                      }

                      @Override
                      public void onError(BaseBean<Produce> outProduceBaseBean) {
                          ToastUtil.showLong(mContext,outProduceBaseBean.getMsg());
                      }
                  });
    }


    /**
     * 3.5.6.完成退货
     */
    public void  salesSend(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Return_ID",String.valueOf(orderSelect.getID()));
        map.put("Employee_ID", String.valueOf(mUser.getEmployeeID()));

        String s = mGson.toJson(map);
        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getBaseBean("ReturnFinish",s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,"退货成功");
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
                .getBaseBean("ReturnCancelScan",mGson.toJson(new JsSalesCodeCancel(barCode,orderSelect.getID(),mUser.getEmployeeID())))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean baseBean) {
                        getRerurnProductList();
                        ToastUtil.showLong(mContext,baseBean.getMsg());
                    }

                    @Override
                    public void onError(BaseBean baseBean) {
                        ToastUtil.showLong(mContext,baseBean.getMsg());
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
                        mView.setOrderInfo(returnInfoBaseBean.getDt().get(0));
                    }

                    @Override
                    public void onError(BaseBean<ReturnInfo> returnInfoBaseBean) {
                        ToastUtil.showLong(mContext,returnInfoBaseBean.getMsg());
                    }
                });
    }


    /**
     * 3.4.4.出库单产品明细列表接口
     */
    public void  getRerurnProductList(){
        RetrofitManager.getApiInstant()
                .getProduce("GetRerurnProductList",mGson.toJson(new JsReturnId(String.valueOf(orderSelect.getID()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Produce>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Produce> outProduceBaseBean) {
                        mView.setProduceDt(outProduceBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<Produce> outProduceBaseBean) {
                        if (!outProduceBaseBean.getRes().equals("0008")){
                            ToastUtil.showLong(mContext,outProduceBaseBean.getMsg());
                        }
                    }
                });
    }

}
