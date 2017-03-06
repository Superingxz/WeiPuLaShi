package com.xolo.weipulashi.bean.json;

/**
 * 3.4.2.出库信息列表接口
 */

public class JsGetSaleList {

    /**
     * BranchID : 4933
     * SaleCode : #####
     * StoreName : *******
     * StartDate : 2016-11-11
     * EndDate : 2016-11-11
     * PageSize : 1
     * PageIndex : 10
     */

    private int BranchID;
    private String SaleCode;
    private String StoreName;
    private String StartDate;
    private String EndDate;
    private int PageSize;
    private String PageIndex;

    public JsGetSaleList(int branchID, String saleCode, String storeName, String startDate, String endDate, int pageSize, String pageIndex) {
        BranchID = branchID;
        SaleCode = saleCode;
        StoreName = storeName;
        StartDate = startDate;
        EndDate = endDate;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int BranchID) {
        this.BranchID = BranchID;
    }

    public String getSaleCode() {
        return SaleCode;
    }

    public void setSaleCode(String SaleCode) {
        this.SaleCode = SaleCode;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
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
