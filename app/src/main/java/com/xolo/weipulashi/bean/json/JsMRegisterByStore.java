package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/10.
 */

public class JsMRegisterByStore {

    /**
     * Store_ID : 49
     * Phone : ********
     * PassWord : *******
     * RPassWord : *******
     */

    private int Store_ID;
    private String Phone;
    private String PassWord;
    private String RPassWord;

    public JsMRegisterByStore(int store_ID, String phone, String passWord, String RPassWord) {
        Store_ID = store_ID;
        Phone = phone;
        PassWord = passWord;
        this.RPassWord = RPassWord;
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

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public String getRPassWord() {
        return RPassWord;
    }

    public void setRPassWord(String RPassWord) {
        this.RPassWord = RPassWord;
    }
}
