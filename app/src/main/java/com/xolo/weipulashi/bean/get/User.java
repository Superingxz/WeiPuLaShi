package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/5.
 */

public class User {

    /**
     * name : 太仓横泾豆豆暖房
     * BType : 2
     * UserName : JS10162
     * TrueName : 史艳丽
     * Sex : 0
     * Phone : 88888888
     * Email : xolo@qq.com
     * EmployeeID : 972
     * branch_id : 933
     */

    private String name;
    private int BType;
    private String UserName;
    private String TrueName;
    private String Sex;
    private String Phone;
    private String Email;
    private int EmployeeID;
    private int branch_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBType() {
        return BType;
    }

    public void setBType(int BType) {
        this.BType = BType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String TrueName) {
        this.TrueName = TrueName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", BType=" + BType +
                ", UserName='" + UserName + '\'' +
                ", TrueName='" + TrueName + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                ", EmployeeID=" + EmployeeID +
                ", branch_id=" + branch_id +
                '}';
    }
}
