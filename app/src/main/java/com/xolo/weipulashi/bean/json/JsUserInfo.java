package com.xolo.weipulashi.bean.json;

/**
 * 提交用户部门id
 */
public class JsUserInfo {
    private String BranchID;

    public JsUserInfo(String branchID) {
        BranchID = branchID;
    }

    public String getBranchID() {
        return BranchID;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }
}
