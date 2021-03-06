package com.xolo.weipulashi.interfaceView;

import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.base.BaseView;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.WareHouse;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface SignInfoView extends BaseView {
    void  setOrderInfo(OutOrderInfo info);
    void setWareHouseDialogDt(List<WareHouse> list);
    void setLvDt(List<Produce> adapter);
    void setResult();
}
