package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.utils.adapterUtils.ComRecyclerViewHolder;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */

public class SignInfoAdapter extends CommenRecycleAdapter<SignCheck> {
    public SignInfoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void setView(ComRecyclerViewHolder holder, int position, SignCheck signCheck) {
        holder.setText(R.id.tv_number,String.valueOf(position+1));
        holder.setText(R.id.tv_jiGou,signCheck.getBranchName());
        holder.setText(R.id.tv_time,signCheck.getWorkDate());
        holder.setText(R.id.tv_order,signCheck.getSaleCode());
        holder.setText(R.id.tv_state,signCheck.getState());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_sign_input;
    }
}
