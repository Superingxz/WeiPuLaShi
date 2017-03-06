package com.xolo.weipulashi.bean.get;

/**
 * 出库单详情
 */

public class OutOrderInfo {

    /**
     * ID : 2605
     * SaleCode : S17010418492900
     * WorkDate : 2017-01-04
     * SalesName : 安宝乐总部
     * ToBranchName : 成都双欢
     * Warehouse : 安宝乐总仓
     * EmpName : 仓储部
     * CheckEmpName : 仓储部
     * ScanEmpName : 仓储部
     * CheckDate : 2017-01-05
     * ScanStatDate : 2017-01-04
     * state : 已发货
     */

    private int ID;
    private String SaleCode;
    private String WorkDate;
    private String SalesName;
    private String ToBranchName;
    private String Warehouse;
    private String EmpName;
    private String CheckEmpName;
    private String ScanEmpName;
    private String CheckDate;
    private String ScanStatDate;
    private String state;
    /**
     * PurchaseCode : P16122915555801
     * Remark : 仓储部
     */

    private String PurchaseCode;
    private String Remark;

    public OutOrderInfo(int ID, String workDate, String toBranchName, String state, String saleCode) {
        this.ID = ID;
        WorkDate = workDate;
        ToBranchName = toBranchName;
        this.state = state;
        SaleCode = saleCode;
    }

    /**
     * BranchName : 安宝乐总部
     */



    private String BranchName;

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

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public String getSalesName() {
        return SalesName;
    }

    public void setSalesName(String SalesName) {
        this.SalesName = SalesName;
    }

    public String getToBranchName() {
        return ToBranchName;
    }

    public void setToBranchName(String ToBranchName) {
        this.ToBranchName = ToBranchName;
    }

    public String getWarehouse() {
        return Warehouse;
    }

    public void setWarehouse(String Warehouse) {
        this.Warehouse = Warehouse;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getCheckEmpName() {
        return CheckEmpName;
    }

    public void setCheckEmpName(String CheckEmpName) {
        this.CheckEmpName = CheckEmpName;
    }

    public String getScanEmpName() {
        return ScanEmpName;
    }

    public void setScanEmpName(String ScanEmpName) {
        this.ScanEmpName = ScanEmpName;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String CheckDate) {
        this.CheckDate = CheckDate;
    }

    public String getScanStatDate() {
        return ScanStatDate;
    }

    public void setScanStatDate(String ScanStatDate) {
        this.ScanStatDate = ScanStatDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getPurchaseCode() {
        return PurchaseCode;
    }

    public void setPurchaseCode(String PurchaseCode) {
        this.PurchaseCode = PurchaseCode;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
}
