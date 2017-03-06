package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/10.
 */

public class JsGetStoreMemberList {

    /**
     * BranchId : 492
     * PageSize : 1
     * PageIndex : 10
     */

    private int BranchId;
    private int PageSize;
    private String PageIndex;

    public JsGetStoreMemberList(int branchId, int pageSize, String pageIndex) {
        BranchId = branchId;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }

    public int getBranchId() {
        return BranchId;
    }

    public void setBranchId(int BranchId) {
        this.BranchId = BranchId;
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
