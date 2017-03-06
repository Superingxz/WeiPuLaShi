package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.TrackInfo;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

/**
 * Created by Administrator on 2017/2/16.
 */

public class TrackInfoAdapter extends CommenAdapter<TrackInfo.TrackListBean> {
    public TrackInfoAdapter(Context context) {
        super(context);
    }

    @Override
    public void setView(ViewHolder holder, int position, TrackInfo.TrackListBean trackInfo) {
                 holder.setText(R.id.tv_jiGou,trackInfo.getBranchName());
        holder.setText(R.id.tv_type,trackInfo.getType());
        holder.setText(R.id.tv_data,trackInfo.getWorkDate());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_tarckinfo;
    }
}
