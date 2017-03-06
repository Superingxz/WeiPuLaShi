package com.xolo.weipulashi.ui.activity.Store;

import android.widget.ListView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.MdKuCunAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.StockNum;
import com.xolo.weipulashi.bean.json.JsBranchId;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MdKuCunActivity extends BaseActivity {

    @BindView(R.id.mListView)
    ListView mListView;
    private MdKuCunAdapter mdKuCunAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_md_ku_cun;
    }

    @Override
    public void initView() {
        mdKuCunAdapter = new MdKuCunAdapter(mContext);
        mListView.setAdapter(mdKuCunAdapter);

        getStockNum();
    }

    public void  getStockNum(){
        RetrofitManager.getApiInstant()
                .getStockNum("GetStockNum",mGson.toJson(new JsBranchId(String.valueOf(mUser.getBranch_id()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<StockNum>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<StockNum> stockNumBaseBean) {
                              mdKuCunAdapter.upDt(stockNumBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<StockNum> stockNumBaseBean) {
                        ToastUtil.showLong(mContext,stockNumBaseBean.getMsg());
                    }
                });
    }
}
