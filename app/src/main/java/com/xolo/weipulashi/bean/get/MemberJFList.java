package com.xolo.weipulashi.bean.get;

/**
 * Created by wei on 2017/2/17.
 */

public class MemberJFList {

    /**
     * id : 3834
     * JFcode : 3852544274373530
     * ProductName : 安宝乐亲畅免护系列较大婴儿配方奶粉2段
     * WorkDate : 2017-01-04
     * Type : Wap积分码获取
     * GainIntegral : 80
     * BranchName : 重庆爱之馨
     */

    private int id;
    private String JFcode;
    private String ProductName;
    private String WorkDate;
    private String Type;
    private int GainIntegral;
    private String BranchName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJFcode() {
        return JFcode;
    }

    public void setJFcode(String JFcode) {
        this.JFcode = JFcode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getGainIntegral() {
        return GainIntegral;
    }

    public void setGainIntegral(int GainIntegral) {
        this.GainIntegral = GainIntegral;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    @Override
    public String toString() {
        return "MemberJFList{" +
                "id=" + id +
                ", JFcode='" + JFcode + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", WorkDate='" + WorkDate + '\'' +
                ", Type='" + Type + '\'' +
                ", GainIntegral=" + GainIntegral +
                ", BranchName='" + BranchName + '\'' +
                '}';
    }
}
