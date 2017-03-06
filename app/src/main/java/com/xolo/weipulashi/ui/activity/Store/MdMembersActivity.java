package com.xolo.weipulashi.ui.activity.Store;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.MenbersAdapter;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.base.LoadMoreActivity;
import com.xolo.weipulashi.bean.get.Menbers;
import com.xolo.weipulashi.bean.json.JsGetStoreMemberList;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 本店会员
 */
public class MdMembersActivity extends LoadMoreActivity {

    @BindView(R.id.mListView)
    ListView mListView;
    private BaseBean<Menbers> menbersBaseBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_md_members;
    }

    @Override
    public void initView() {
        loadAdapter = new MenbersAdapter(mContext);
        mListView.setAdapter(loadAdapter);
        mListView.setOnScrollListener(this);

        load();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MdMembersActivity.this, MdMembersInfoActivity.class);
                int ID = menbersBaseBean.getDt().get(i).getId();
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
    }

    /**
     *
     3.8.1.门店会员列表接口
     */
    @Override
    public void load() {
        RetrofitManager.getApiInstant()
                .getMenbers("GetStoreMemberList",mGson.toJson(new JsGetStoreMemberList(mUser.getBranch_id(),10,String.valueOf(currentPage))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Menbers>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Menbers> menbersBaseBean) {
                        loadAdapter.addAll(menbersBaseBean.getDt());
                        getMenbersBaseBean(menbersBaseBean);
                    }

                    @Override
                    public void onError(BaseBean<Menbers> menbersBaseBean) {
                        check(menbersBaseBean);
                    }
                });
    }

    public void getMenbersBaseBean(BaseBean<Menbers> menbersBaseBean) {
        this.menbersBaseBean=menbersBaseBean;
    }
}
