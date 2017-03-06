package com.xolo.weipulashi.bean.get;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Address implements Serializable{

    /**
     * ID : 369
     * Receiver : 胡晓云
     * Tel : 13822250317
     * Address : 广东省广州市海珠区测试
     * IsDefault : false
     */

    private int ID;
    private String Receiver;
    private String Tel;
    private String Address;
    private boolean IsDefault;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean isIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(boolean IsDefault) {
        this.IsDefault = IsDefault;
    }
}
