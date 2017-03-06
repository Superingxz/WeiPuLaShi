package com.xolo.weipulashi.bean.get;

import java.util.List;

import static com.xolo.weipulashi.R.id.IntegralValue;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Duijiang {

    /**
     * id : 211
     * Phone : 15603027221
     * LevelName : 普通会员
     * IntegralValue : 100
     * TotalCount : 3
     * PrizeList : [{"id":13,"ImgPath":"http://124.172.237.37:1516/File/PrizePic/201607271107d9e79a26-aa7c-4e07-a9ee-80cff72e512a.jpg","PrizeName":"澳洲安慕雪眼肉牛排","LevelName":"普通会员","IntegralValue":500},{"id":14,"ImgPath":"http://124.172.237.37:1516/File/PrizePic/20160727110766a8ee04-a9a1-4848-a31f-74a8d382c7ea.jpg","PrizeName":"澳洲安慕雪西冷牛排","LevelName":"普通会员","IntegralValue":500},{"id":24,"ImgPath":"http://124.172.237.37:1516/File/PrizePic/20160920092960af04ff-f1dc-44d1-805c-adf97d208a1b.png","PrizeName":"测试奖品","LevelName":"普通会员","IntegralValue":200}]
     */

    private int id;
    private String Phone;
    private String LevelName;
    private String IntegralValue;
    private String TotalCount;

    /**
     * id : 13
     * ImgPath : http://124.172.237.37:1516/File/PrizePic/201607271107d9e79a26-aa7c-4e07-a9ee-80cff72e512a.jpg
     * PrizeName : 澳洲安慕雪眼肉牛排
     * LevelName : 普通会员
     * IntegralValue : 500
     */

    private List<Prize> PrizeList;

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

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String LevelName) {
        this.LevelName = LevelName;
    }

    public String getIntegralValue() {
        return IntegralValue;
    }

    public void setIntegralValue(String integralValue) {
        IntegralValue = integralValue;
    }

    public String getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(String totalCount) {
        TotalCount = totalCount;
    }

    public List<Prize> getPrizeList() {
        return PrizeList;
    }

    public void setPrizeList(List<Prize> prizeList) {
        PrizeList = prizeList;
    }

    public List<Prize> getPrize() {
        return PrizeList;
    }

    public void setPrize(List<Prize> PrizeList) {
        this.PrizeList = PrizeList;
    }

}
