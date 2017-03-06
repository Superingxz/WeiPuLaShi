package com.xolo.weipulashi.ui.activity.Store;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xolo.weipulashi.R;
import com.xolo.weipulashi.adapter.AreaAdapter;
import com.xolo.weipulashi.adapter.CityAdapter;
import com.xolo.weipulashi.adapter.ProvinceAdapter;
import com.xolo.weipulashi.base.BaseActivity;
import com.xolo.weipulashi.base.BaseBean;
import com.xolo.weipulashi.bean.get.Area;
import com.xolo.weipulashi.bean.get.City;
import com.xolo.weipulashi.bean.get.Province;
import com.xolo.weipulashi.configuration.IntentConfig;
import com.xolo.weipulashi.ui.dialog.ListDialog;
import com.xolo.weipulashi.utils.ToastUtil;
import com.xolo.weipulashi.utils.http.ExSubscriber;
import com.xolo.weipulashi.utils.http.RetrofitManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 添加收货地址
 */
public class AddAddressActivity extends BaseActivity {


    @BindView(R.id.et_province)
    TextView etProvince;
    @BindView(R.id.et_city)
    TextView etCity;
    @BindView(R.id.et_area)
    TextView etArea;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_man)
    EditText etMan;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private ListDialog   provinceDialog;
    private ListDialog   cityDialog;
    private ListDialog   areaDialog;

    private ProvinceAdapter provinceAdapter;
    private CityAdapter cityAdapter;
    private AreaAdapter areaAdapter;

    private String provinceCode;
    private String cityCode;
    private String areaCode;

    private String memberId;

    @Override
    public int getLayoutId() {
        return  R.layout.activity_add_address;
    }

    @Override
    public void initView() {
        initIntent();
           initDialog();
    }

    private void initIntent() {
        memberId = getIntent().getStringExtra(IntentConfig.MENBER_ID);
    }

    private void initDialog() {
        provinceAdapter = new ProvinceAdapter(mContext);
        cityAdapter = new CityAdapter(mContext);
        areaAdapter = new AreaAdapter(mContext);

        provinceDialog = new ListDialog(mContext, "选择省", provinceAdapter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Province item = provinceAdapter.getItem(provinceAdapter.getSelectPosition());
                provinceCode = item.getProvinceCode();

                etProvince.setText(item.getProvinceName());
                etCity.setText("");
                etArea.setText("");

                provinceDialog.dismiss();
            }
        });
        cityDialog = new ListDialog(mContext, "选择市", cityAdapter, new View.OnClickListener() {
            private City item;

            @Override
            public void onClick(View v) {
                item = cityAdapter.getItem(cityAdapter.getSelectPosition());
                cityCode = cityAdapter.getItem(cityAdapter.getSelectPosition()).getCityCode();

                etCity.setText(item.getCityName());
                etArea.setText("");

                cityDialog.dismiss();
            }
        });
        areaDialog = new ListDialog(mContext, "选择县", areaAdapter, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Area item = areaAdapter.getItem(areaAdapter.getSelectPosition());
                areaCode = item.getAreaCode();
                etArea.setText(item.getAreaName());

                areaDialog.dismiss();
            }
        });
    }

    @OnClick({R.id.et_province, R.id.et_city, R.id.et_area, R.id.btn_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_province:
                if (TextUtils.isEmpty(getText(etProvince))){
                    getProvince();
                }else
                   provinceDialog.show();
                break;
            case R.id.et_city:
                if (!TextUtils.isEmpty(getText(etProvince))) {

                    if (TextUtils.isEmpty(getText(etCity))) {
                        getCity();
                    } else
                        cityDialog.show();
                }
                break;
            case R.id.et_area:
                if (!TextUtils.isEmpty(getText(etCity))) {
                    if (TextUtils.isEmpty(getText(etAddress))) {
                        getArea();
                    } else
                        areaDialog.show();
                }
                break;
            case R.id.btn_add:
                addMemberTakeAddress();
                break;
        }
    }

    /**
     * 3.9.12.获取区/县接口
     */
    private void  getArea(){
        HashMap<String, String> map = new HashMap<>();
        map.put("CityCode",cityCode);

        RetrofitManager.getApiInstant()
                .getArea("GetArea",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Area>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Area> areaBaseBean) {
                        areaAdapter.upDt(areaBaseBean.getDt());
                        areaDialog.show();
                    }

                    @Override
                    public void onError(BaseBean<Area> areaBaseBean) {
                        ToastUtil.showLong(mContext,areaBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.9.11.获取市接口
     */
    private void  getCity(){
        HashMap<String, String> map = new HashMap<>();
        map.put("ProvinceCode",provinceCode);

        RetrofitManager.getApiInstant()
                .getCity("GetCity",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<City>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<City> areaBaseBean) {
                        cityAdapter.upDt(areaBaseBean.getDt());
                        cityDialog.show();
                    }

                    @Override
                    public void onError(BaseBean<City> areaBaseBean) {
                        ToastUtil.showLong(mContext,areaBaseBean.getMsg());
                    }
                });
    }

    /**
     * 3.9.10.获取省份接口
     */
    private void  getProvince(){
        RetrofitManager.getApiInstant()
                .getProvince("GetProvince","{}")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean<Province>>(mContext) {
                    @Override
                    public void onSuccess(BaseBean<Province> areaBaseBean) {
                               provinceAdapter.upDt(areaBaseBean.getDt());
                        provinceDialog.show();
                    }

                    @Override
                    public void onError(BaseBean<Province> areaBaseBean) {
                        ToastUtil.showLong(mContext,areaBaseBean.getMsg());
                    }
                });
    }


    /**
     * 3.9.14.新增会员收货地址接口
     * MemberId	Int	是		会员ID
     Reciver	String	是		收货人
     Tel	String	是		收货人电话
     ProvinceName	String	是		省
     CityName	String	是		市
     AreaName	String	是		区/县
     DetailedAdress	String	是		收货地址
     */
    private void  addMemberTakeAddress(){
        HashMap<String, String> map = new HashMap<>();
        map.put("MemberId",memberId);
        map.put("Reciver",getText(etMan));
        map.put("Tel",getText(etPhone));
        map.put("ProvinceName",getText(etProvince));
        map.put("CityName",getText(etCity));
        map.put("AreaName",getText(etArea));
        map.put("DetailedAdress",getText(etAddress));

        RetrofitManager.getApiInstant()
                .getBaseBean("AddMemberTakeAddress",mGson.toJson(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new ExSubscriber<BaseBean>(mContext) {
                    @Override
                    public void onSuccess(BaseBean bean) {
                        ToastUtil.showLong(mContext,bean.getMsg());
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(BaseBean bean) {
                        ToastUtil.showLong(mContext,bean.getMsg());
                    }
                });
    }

}
