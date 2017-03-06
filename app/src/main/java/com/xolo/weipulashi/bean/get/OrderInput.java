package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderInput {

    /**
     * ID : 2605
     * PurchaseCode : S17010418492900
     * WorkDate : 2017-01-04
     * EmpName : 安宝乐总部
     * PurchaseType : 在线入库
     * state : 完成采集
     */

    private int ID;
    private String PurchaseCode;
    private String WorkDate;
    private String EmpName;
    private String PurchaseType;
    private String state;

    public OrderInput(int ID, String purchaseCode, String workDate, String empName, String purchaseType, String state) {
        this.ID = ID;
        PurchaseCode = purchaseCode;
        WorkDate = workDate;
        EmpName = empName;
        PurchaseType = purchaseType;
        this.state = state;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPurchaseCode() {
        return PurchaseCode;
    }

    public void setPurchaseCode(String PurchaseCode) {
        this.PurchaseCode = PurchaseCode;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getPurchaseType() {
        return PurchaseType;
    }

    public void setPurchaseType(String PurchaseType) {
        this.PurchaseType = PurchaseType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
