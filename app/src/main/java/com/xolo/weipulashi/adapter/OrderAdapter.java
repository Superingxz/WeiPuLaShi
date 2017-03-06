package com.xolo.weipulashi.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;
import com.xolo.weipulashi.utils.adapterUtils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.order;

/**
 * Created by Administrator on 2017/2/6.
 */

public class OrderAdapter extends CommenAdapter<Order> implements AdapterView.OnItemClickListener{
    private int selectPosition;

    public List<Order> allOrder;

    public OrderAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_dialog_rb_select;
    }

    @Override
    public void upDt(List<Order> list) {
        mList = list;
        mList.get(0).setSelect(true);
        selectPosition = 0;
        notifyDataSetChanged();
    }

    public void setAllOrder(List<Order> allOrder) {
        this.allOrder = allOrder;
        allOrder.get(0).setSelect(true);
        selectPosition = 0;
    }

    public boolean isAllEmpty(){
        if (allOrder == null || allOrder.size()==0){
            return true;
        }else
            return false;
    }

    public void flit(String flit){
        selectPosition = 0;
        for (Order  o:allOrder) {
            o.setSelect(false);
        }

        mList.clear();

        if (TextUtils.isEmpty(flit)){
            mList.addAll(allOrder);
            mList.get(0).setSelect(true);
            notifyDataSetChanged();
            return;
        }

        for (Order  o:allOrder) {
            String code = o.getSaleCode();
            if (code.contains(flit)){
                  mList.add(o);
            }
        }

        if (mList.size()>0){
            mList.get(0).setSelect(true);
        }
           notifyDataSetChanged();
    }

    @Override
    public void setView(ViewHolder holder, final int position, final Order order) {
        if (order.isSelect()){
            holder.setCheck(R.id.rb,true);
        }else {
            holder.setCheck(R.id.rb,false);
        }

        if (TextUtils.isEmpty(order.getSaleCode())){
            holder.setText(R.id.tv_content,order.getRerurnCode());
        }else
            holder.setText(R.id.tv_content,order.getSaleCode());
    }

    public int getSelectPosition() {
        return selectPosition;
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


}
