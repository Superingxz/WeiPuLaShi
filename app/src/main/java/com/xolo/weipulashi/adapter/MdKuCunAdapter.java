package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.StockNum;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MdKuCunAdapter extends CommenAdapter<StockNum> {
    public MdKuCunAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, StockNum stockNum) {
        holder.setText(R.id.tv_ProductName,stockNum.getProductName());
        holder.setText(R.id.tv_bianma,stockNum.getProductCode());
        holder.setText(R.id.tv_guige, String.valueOf(stockNum.getBz()));
        holder.setText(R.id.tv_gqty, String.valueOf(stockNum.getNum()));
        holder.setText(R.id.tv_xqty, String.valueOf(stockNum.getBZNum()));
        holder.setText(R.id.tv_number, String.valueOf(position+1));
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_md_ku_cun;
    }
}
