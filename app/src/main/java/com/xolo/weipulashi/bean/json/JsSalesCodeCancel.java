package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/9.
 */

public class JsSalesCodeCancel {

    /**
     * SalesId : 2605
     * BarCode : 892394239
     * EmployeeId : 3
     */
    private int SalesId;
    private String BarCode;
    private int EmployeeId;
    /**
     * ReturnId : 2605
     */

    private int ReturnId;

    public JsSalesCodeCancel(int salesId, String barCode, int employeeId) {
        SalesId = salesId;
        BarCode = barCode;
        EmployeeId = employeeId;
    }
    public JsSalesCodeCancel( String barCode,int returnId, int employeeId) {
        ReturnId = returnId;
        BarCode = barCode;
        EmployeeId = employeeId;
    }

    public int getSalesId() {
        return SalesId;
    }

    public void setSalesId(int SalesId) {
        this.SalesId = SalesId;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public int getReturnId() {
        return ReturnId;
    }

    public void setReturnId(int ReturnId) {
        this.ReturnId = ReturnId;
    }
}
