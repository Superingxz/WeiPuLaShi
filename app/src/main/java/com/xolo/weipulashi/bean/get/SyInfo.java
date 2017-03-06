package com.xolo.weipulashi.bean.get;

import java.io.Serializable;

/**
 * suoyuan 信息
 */

public class SyInfo implements Serializable{

    /**
     * Barnd : 安宝乐
     * ProductName : 安宝乐金装幼儿配方奶粉3段
     * Standard : 800g/罐
     * BatchNO : ANB30CAF300
     * FinishProduceDate : 2016-03-30
     * FomilkDate : 2016-03-30
     * TestDate : 2016-05-24
     * AZCheckDate : 2016-04-08
     * ZGCheckDate : 2016-05-24
     * ValidDate : 2018-03-29
     * Manufacturing :
     * OutDate : 2016-10-14
     * TestDate1 : 2016-05-24
     * CowBatchNo :
     * ProductionCertificate : http://124.172.237.37:1516/Admin/UpLoadFile/Trace/2016/08/01/原产地证.jpg
     * AZTestCertificate : http://124.172.237.37:1516/Admin/UpLoadFile/Trace/2016/08/01/卫生证3.jpg
     * EnterCertificate : http://124.172.237.37:1516/Admin/UpLoadFile/Trace/2016/08/01/提单.jpg
     * ZGTestCertificate : http://124.172.237.37:1516/Admin/UpLoadFile/Trace/2016/08/01/30金装800克.jpg
     * Images : http://124.172.237.37:1516/Admin/UpLoadFile/Trace/2016/08/01/积3.png
     */

    private String Barnd;
    private String ProductName;
    private String Standard;
    private String BatchNO;
    private String FinishProduceDate;
    private String FomilkDate;
    private String TestDate;
    private String AZCheckDate;
    private String ZGCheckDate;
    private String ValidDate;
    private String Manufacturing;
    private String OutDate;
    private String TestDate1;
    private String CowBatchNo;
    private String ProductionCertificate;
    private String AZTestCertificate;
    private String EnterCertificate;
    private String ZGTestCertificate;
    private String Images;

    public String getBarnd() {
        return Barnd;
    }

    public void setBarnd(String Barnd) {
        this.Barnd = Barnd;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String Standard) {
        this.Standard = Standard;
    }

    public String getBatchNO() {
        return BatchNO;
    }

    public void setBatchNO(String BatchNO) {
        this.BatchNO = BatchNO;
    }

    public String getFinishProduceDate() {
        return FinishProduceDate;
    }

    public void setFinishProduceDate(String FinishProduceDate) {
        this.FinishProduceDate = FinishProduceDate;
    }

    public String getFomilkDate() {
        return FomilkDate;
    }

    public void setFomilkDate(String FomilkDate) {
        this.FomilkDate = FomilkDate;
    }

    public String getTestDate() {
        return TestDate;
    }

    public void setTestDate(String TestDate) {
        this.TestDate = TestDate;
    }

    public String getAZCheckDate() {
        return AZCheckDate;
    }

    public void setAZCheckDate(String AZCheckDate) {
        this.AZCheckDate = AZCheckDate;
    }

    public String getZGCheckDate() {
        return ZGCheckDate;
    }

    public void setZGCheckDate(String ZGCheckDate) {
        this.ZGCheckDate = ZGCheckDate;
    }

    public String getValidDate() {
        return ValidDate;
    }

    public void setValidDate(String ValidDate) {
        this.ValidDate = ValidDate;
    }

    public String getManufacturing() {
        return Manufacturing;
    }

    public void setManufacturing(String Manufacturing) {
        this.Manufacturing = Manufacturing;
    }

    public String getOutDate() {
        return OutDate;
    }

    public void setOutDate(String OutDate) {
        this.OutDate = OutDate;
    }

    public String getTestDate1() {
        return TestDate1;
    }

    public void setTestDate1(String TestDate1) {
        this.TestDate1 = TestDate1;
    }

    public String getCowBatchNo() {
        return CowBatchNo;
    }

    public void setCowBatchNo(String CowBatchNo) {
        this.CowBatchNo = CowBatchNo;
    }

    public String getProductionCertificate() {
        return ProductionCertificate;
    }

    public void setProductionCertificate(String ProductionCertificate) {
        this.ProductionCertificate = ProductionCertificate;
    }

    public String getAZTestCertificate() {
        return AZTestCertificate;
    }

    public void setAZTestCertificate(String AZTestCertificate) {
        this.AZTestCertificate = AZTestCertificate;
    }

    public String getEnterCertificate() {
        return EnterCertificate;
    }

    public void setEnterCertificate(String EnterCertificate) {
        this.EnterCertificate = EnterCertificate;
    }

    public String getZGTestCertificate() {
        return ZGTestCertificate;
    }

    public void setZGTestCertificate(String ZGTestCertificate) {
        this.ZGTestCertificate = ZGTestCertificate;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String Images) {
        this.Images = Images;
    }

    @Override
    public String toString() {
        return "SyInfo{" +
                "Barnd='" + Barnd + '\'' +
                ", ProductName='" + ProductName + '\'' +
                ", Standard='" + Standard + '\'' +
                ", BatchNO='" + BatchNO + '\'' +
                ", FinishProduceDate='" + FinishProduceDate + '\'' +
                ", FomilkDate='" + FomilkDate + '\'' +
                ", TestDate='" + TestDate + '\'' +
                ", AZCheckDate='" + AZCheckDate + '\'' +
                ", ZGCheckDate='" + ZGCheckDate + '\'' +
                ", ValidDate='" + ValidDate + '\'' +
                ", Manufacturing='" + Manufacturing + '\'' +
                ", OutDate='" + OutDate + '\'' +
                ", TestDate1='" + TestDate1 + '\'' +
                ", CowBatchNo='" + CowBatchNo + '\'' +
                ", ProductionCertificate='" + ProductionCertificate + '\'' +
                ", AZTestCertificate='" + AZTestCertificate + '\'' +
                ", EnterCertificate='" + EnterCertificate + '\'' +
                ", ZGTestCertificate='" + ZGTestCertificate + '\'' +
                ", Images='" + Images + '\'' +
                '}';
    }
}
