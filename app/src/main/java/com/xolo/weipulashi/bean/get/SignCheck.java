package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/6.
 */

public class SignCheck {

    /**
     * ID : 2605
     * SaleCode : S17010418492900
     * WorkDate : 2017-01-04
     * BranchName : 安宝乐总部
     * state : 已发货
     */

    private int ID;
    private String SaleCode;
    private String WorkDate;
    private String BranchName;
    private String state;

    public SignCheck(int iD, String saleCode, String workDate, String branchName, String state) {
        ID = iD;
        SaleCode = saleCode;
        WorkDate = workDate;
        BranchName = branchName;
        this.state = state;
    }

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

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
