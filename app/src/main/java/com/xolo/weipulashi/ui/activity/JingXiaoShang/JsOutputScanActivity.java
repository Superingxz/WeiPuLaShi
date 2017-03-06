package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.OrderAdapter;
import com.xolo.weipulashi.adapter.OutScanAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.OutOrderInfo;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.interfaceView.JsOutputScanView;
import com.xolo.weipulashi.presenter.JsOutputScanPresenter;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.dialog.MsgDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.LogUtil;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.zxing.camera.CameraActivityCapture;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.R.id.list;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.media.CamcorderProfile.get;

/**
 * 销售出库扫码
 */
public class JsOutputScanActivity extends BaseActivity<JsOutputScanPresenter> implements JsOutputScanView {

    @BindView(R.id.tv_SaleCode)
    TextView tvSaleCode;
    @BindView(R.id.tv_WorkDate)
    TextView tvWorkDate;
    @BindView(R.id.tv_EmpName)
    TextView tvEmpName;
    @BindView(R.id.tv_CheckDate)
    TextView tvCheckDate;
    @BindView(R.id.tv_CheckEmpName)
    TextView tvCheckEmpName;
    @BindView(R.id.tv_Warehouse)
    TextView tvWarehouse;
    @BindView(R.id.tv_ToBranchName)
    TextView tvToBranchName;
    @BindView(R.id.et_barcode)
    EditText etBarcode;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.mTitleview)
    TitleView mTitleview;

    private ListDialog orderListDialog;
    private MsgDialog delectDialog;
    private MsgDialog closeDialog;

    private OrderAdapter orderAdapter;
    private OutScanAdapter outScanAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_js_output_scan;
    }

    @Override
    public void initView() {
        initTitleView();
        initListView();
        initDialog();
    }

    private void initListView() {
        outScanAdapter = new OutScanAdapter(mContext);
        lv.setAdapter(outScanAdapter);
    }

    private void initDialog() {
        orderAdapter = new OrderAdapter(mContext);
        orderListDialog = new ListDialog(mContext, "选择出货单号", orderAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = orderAdapter.getItem(orderAdapter.getSelectPosition());
                tvSaleCode.setText(order.getSaleCode());
                mPresenter.setOrderSelect(order);
                mPresenter.getSaleProductList();
                orderListDialog.dismiss();
            }
        });
        delectDialog = new MsgDialog(mContext, "删除条码", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.salesCodeCancel(delectDialog.getMsg());
                delectDialog.dismiss();
            }
        });
        closeDialog = new MsgDialog(mContext, "提示", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        closeDialog.setMsg("是否退出操作？");
    }

    private void initTitleView() {
        mTitleview.setRightBg(R.mipmap.scan);
        mTitleview.setRightIvClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivityForResult(new Intent(mContext, CameraActivityCapture.class),CameraActivityCapture.CAMERA);
            }
        });
    }

    @OnClick({R.id.tv_SaleCode, R.id.btn_delect, R.id.btn_cancel, R.id.btn_confirm,R.id.btn_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_SaleCode:
                if (!orderAdapter.isEmpty()) {
                    orderListDialog.show();
                }else
                mPresenter.getSaleCodeList();
                break;
            case R.id.btn_delect:
                if (TextUtils.isEmpty(getText(tvSaleCode))){
                    ToastUtil.showShort(mContext,"请选择单号");
                    return;
                }
                startActivityForResult(new Intent(mContext, CameraActivityCapture.class),9);
                break;
            case R.id.btn_cancel:
                closeDialog.show();
                break;
            case R.id.btn_confirm:
                if (outScanAdapter.isEmpty()){
                    ToastUtil.showShort(mContext,"请上传条码");
                    return;
                }
                mPresenter.salesSend();
               break;
            case R.id.btn_sure:
                if (TextUtils.isEmpty(getText(etBarcode))){
                    ToastUtil.showShort(mContext,"请输入条码");
                    return;
                }
                mPresenter.kDSalesScan(getText(etBarcode));
                break;
        }
    }

    @Override
    public void setOrderDialogDt(final List<Order> list) {
        orderAdapter.upDt(list);
        orderListDialog.show();
    }

    @Override
    public void setProduceDt(List<Produce> list) {
        outScanAdapter.upDt(list);
    }

    @Override
    public void setOrderInfo(OutOrderInfo info) {
        tvCheckEmpName.setText(info.getCheckEmpName());
        tvCheckDate.setText(info.getCheckDate());
        tvWorkDate.setText(info.getWorkDate());
        tvWarehouse.setText(info.getWarehouse());
        tvToBranchName.setText(info.getToBranchName());
        tvEmpName.setText(info.getEmpName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraActivityCapture.CAMERA&&resultCode == RESULT_OK){
            String result = data.getStringExtra(CameraActivityCapture.RESULT);
            mPresenter.kDSalesScan(result);
        }
        if (requestCode == 9 &&resultCode == RESULT_OK){
            String result = data.getStringExtra(CameraActivityCapture.RESULT);
            delectDialog.setMsg(result);
            delectDialog.show();
        }
    }

}
