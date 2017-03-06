package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/8.
 */

public class JsGetRerurnList {
    private int BranchID;
    private int PageSize;
    private String PageIndex;
    private String  StoreName ;
    private String  RerurnCode ;
    private String  StartDate ;
    private String  EndDate ;

    public JsGetRerurnList(int branchID, String rerurnCode, String storeName, String startDate, String endDate, int pageSize, String pageIndex) {
        BranchID = branchID;
        RerurnCode = rerurnCode;
        StoreName = storeName;
        StartDate = startDate;
        EndDate = endDate;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getRerurnCode() {
        return RerurnCode;
    }

    public void setRerurnCode(String rerurnCode) {
        RerurnCode = rerurnCode;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int BranchID) {
        this.BranchID = BranchID;
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
