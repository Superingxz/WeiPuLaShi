package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/10.
 */

public class JsMScoreByStore {

    /**
     * Store_ID : 1
     * Phone : ******
     * JFCode : ********
     * EmpId : 3
     */

    private int Store_ID;
    private String Phone;
    private String JFCode;
    private int EmpId;

    public JsMScoreByStore(int store_ID, String phone, String JFCode, int empId) {
        Store_ID = store_ID;
        Phone = phone;
        this.JFCode = JFCode;
        EmpId = empId;
    }

    public int getStore_ID() {
        return Store_ID;
    }

    public void setStore_ID(int Store_ID) {
        this.Store_ID = Store_ID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getJFCode() {
        return JFCode;
    }

    public void setJFCode(String JFCode) {
        this.JFCode = JFCode;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int EmpId) {
        this.EmpId = EmpId;
    }
}
