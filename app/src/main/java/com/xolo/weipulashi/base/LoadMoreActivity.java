package com.xolo.weipulashi.base;

import android.widget.AbsListView;

import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.adapterUtils.CommenAdapter;

/**
 * Created by Administrator on 2017/2/14.
 */

public abstract class LoadMoreActivity<T extends BasePresenter  > extends BaseActivity<T> implements AbsListView.OnScrollListener{

    protected  int lastItem;

    protected boolean isLoad = true;

    protected int currentPage = 1;

    protected CommenAdapter  loadAdapter;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        LogUtil.i("scrollstate:"+scrollState);
//        int count = loadAdapter.getCount();
//        LogUtil.i("totalcout:"+count);

        if ( isLoad && lastItem == loadAdapter.getCount() && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE ) {
//            LogUtil.i("inter");
            currentPage = currentPage+1;
            load();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount  ;
//        LogUtil.i("lastItem:"+lastItem);
    }

    public void check(BaseBean bean){
        if (bean.getRes().equals("0008") ){
            isLoad = false;
        }
        ToastUtil.showLong(mContext, bean.getMsg());
    }

    public abstract  void load();

    public void  loadFristPage(){
        isLoad = true;
        loadAdapter.clearAll();
        currentPage = 1;
        load();
    }

 }
