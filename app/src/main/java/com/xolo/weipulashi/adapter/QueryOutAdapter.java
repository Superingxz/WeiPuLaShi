package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.utils.adapterUtils.ComRecyclerViewHolder;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */

public class QueryOutAdapter extends CommenAdapter<OutOrderInfo> {
    public QueryOutAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, OutOrderInfo outOrderInfo) {
        holder.setText(R.id.tv_number,String.valueOf(position+1));
        holder.setText(R.id.tv_jiGou,outOrderInfo.getToBranchName());
        holder.setText(R.id.tv_time,outOrderInfo.getWorkDate());
        holder.setText(R.id.tv_order,outOrderInfo.getSaleCode());
        holder.setText(R.id.tv_state,outOrderInfo.getState());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_query_out;
    }
}
