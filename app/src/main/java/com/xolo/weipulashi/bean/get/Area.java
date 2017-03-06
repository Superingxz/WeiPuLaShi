package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Area {

    /**
     * AreaCode : 440102
     * AreaName : 东山区
     */

    private String AreaCode;
    private String AreaName;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String AreaCode) {
        this.AreaCode = AreaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }
}
