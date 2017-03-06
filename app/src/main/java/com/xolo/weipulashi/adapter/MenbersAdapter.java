package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Menbers;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/10.
 */

public class MenbersAdapter extends CommenAdapter<Menbers> {
    public MenbersAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, Menbers menbers) {
        holder.loadUrl(R.id.iv,menbers.getHeadImage());
            holder.setText(R.id.tv_name,menbers.getRealName());
            holder.setText(R.id.tv_phone,menbers.getPhone());
            holder.setText(R.id.tv_store,menbers.getBranchName());
            holder.setText(R.id.tv_jf, String.valueOf(menbers.getIntegralValue()));
            holder.setText(R.id.tv_integralNum, String.valueOf(menbers.getIntegralNum()));
            holder.setText(R.id.tv_exchangeNum, String.valueOf(menbers.getExchangeNum()));
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_menbers;
    }
}
