package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/9.
 */

public class OutScanAdapter extends CommenAdapter<Produce> {
    public OutScanAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, Produce produce) {
        String qty = produce.getQty();
        String scanQty = produce.getScanQty();
        int yQty = Integer.parseInt(qty) - Integer.parseInt(scanQty);

        holder.setText(R.id.tv_ProductName,produce.getProductName());
             holder.setText(R.id.tv_ScanQty, scanQty);
             holder.setText(R.id.tv_XQty,produce.getXQty());
             holder.setText(R.id.tv_SQty, qty);
             holder.setText(R.id.tv_YQty, String.valueOf(yQty));
        holder.setText(R.id.tv_number,(position+1)+".");
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_out_scan;
    }
}
