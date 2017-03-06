package com.xolo.weipulashi.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.OrderAdapter;
import com.xolo.weipulashi.adapter.SignInfoAdapter;
import com.xolo.weipulashi.adapter.WareHouseAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.SignCheck;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.interfaceView.ProduceSignView;
import com.xolo.weipulashi.presenter.ProduceSignPresenter;
import com.xolo.weipulashi.ui.dialog.DataDialog;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.adapterUtils.CommenRecycleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.attr.order;
import static android.R.id.list;

/**
 * 产品签收
 */
public class ProduceSignActivity extends BaseActivity<ProduceSignPresenter> implements ProduceSignView,CommenRecycleAdapter.OnItemClickLitener {

    @BindView(R.id.mTitleview)
    TitleView mTitleview;
    @BindView(R.id.et_order)
    EditText etOrder;
    @BindView(R.id.et_start_time)
    TextView etStartTime;
    @BindView(R.id.et_end_time)
    TextView etEndTime;
    @BindView(R.id.rv)
    RecyclerView rv;

    private Dialog startDialog;
    private Dialog endDialog;
    private ListDialog wareHouseListDialog;
    private ListDialog orderListDialog;

    private OrderAdapter orderAdapter;
    private WareHouseAdapter wareHouseAdapter;
    private SignInfoAdapter signInfoAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_produce_sign;
    }

    @Override
    public void initView() {
        initTitleView();
        initDialog();
        initRecycleview();
    }

    private void initDialog() {
        startDialog = DataDialog.creatDateDialog(mContext,etStartTime);
        endDialog = DataDialog.creatDateDialog(mContext,etEndTime);

        wareHouseAdapter = new WareHouseAdapter(mContext);
        wareHouseListDialog = new ListDialog(mContext, "选择签收仓库", wareHouseAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkSalesALL(String.valueOf(wareHouseAdapter.getItem(wareHouseAdapter.getSelectPosition()).getId()));
                wareHouseListDialog.dismiss();
            }});

        orderAdapter = new OrderAdapter(mContext);
        orderListDialog = new ListDialog(mContext, "选择出货单号", orderAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderAdapter.getCount()>0){
                    Order order = orderAdapter.getItem(orderAdapter.getSelectPosition());
                    etOrder.setText(order.getSaleCode());
                }
                orderListDialog.dismiss();
            }
        });
    }

    public void initRecycleview() {
        signInfoAdapter = new SignInfoAdapter(mContext);
        signInfoAdapter.setOnItemClickLitener(this);
        rv.setAdapter(signInfoAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initTitleView() {
//        mTitleview.setRightBg(R.mipmap.search);
//        mTitleview.setRightIvClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              mPresenter.getSaleCheckList(getText(etOrder),getText(etStartTime),getText(etEndTime));
//            }
//        });
    }

    @OnClick({R.id.btn_show, R.id.et_start_time, R.id.et_end_time, R.id.btn_confirm,R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show:
                if(!orderAdapter.isAllEmpty()){
                       orderAdapter.flit("");
                    orderListDialog.show();
                }else {
                    etOrder.setText("");
                    mPresenter.getCheckSaleCodeList();
                }
                break;
            case R.id.et_start_time:
                startDialog.show();
                break;
            case R.id.et_end_time:
                endDialog.show();
                break;
            case R.id.btn_confirm:
                if (!wareHouseAdapter.isEmpty()){
                    wareHouseListDialog.show();
                }else {
                    mPresenter.getWareHouse();
                }
                break;
            case R.id.btn_search:
//                if(!orderAdapter.isAllEmpty()){
//                    orderAdapter.flit(getText(etOrder));
//                    orderListDialog.show();
//                }else {
//                    mPresenter.getCheckSaleCodeList();
//                }
                signInfoAdapter.clearAll();
                mPresenter.getSaleCheckList(getText(etOrder),getText(etStartTime),getText(etEndTime));
                break;
        }
    }

    @Override
    public void setOrderDialogDt(final List<Order> list) {
        orderAdapter.setAllOrder(list);
        orderAdapter.flit(getText(etOrder));
        orderListDialog.show();
    }

    @Override
    public void setWareHouseDialogDt(final List<WareHouse> list) {
        wareHouseAdapter.upDt(list);
        wareHouseListDialog.show();
    }

    @Override
    public void setRecycleviewDt(List<SignCheck> list) {
        signInfoAdapter.upDt(list);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, SignInfoActivity.class);
        intent.putExtra(IntentConfig.SALEID,String.valueOf(signInfoAdapter.getItem(position).getID()));
        startActivityForResult(intent,99);
    }

    @Override
    public void onItemLongClick(View view, int position) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            signInfoAdapter.clearAll();
            etOrder.setText("");
            mPresenter.getSaleCheckList(getText(etOrder),getText(etStartTime),getText(etEndTime));
        }
    }

}
