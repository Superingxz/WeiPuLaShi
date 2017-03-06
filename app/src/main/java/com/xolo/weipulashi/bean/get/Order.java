package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/6.
 */

public class Order {

    /**
     * ID : 452
     * SaleCode : S26032220195600
     */

    private int ID;
    private String SaleCode;
    private String RerurnCode;

    private boolean isSelect;

    public String getRerurnCode() {
        return RerurnCode;
    }

    public void setRerurnCode(String rerurnCode) {
        RerurnCode = rerurnCode;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSaleCode() {
        return SaleCode;
    }

    public void setSaleCode(String SaleCode) {
        this.SaleCode = SaleCode;
    }

    public Order(String saleCode, int ID) {
        SaleCode = saleCode;
        this.ID = ID;
    }
}
