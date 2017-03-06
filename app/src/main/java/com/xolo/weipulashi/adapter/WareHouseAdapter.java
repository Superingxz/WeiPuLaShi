package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.utils.adapterUtils.ComRecyclerViewHolder;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.List;

import static android.R.attr.order;
import static com.xolo.weipulashi.R.id.rb;

/**
 * Created by Administrator on 2017/2/7.
 */

public class WareHouseAdapter extends CommenAdapter<WareHouse> implements AdapterView.OnItemClickListener{
    private int selectPosition;

    public WareHouseAdapter(Context context) {
        super(context);
    }


    @Override
    public void upDt(List<WareHouse> list) {
        mList = list;
        mList.get(0).setSelect(true);
        selectPosition = 0;
        notifyDataSetChanged();
    }

    @Override
    public void setView(ViewHolder holder, final int position, final WareHouse wareHouse) {
        if (wareHouse.isSelect()){
            holder.setCheck(R.id.rb,true);
        }else {
            holder.setCheck(R.id.rb,false);
        }

        holder.setText(R.id.tv_content,wareHouse.getName());
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_dialog_rb_select;
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != selectPosition){
            mList.get(selectPosition).setSelect(false);
            mList.get(position).setSelect(true);
            selectPosition = position;
            notifyDataSetChanged();
        }
    }

}
