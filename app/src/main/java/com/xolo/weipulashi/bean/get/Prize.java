package com.xolo.weipulashi.bean.get;

/**
 * 奖品
 */

public class Prize {

    /**
     * id : 10
     * ImgPath : http://124.172.237.37:1516/File/PrizePic/201607271108fd0dd1ea-b290-447a-a30a-e1469228eddb.jpg
     * PrizeName : 澳洲安慕雪黑安格斯儿童牛排
     * LevelName : 普通会员
     * IntegralValue : 1500
     */

    private int id;
    private String ImgPath;
    private String PrizeName;
    private String LevelName;
    private String IntegralValue;


    public Prize(int id, String imgPath, String prizeName, String levelName, String integralValue) {
        this.id = id;
        ImgPath = imgPath;
        PrizeName = prizeName;
        LevelName = levelName;
        IntegralValue = integralValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String ImgPath) {
        this.ImgPath = ImgPath;
    }

    public String getPrizeName() {
        return PrizeName;
    }

    public void setPrizeName(String PrizeName) {
        this.PrizeName = PrizeName;
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

    public void setIntegralValue(String IntegralValue) {
        this.IntegralValue = IntegralValue;
    }
}
