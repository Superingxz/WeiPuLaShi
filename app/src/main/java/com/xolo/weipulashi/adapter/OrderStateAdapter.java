package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.OrderState;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderStateAdapter extends CommenAdapter<OrderState> implements AdapterView.OnItemClickListener{
    private int selectPosition;

    public OrderStateAdapter(Context context) {
        super(context);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (selectPosition !=position){
            mList.get(selectPosition).setSelect(false);
            mList.get(position).setSelect(true);
            selectPosition = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public void upDt(List<OrderState> list) {
        mList = list;
        mList.get(0).setSelect(true);
        selectPosition = 0;
        notifyDataSetChanged();
    }

    @Override
    public void setView(ViewHolder holder, int position, OrderState s) {
        if (s.isSelect()){
            holder.setCheck(R.id.rb,true);
        }else {
            holder.setCheck(R.id.rb,false);
        }

        if (!TextUtils.isEmpty(s.getContent())){
            holder.setText(R.id.tv_content,s.getContent());
        }
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_dialog_rb_select;
    }

    public int getSelectPosition() {
        return selectPosition;
    }


}
