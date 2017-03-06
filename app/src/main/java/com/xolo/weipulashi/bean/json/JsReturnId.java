package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/8.
 */

public class JsReturnId {
    private String RerurnID;
    private String Employee_ID;

    public JsReturnId(String rerurnID) {
        RerurnID = rerurnID;
    }

    public JsReturnId(String rerurnID, String employee_ID) {
        RerurnID = rerurnID;
        Employee_ID = employee_ID;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        Employee_ID = employee_ID;
    }

    public String getRerurnID() {
        return RerurnID;
    }

    public void setRerurnID(String rerurnID) {
        RerurnID = rerurnID;
    }
}
