package com.xolo.weipulashi.bean.get;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/2/16.
 */

public class DuiJiangRecord implements Parcelable {

    /**
     * ID : 6
     * BranchName : 安宝乐总部
     * RealName : 胡晓云
     * Phone : 13112297627
     * WorkDate : 2016-09-24
     * ToTalIntegralValue : 200
     */

    private int ID;
    private String BranchName;
    private String RealName;
    private String Phone;
    private String WorkDate;
    private int ToTalIntegralValue;
    /**
     * IntegralValue : 1000.0
     * Name : 测试门店
     * Code : 20160822
     */

    private double IntegralValue;
    private String Name;
    private String Code;


    public DuiJiangRecord(int ID, String branchName, String realName, String phone, String workDate, int toTalIntegralValue) {
        this.ID = ID;
        BranchName = branchName;
        RealName = realName;
        Phone = phone;
        WorkDate = workDate;
        ToTalIntegralValue = toTalIntegralValue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public int getToTalIntegralValue() {
        return ToTalIntegralValue;
    }

    public void setToTalIntegralValue(int ToTalIntegralValue) {
        this.ToTalIntegralValue = ToTalIntegralValue;
    }



    public double getIntegralValue() {
        return IntegralValue;
    }

    public void setIntegralValue(double IntegralValue) {
        this.IntegralValue = IntegralValue;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.BranchName);
        dest.writeString(this.RealName);
        dest.writeString(this.Phone);
        dest.writeString(this.WorkDate);
        dest.writeInt(this.ToTalIntegralValue);
        dest.writeDouble(this.IntegralValue);
        dest.writeString(this.Name);
        dest.writeString(this.Code);
    }

    protected DuiJiangRecord(Parcel in) {
        this.ID = in.readInt();
        this.BranchName = in.readString();
        this.RealName = in.readString();
        this.Phone = in.readString();
        this.WorkDate = in.readString();
        this.ToTalIntegralValue = in.readInt();
        this.IntegralValue = in.readDouble();
        this.Name = in.readString();
        this.Code = in.readString();
    }

    public static final Creator<DuiJiangRecord> CREATOR = new Creator<DuiJiangRecord>() {
        @Override
        public DuiJiangRecord createFromParcel(Parcel source) {
            return new DuiJiangRecord(source);
        }

        @Override
        public DuiJiangRecord[] newArray(int size) {
            return new DuiJiangRecord[size];
        }
    };
}
