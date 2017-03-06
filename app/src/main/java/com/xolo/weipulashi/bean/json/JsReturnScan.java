package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/9.
 */

public class JsReturnScan {

    /**
     * Return_ID : 49
     * BarCode : ********
     * Employee_ID : 3
     */

    private int Return_ID;
    private String BarCode;
    private int Employee_ID;

    public JsReturnScan(int return_ID, String barCode, int employee_ID) {
        Return_ID = return_ID;
        BarCode = barCode;
        Employee_ID = employee_ID;
    }

    public int getReturn_ID() {
        return Return_ID;
    }

    public void setReturn_ID(int Return_ID) {
        this.Return_ID = Return_ID;
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
