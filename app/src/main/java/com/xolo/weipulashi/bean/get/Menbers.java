package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/10.
 */

public class Menbers {

    /**
     * id : 4045
     * Phone : 18225496934
     * RealName : 谭品凌
     * BranchName : 重庆爱之馨
     * IntegralValue : 180
     * ExchangeNum : 0
     * IntegralNum : 1
     */

    private int id;
    private String Phone;
    private String RealName;
    private String BranchName;
    private int IntegralValue;
    private int ExchangeNum;
    private int IntegralNum;
    private String HeadImage;

    public Menbers(String phone, String realName, String branchName, int integralValue, int exchangeNum, int integralNum) {
        Phone = phone;
        RealName = realName;
        BranchName = branchName;
        IntegralValue = integralValue;
        ExchangeNum = exchangeNum;
        IntegralNum = integralNum;
    }

    public String getHeadImage() {
        return HeadImage;
    }

    public void setHeadImage(String headImage) {
        HeadImage = headImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String BranchName) {
        this.BranchName = BranchName;
    }

    public int getIntegralValue() {
        return IntegralValue;
    }

    public void setIntegralValue(int IntegralValue) {
        this.IntegralValue = IntegralValue;
    }

    public int getExchangeNum() {
        return ExchangeNum;
    }

    public void setExchangeNum(int ExchangeNum) {
        this.ExchangeNum = ExchangeNum;
    }

    public int getIntegralNum() {
        return IntegralNum;
    }

    public void setIntegralNum(int IntegralNum) {
        this.IntegralNum = IntegralNum;
    }
}
