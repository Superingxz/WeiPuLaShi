package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/9.
 */

public class ReturnScanAdapter extends CommenAdapter<Produce> {
    public ReturnScanAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, Produce produce) {
             holder.setText(R.id.tv_ProductName,produce.getProductName());
//             holder.setText(R.id.tv_ScanQty,produce.getScanQty());
//             holder.setText(R.id.tv_XQty,produce.getXQty());
             holder.setText(R.id.tv_SQty, produce.getQty());
        holder.setText(R.id.tv_number,(position+1)+".");
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_return_scan;
    }
}
