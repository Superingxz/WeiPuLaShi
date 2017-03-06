package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.OrderInput;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderInputAdapter extends CommenAdapter<OrderInput> {
    public OrderInputAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, OrderInput orderInput) {
                  holder.setText(R.id.tv_order,orderInput.getPurchaseCode());
                  holder.setText(R.id.tv_number, String.valueOf(position+1));
                  holder.setText(R.id.tv_jiGou,orderInput.getPurchaseType());
                  holder.setText(R.id.tv_WorkDate,orderInput.getWorkDate());
                  holder.setText(R.id.tv_ScanEmpName,orderInput.getEmpName());
                  holder.setText(R.id.tv_state,orderInput.getState());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_order_input;
    }
}
