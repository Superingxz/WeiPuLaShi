package com.xolo.weipulashi.bean.json;

/**
 * 一键签收
 */

public class JsCheckSaleAll {
    private String BranchID;
    private String WarehouseID;
    private String EmployeeID;

    public JsCheckSaleAll(String branchID, String warehouseID, String employeeID) {
        BranchID = branchID;
        WarehouseID = warehouseID;
        EmployeeID = employeeID;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public String getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        WarehouseID = warehouseID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }
}
