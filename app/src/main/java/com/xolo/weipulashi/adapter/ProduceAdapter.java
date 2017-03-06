package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ProduceAdapter extends CommenAdapter<Produce> {
    public ProduceAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, Produce outProduce) {
        holder.setText(R.id.tv_number,outProduce.getProductCode());
        holder.setText(R.id.tv_name,outProduce.getProductName());
        holder.setText(R.id.tv_duanci,outProduce.getDc());
        holder.setText(R.id.tv_guige,outProduce.getBz());
        holder.setText(R.id.tv_qty,String.valueOf(outProduce.getQty()));
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_outorder_produce;
    }
}
