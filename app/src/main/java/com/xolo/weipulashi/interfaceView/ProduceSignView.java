package com.xolo.weipulashi.interfaceView;

import android.support.v7.widget.RecyclerView;

import com.xolo.weipulashi.base.BaseView;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */

public interface ProduceSignView extends BaseView{
            void setOrderDialogDt(List<Order> list);
            void setWareHouseDialogDt(List<WareHouse> list);
    void setRecycleviewDt(List<SignCheck> list);
}
