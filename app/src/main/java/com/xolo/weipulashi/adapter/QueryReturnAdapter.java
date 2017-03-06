package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.ReturnInfo;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/6.
 */

public class QueryReturnAdapter extends CommenAdapter<ReturnInfo> {
    public QueryReturnAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, ReturnInfo returnInfo) {
        holder.setText(R.id.tv_number,String.valueOf(position+1));
        holder.setText(R.id.tv_jiGou,returnInfo.getRerurnName());
        holder.setText(R.id.tv_time,returnInfo.getWorkDate());
        holder.setText(R.id.tv_order,returnInfo.getRerurnCode());
        holder.setText(R.id.tv_state,returnInfo.getState());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_query_return;
    }
}
