package com.xolo.weipulashi.bean.json;

/**
 * 3.3.4.扫码签收接口（扫一个码签收整张单）
 */

public class JsQSScanCheckAll {

    /**
     * SaleID : 256
     * BarCode : 9797990987
     * WarehouseID : 10
     * EmployeeID : 1
     */

    private String SaleID;
    private String BarCode;
    private int WarehouseID;
    private int EmployeeID;

    public JsQSScanCheckAll(String saleID, String barCode, int warehouseID, int employeeID) {
        SaleID = saleID;
        BarCode = barCode;
        WarehouseID = warehouseID;
        EmployeeID = employeeID;
    }

    public String getSaleID() {
        return SaleID;
    }

    public void setSaleID(String SaleID) {
        this.SaleID = SaleID;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(int WarehouseID) {
        this.WarehouseID = WarehouseID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }
}
