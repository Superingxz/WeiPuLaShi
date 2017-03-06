package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Area;
import com.xolo.weipulashi.bean.get.City;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class AreaAdapter extends CommenAdapter<Area> implements AdapterView.OnItemClickListener{
    private int selectPosition;

    public AreaAdapter(Context context) {
        super(context);
    }


    @Override
    public void upDt(List<Area> list) {
        mList = list;
        mList.get(0).setSelect(true);
        selectPosition = 0;
        notifyDataSetChanged();
    }

    @Override
    public void setView(ViewHolder holder, final int position, final Area wareHouse) {
        if (wareHouse.isSelect()){
            holder.setCheck(R.id.rb,true);
        }else {
            holder.setCheck(R.id.rb,false);
        }

        holder.setText(R.id.tv_content,wareHouse.getAreaName());
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
