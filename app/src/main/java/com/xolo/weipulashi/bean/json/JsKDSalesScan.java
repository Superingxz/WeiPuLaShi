package com.xolo.weipulashi.bean.json;

/**
 * 3.4.5.出库扫码接口
 */

public class JsKDSalesScan {

    /**
     * Sale_ID : 49
     * BarCode : ********
     * Employee_ID : 3
     */

    private int Sale_ID;
    private String BarCode;
    private int Employee_ID;



    public JsKDSalesScan(int sale_ID, String barCode, int employee_ID) {
        Sale_ID = sale_ID;
        BarCode = barCode;
        Employee_ID = employee_ID;
    }

    public int getSale_ID() {
        return Sale_ID;
    }

    public void setSale_ID(int Sale_ID) {
        this.Sale_ID = Sale_ID;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(int Employee_ID) {
        this.Employee_ID = Employee_ID;
    }
}
