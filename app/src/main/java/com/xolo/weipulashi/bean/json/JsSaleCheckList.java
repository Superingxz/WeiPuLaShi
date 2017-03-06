package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/6.
 */

public class JsSaleCheckList {
    private  String BranchID;
    private  String SaleCode;
    private  String StartDate;
    private  String EndDate;
    private  String PageSize;
    private  String PageIndex;

    public JsSaleCheckList(String branchID, String saleCode, String startDate, String endDate, String pageSize, String pageIndex) {
        BranchID = branchID;
        SaleCode = saleCode;
        StartDate = startDate;
        EndDate = endDate;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getSaleCode() {
        return SaleCode;
    }

    public void setSaleCode(String saleCode) {
        SaleCode = saleCode;
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

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }
}
