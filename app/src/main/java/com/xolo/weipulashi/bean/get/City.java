package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/24.
 */

public class City {

    /**
     * CityCode : 440100
     * CityName : 广州市
     */

    private String CityCode;
    private String CityName;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }
}
