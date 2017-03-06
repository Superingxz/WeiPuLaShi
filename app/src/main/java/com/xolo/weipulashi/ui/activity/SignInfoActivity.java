package com.xolo.weipulashi.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.ProduceAdapter;
import com.xolo.weipulashi.adapter.WareHouseAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.WareHouse;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.interfaceView.SignInfoView;
import com.xolo.weipulashi.presenter.SignInfoPresenter;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.zxing.camera.CameraActivityCapture;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.id.list;

public class SignInfoActivity extends BaseActivity<SignInfoPresenter> implements SignInfoView {
    @BindView(R.id.mTitleview)
    TitleView mTitleView;
    @BindView(R.id.tv_CheckEmpName)
    TextView tvCheckEmpName;
    @BindView(R.id.tv_CheckDate)
    TextView tvCheckDate;
    @BindView(R.id.tv_ScanEmpName)
    TextView tvScanEmpName;
    @BindView(R.id.tv_ScanStatDate)
    TextView tvScanStatDate;
    @BindView(R.id.et_logistics)
    EditText etLogistics;
    @BindView(R.id.tv_SaleCode)
    TextView tvSaleCode;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_SalesName)
    TextView tvSalesName;
    @BindView(R.id.tv_Warehouse)
    TextView tvWarehouse;
    @BindView(R.id.tv_ToBranchName)
    TextView tvToBranchName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.mListView)
    ListView lv;
    @BindView(R.id.tv_select_warehouse)
    TextView tvSelectWarehouse;

    private ListDialog wareHouseListDialog;

    private ProduceAdapter outProduceAdapter;
    private WareHouseAdapter wareHouseAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sign_info;
    }

    @Override
    public void initView() {
        initTitle();
        initRv();
        iniDialog();
    }

    private void initTitle() {
        mTitleView.setRightBg(R.mipmap.scan);
        mTitleView.setRightIvClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  startActivityForResult(new Intent(mContext, CameraActivityCapture.class),99);
            }
        });
    }

    public void initRv() {
        outProduceAdapter = new ProduceAdapter(mContext);
        lv.setAdapter(outProduceAdapter);
    }

    private void iniDialog() {
        wareHouseAdapter = new WareHouseAdapter(mContext);
        wareHouseListDialog = new ListDialog(mContext, "选择签收仓库", wareHouseAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WareHouse wareHouse = wareHouseAdapter.getItem(wareHouseAdapter.getSelectPosition());
                tvSelectWarehouse.setText(wareHouse.getName());
                mPresenter.setWareHouse(wareHouse);
                wareHouseListDialog.dismiss();
            }});
    }

    @Override
    protected void onResume() {
        super.onResume();
        String saleId = getIntent().getStringExtra(IntentConfig.SALEID);
        mPresenter.getSaleInfo(saleId);
    }

    @OnClick({R.id.tv_select_warehouse, R.id.btn_sign})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_warehouse:
                if (!wareHouseAdapter.isEmpty()){
                    wareHouseListDialog.show();
                }else
                  mPresenter.getWareHouse();
                break;
            case R.id.btn_sign:
                String logistics = getText(etLogistics);
                if (TextUtils.isEmpty(logistics)){
                    ToastUtil.showLong(mContext,"请输入物流码");
                    return;
                }
                mPresenter.qSScanCheckAll(logistics);
                break;
        }
    }

    @Override
    public void setOrderInfo(OutOrderInfo info) {
         tvCheckEmpName.setText(info.getCheckEmpName());
        tvCheckDate.setText(info.getCheckDate());
        tvScanEmpName.setText(info.getScanEmpName());
        tvScanStatDate.setText(info.getScanStatDate());
        tvSaleCode.setText(info.getSaleCode());
        tvWorkDate.setText(info.getWorkDate());
        tvSalesName.setText(info.getSalesName());
        tvWarehouse.setText(info.getWarehouse());
        tvToBranchName.setText(info.getToBranchName());
        tvState.setText(info.getState());
    }

    @Override
    public void setWareHouseDialogDt(final List<WareHouse> list) {
        wareHouseAdapter.upDt(list);
        wareHouseListDialog.show();
    }

    @Override
    public void setLvDt(List<Produce> list) {
        outProduceAdapter.upDt(list);
    }

    @Override
    public void setResult() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            String result = data.getStringExtra(CameraActivityCapture.RESULT);
               etLogistics.setText(result);

            mPresenter.qSScanCheckAll(result);
        }
    }

}
