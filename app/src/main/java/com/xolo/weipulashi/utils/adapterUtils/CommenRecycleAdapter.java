package com.xolo.weipulashi.utils.adapterUtils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Administrator on 2016/6/22.
 */
public abstract  class CommenRecycleAdapter<T> extends RecyclerView.Adapter<ComRecyclerViewHolder> {
    private  LayoutInflater inflater;

    protected Context context;
    protected List<T> mList;

    private OnItemClickLitener mOnItemClickLitener;

    public CommenRecycleAdapter( Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        mList = new ArrayList<>();
    }

    @Override
    public ComRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getLayoutID(), parent, false);
        return new ComRecyclerViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final ComRecyclerViewHolder holder, int position) {
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

                    setView(holder,position,mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract void setView(ComRecyclerViewHolder holder,int position, T t);

    public abstract int  getLayoutID();


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    public boolean isEmpty(){
        if (mList == null || mList.size()==0){
            return true;
        }else
            return false;

    }

    public void  clearAll(){
        mList.clear();
        notifyDataSetChanged();
    }

    public T getItem(int position){
       return mList.get(position);
    }

    public void upDt(List<T> list){
        mList = list;
        notifyDataSetChanged();
    }
}
