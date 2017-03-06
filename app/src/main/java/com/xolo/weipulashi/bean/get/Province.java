package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Province {

    /**
     * ProvinceCode : 350000
     * ProvinceName : 福建省
     */

    private String ProvinceCode;
    private String ProvinceName;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(String ProvinceCode) {
        this.ProvinceCode = ProvinceCode;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String ProvinceName) {
        this.ProvinceName = ProvinceName;
    }
}
