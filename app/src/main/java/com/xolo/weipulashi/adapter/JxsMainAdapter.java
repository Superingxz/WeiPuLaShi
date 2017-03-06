package com.xolo.weipulashi.adapter;

import android.content.Context;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.Menu;
import com.xolo.weipulashi.utils.adapterUtils.ComRecyclerViewHolder;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class JxsMainAdapter extends CommenRecycleAdapter<Menu> {
    public JxsMainAdapter(Context context) {
        super(context);
    }

    @Override
    protected void setView(ComRecyclerViewHolder holder, int position, Menu menu) {
        holder.setImage(R.id.iv,menu.getSrc());
        holder.setText(R.id.tv,menu.getTitle());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_gv_main;
    }
}
