package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.MemberJFList;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by wei on 2017/2/16.
 */

public class MdMembersJFListAdapter extends CommenAdapter<MemberJFList> {
    public MdMembersJFListAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, MemberJFList memberJFList) {
        holder.setText(R.id.tv_number,String.valueOf(position+1));
        holder.setText(R.id.JFcode,memberJFList.getJFcode());
        holder.setText(R.id.ProductName,memberJFList.getProductName());
        holder.setText(R.id.BranchName,memberJFList.getBranchName());
        holder.setText(R.id.WorkDate,memberJFList.getWorkDate());
        holder.setText(R.id.Type,memberJFList.getType());
        holder.setText(R.id.GainIntegral,String.valueOf(memberJFList.getGainIntegral()));
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_md_members_jf_list;
    }
}
