package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.DuiJiangRecord;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/16.
 */

public class DuiJiangRecordAdapter extends CommenAdapter<DuiJiangRecord> {
    public DuiJiangRecordAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, DuiJiangRecord duiJiangRecord) {
                       holder.setText(R.id.tv_number, String.valueOf(position+1));
        holder.setText(R.id.tv_name,duiJiangRecord.getRealName());
        holder.setText(R.id.tv_phone,duiJiangRecord.getPhone());
        holder.setText(R.id.tv_WorkDate,duiJiangRecord.getWorkDate());
        holder.setText(R.id.tv_ToTalIntegralValue, String.valueOf(duiJiangRecord.getIntegralValue()));
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_query_prize;
    }
}
