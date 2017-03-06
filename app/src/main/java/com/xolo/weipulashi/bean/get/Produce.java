package com.xolo.weipulashi.bean.get;

/**
 * 出库单扫描商品
 */

public class Produce {

    /**
     * ProductCode : A1003
     * ProductName : 安宝乐金装幼儿配方奶粉3段
     * Barnd : 安宝乐
     * bz : 6罐/箱
     * dc : 三段
     * Qty : 480
     * SQty : 480
     * CheckQty : 0
     */

    private String ProductCode;
    private String ProductName;
    private String Barnd;
    private String bz;
    private String dc;
    private String Qty;
    private String SQty;
    private String CheckQty;
    /**
     * Product_ID : 7
     * BatchNO : 89922342
     * XQty : 80
     * ScanQty : 0
     */

    private int Product_ID;
    private String BatchNO;
    private String XQty;
    private String ScanQty;
    /**
     * id : 5
     * Integral : 200
     */

    private int id;
    private String Integral;
    private String ImgPath;

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
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

    public String getBarnd() {
        return Barnd;
    }

    public void setBarnd(String Barnd) {
        this.Barnd = Barnd;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }


    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public String getBatchNO() {
        return BatchNO;
    }

    public void setBatchNO(String BatchNO) {
        this.BatchNO = BatchNO;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getSQty() {
        return SQty;
    }

    public void setSQty(String SQty) {
        this.SQty = SQty;
    }

    public String getCheckQty() {
        return CheckQty;
    }

    public void setCheckQty(String checkQty) {
        CheckQty = checkQty;
    }

    public String getXQty() {
        return XQty;
    }

    public void setXQty(String XQty) {
        this.XQty = XQty;
    }

    public String getScanQty() {
        return ScanQty;
    }

    public void setScanQty(String scanQty) {
        ScanQty = scanQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntegral() {
        return Integral;
    }

    public void setIntegral(String integral) {
        Integral = integral;
    }
}
