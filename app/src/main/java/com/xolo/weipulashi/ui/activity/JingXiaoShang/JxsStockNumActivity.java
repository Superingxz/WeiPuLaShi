package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.JxsKuCunAdapter;
import com.xolo.weipulashi.adapter.MdKuCunAdapter;
import com.xolo.weipulashi.adapter.WareHouseAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.StockNum;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.bean.json.JsBranchId;
import com.xolo.weipulashi.bean.json.JsUserInfo;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 产品库存
 */
public class JxsStockNumActivity extends BaseActivity {

    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.tv_Warehouse)
    TextView tvWarehouse;
    @BindView(R.id.et_store)
    EditText etStore;
    @BindView(R.id.et_produce_name)
    EditText etProduceName;
    @BindView(R.id.mListView)
    ListView mListView;

    private Dialog  wareHouseListDialog;

    private WareHouseAdapter wareHouseAdapter;
    private JxsKuCunAdapter mdKuCunAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_num;
    }

    @Override
    public void initView() {
        initTitleView();
        initDialog();

        mdKuCunAdapter = new JxsKuCunAdapter(mContext);
        mListView.setAdapter(mdKuCunAdapter);

        getStockNum();
    }

    private void initDialog() {
        wareHouseAdapter = new WareHouseAdapter(mContext);
        wareHouseListDialog = new ListDialog(mContext, "选择签收仓库", wareHouseAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWarehouse.setText(wareHouseAdapter.getItem(wareHouseAdapter.getSelectPosition()).getName());
                wareHouseListDialog.dismiss();
            }});
    }

    @OnClick(R.id.tv_Warehouse)
    public void onClick() {
        if (wareHouseAdapter.isEmpty()){
            getWareHouse();
        }else
            wareHouseListDialog.show();
    }

    private void initTitleView() {
        mTitleview.setRightBg(R.mipmap.search);
        mTitleview.setRightIvClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdKuCunAdapter.clearAll();
                getStockNum();
            }
        });
    }

    public void setWareHouseDialogDt(final List<WareHouse> list) {
        wareHouseAdapter.upDt(list);
        wareHouseListDialog.show();
    }


    /**
     * 3.2.2.仓库信息列表接口
     */
    private void getWareHouse(){
        RetrofitManager.getApiInstant()
                .getWareHouse("GetWareHouseList",mGson.toJson(new JsUserInfo(String.valueOf(mUser.getBranch_id()))))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<WareHouse>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<WareHouse> wareHouseBaseBean) {
                        setWareHouseDialogDt(wareHouseBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<WareHouse> wareHouseBaseBean) {
                        ToastUtil.showLong(mContext,wareHouseBaseBean.getMsg());
                    }
                });
    }

    public void  getStockNum(){
        HashMap<String, String> map = new HashMap<>();
        map.put("BranchId", String.valueOf(mUser.getBranch_id()));
        map.put("StoreName",getText(etStore));
        map.put("ProductName",getText(etProduceName));

        String s = mGson.toJson(map);
        LogUtil.i(s);

        RetrofitManager.getApiInstant()
                .getStockNum("GetStockNum",s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<StockNum>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<StockNum> stockNumBaseBean) {
                        mdKuCunAdapter.upDt(stockNumBaseBean.getDt());
                    }

                    @Override
                    public void onError(BaseBean<StockNum> stockNumBaseBean) {
                        ToastUtil.showShort(mContext,stockNumBaseBean.getMsg());
                    }
                });
    }

}
