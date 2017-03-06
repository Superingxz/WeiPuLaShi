package com.xolo.weipulashi.utils.adapterUtils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2016/6/22.
 */
public abstract class CommenAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> mList;

    public CommenAdapter( Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(context, convertView, parent, getLayoutID());
        setView(viewHolder,position,mList.get(position));
        return viewHolder.getConvertView();
    }

    public void upDt(List<T> list){
        this.mList =list;
        notifyDataSetChanged();
    }

    public void  add(T t){
        mList.add(t);
        notifyDataSetChanged();
    }

    public void addAll(List<T> list){
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public boolean isEmpty(){
        if (mList == null || mList.size()==0){
            return true;
        }else
            return false;
    }

    public void clearAll(){
        mList.clear();
        notifyDataSetChanged();
    }

    public abstract void  setView(ViewHolder holder, int position,T t);

    public abstract int  getLayoutID();
}
