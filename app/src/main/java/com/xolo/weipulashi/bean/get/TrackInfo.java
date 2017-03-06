package com.xolo.weipulashi.bean.get;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class TrackInfo {

    /**
     * Code : 6506912062为小条码！
     * State : 库存状态
     * WName : 重庆爱之馨_仓库
     * ProductName : 安宝乐金装婴儿配方奶粉1段
     * ProductNo : ANB27BAF100
     * TrackList : [{"BranchName":"重庆爱之馨","Type":"签收入库","WorkDate":"2016-05-03","MBarcode":"7789064170","SBarCode":"6506912062"},{"BranchName":"安宝乐总部","Type":"在线入库","WorkDate":"2016-04-25","MBarcode":"7789064170","SBarCode":"6506912062"},{"BranchName":"安宝乐总部","Type":"在线出库","WorkDate":"2016-04-26","MBarcode":"7789064170","SBarCode":"6506912062"}]
     */

    private String Code;
    private String State;
    private String WName;
    private String ProductName;
    private String ProductNo;
    private String TrackName;

    public String getTrackName() {
        return TrackName;
    }

    public void setTrackName(String trackName) {
        TrackName = trackName;
    }

    /**
     * BranchName : 重庆爱之馨
     * Type : 签收入库
     * WorkDate : 2016-05-03
     * MBarcode : 7789064170
     * SBarCode : 6506912062
     */



    private List<TrackListBean> TrackList;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getWName() {
        return WName;
    }

    public void setWName(String WName) {
        this.WName = WName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductNo() {
        return ProductNo;
    }

    public void setProductNo(String ProductNo) {
        this.ProductNo = ProductNo;
    }

    public List<TrackListBean> getTrackList() {
        return TrackList;
    }

    public void setTrackList(List<TrackListBean> TrackList) {
        this.TrackList = TrackList;
    }

    public static class TrackListBean {
        private String BranchName;
        private String Type;
        private String WorkDate;
        private String MBarcode;
        private String SBarCode;

        public String getBranchName() {
            return BranchName;
        }

        public void setBranchName(String BranchName) {
            this.BranchName = BranchName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getWorkDate() {
            return WorkDate;
        }

        public void setWorkDate(String WorkDate) {
            this.WorkDate = WorkDate;
        }

        public String getMBarcode() {
            return MBarcode;
        }

        public void setMBarcode(String MBarcode) {
            this.MBarcode = MBarcode;
        }

        public String getSBarCode() {
            return SBarCode;
        }

        public void setSBarCode(String SBarCode) {
            this.SBarCode = SBarCode;
        }
    }
}
