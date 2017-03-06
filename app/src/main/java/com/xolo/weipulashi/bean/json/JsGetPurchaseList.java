package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/16.
 */

public class JsGetPurchaseList {

    /**
     * BranchID : 4933
     * PurchaseCode : #####
     * EmpName : *******
     * StartDate : 2016-11-11
     * EndDate : 2016-11-11
     * PageSize : 1
     * PageIndex : 10
     */

    private int BranchID;
    private String PurchaseCode;
    private String EmpName;
    private String StartDate;
    private String EndDate;
    private int PageSize;
    private String PageIndex;
    private String state;

    public JsGetPurchaseList(int branchID, String purchaseCode, String empName, String startDate, String endDate, int pageSize, String pageIndex,String state) {
        BranchID = branchID;
        PurchaseCode = purchaseCode;
        EmpName = empName;
        StartDate = startDate;
        EndDate = endDate;
        PageSize = pageSize;
        PageIndex = pageIndex;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int BranchID) {
        this.BranchID = BranchID;
    }

    public String getPurchaseCode() {
        return PurchaseCode;
    }

    public void setPurchaseCode(String PurchaseCode) {
        this.PurchaseCode = PurchaseCode;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String PageIndex) {
        this.PageIndex = PageIndex;
    }
}
