package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/8.
 */

public class ReturnInfo{

    /**
     * ID : 128
     * RerurnCode : R16122915555801
     * WorkDate : 2016-12-29
     * RerurnName : 陕西吉婴商贸
     * CheckDate : 2016-12-29
     * CheckName : 仓储部
     * state : 审核通过
     */

    private int ID;
    private String RerurnCode;
    private String WorkDate;
    private String RerurnName;
    private String CheckDate;
    private String CheckName;
    private String state;

    public ReturnInfo(int ID, String workDate, String rerurnName, String state, String rerurnCode) {
        this.ID = ID;
        WorkDate = workDate;
        RerurnName = rerurnName;
        this.state = state;
        RerurnCode = rerurnCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRerurnCode() {
        return RerurnCode;
    }

    public void setRerurnCode(String RerurnCode) {
        this.RerurnCode = RerurnCode;
    }

    public String getWorkDate() {
        return WorkDate;
    }

    public void setWorkDate(String WorkDate) {
        this.WorkDate = WorkDate;
    }

    public String getRerurnName() {
        return RerurnName;
    }

    public void setRerurnName(String RerurnName) {
        this.RerurnName = RerurnName;
    }

    public String getCheckDate() {
        return CheckDate;
    }

    public void setCheckDate(String CheckDate) {
        this.CheckDate = CheckDate;
    }

    public String getCheckName() {
        return CheckName;
    }

    public void setCheckName(String CheckName) {
        this.CheckName = CheckName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
