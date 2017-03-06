package com.xolo.weipulashi.ui.activity.JingXiaoShang;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.OrderAdapter;
import com.xolo.weipulashi.adapter.OutScanAdapter;
import com.xolo.weipulashi.adapter.ReturnScanAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.Order;
import com.xolo.weipulashi.bean.get.Produce;
import com.xolo.weipulashi.bean.get.ReturnInfo;
import com.xolo.weipulashi.bean.json.JsReturnId;
import com.xolo.weipulashi.interfaceView.ReturnScanView;
import com.xolo.weipulashi.presenter.ReturnScanPresenter;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.ui.dialog.MsgDialog;
import com.xolo.weipulashi.ui.view.TitleView;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;
import com.xolo.weipulashi.zxing.camera.CameraActivityCapture;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

public class ReturnScanActivity extends BaseActivity<ReturnScanPresenter> implements ReturnScanView {

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
    @BindView(R.id.et_barcode)
    EditText etBarcode;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.mTitleview)
    TitleView mTitleview;

    private ListDialog orderListDialog;
    private MsgDialog delectDialog;

    private OrderAdapter orderAdapter;
    private ReturnScanAdapter outScanAdapter;

    private MsgDialog closeDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_return_scan;
    }

    @Override
    public void initView() {
        initTitleView();
        initListView();
        initDialog();
    }

    private void initListView() {
        outScanAdapter = new ReturnScanAdapter(mContext);
        lv.setAdapter(outScanAdapter);
    }

    private void initDialog() {
        orderAdapter = new OrderAdapter(mContext);
        orderListDialog = new ListDialog(mContext, "选择出货单号", orderAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outScanAdapter.clearAll();

                Order order = orderAdapter.getItem(orderAdapter.getSelectPosition());
                tvSaleCode.setText(order.getRerurnCode());
                mPresenter.setOrderSelect(order);
                mPresenter.getRerurnProductList();
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
//                mPresenter.salesCodeCancel("4267893375");
                break;
            case R.id.btn_cancel:
                closeDialog.show();
                break;
            case R.id.btn_confirm:
                if (outScanAdapter.isEmpty()){
                    ToastUtil.showShort(mContext,"请先扫码");
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
    public void setOrderInfo(ReturnInfo info) {
        tvCheckEmpName.setText(info.getCheckName());
        tvCheckDate.setText(info.getCheckDate());
        tvWorkDate.setText(info.getWorkDate());
        tvEmpName.setText(info.getRerurnName());
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
