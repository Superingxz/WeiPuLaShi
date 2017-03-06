package com.xolo.weipulashi.bean.get;

/**
 * 库存
 */

public class StockNum {
    /**
     * id : 5
     * ProductCode : A1001
     * ProductName : 安宝乐金装婴儿配方奶粉1段
     * bz : 6
     * Num : 4652
     * BZNum : 775.33
     * BranchName : 温州辉腾商贸有限公司
     */

    private int id;
    private String ProductCode;
    private String ProductName;
    private int bz;
    private int Num;
    private double BZNum;
    private String BranchName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String ProductCode) {
        this.ProductCode = ProductCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getBz() {
        return bz;
    }

    public void setBz(int bz) {
        this.bz = bz;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }

    public double getBZNum() {
        return BZNum;
    }

    public void setBZNum(double BZNum) {
        this.BZNum = BZNum;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }
}
