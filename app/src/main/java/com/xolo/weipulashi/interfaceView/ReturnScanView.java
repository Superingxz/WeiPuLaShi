package com.xolo.weipulashi.interfaceView;

import com.xolo.weipulashi.base.BaseView;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.ReturnInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface ReturnScanView extends BaseView{
    void setOrderDialogDt(List<Order> list);
    void setProduceDt(List<Produce> list);
    void  setOrderInfo(ReturnInfo info);
}
