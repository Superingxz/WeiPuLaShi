package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Province;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ProvinceAdapter extends CommenAdapter<Province> implements AdapterView.OnItemClickListener{
    private int selectPosition;

    public ProvinceAdapter(Context context) {
        super(context);
    }


    @Override
    public void upDt(List<Province> list) {
        mList = list;
        mList.get(0).setSelect(true);
        selectPosition = 0;
        notifyDataSetChanged();
    }

    @Override
    public void setView(ViewHolder holder, final int position, final Province wareHouse) {
        if (wareHouse.isSelect()){
            holder.setCheck(R.id.rb,true);
        }else {
            holder.setCheck(R.id.rb,false);
        }

        holder.setText(R.id.tv_content,wareHouse.getProvinceName());
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
