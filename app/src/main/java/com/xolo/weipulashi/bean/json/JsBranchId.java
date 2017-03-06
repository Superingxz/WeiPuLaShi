package com.xolo.weipulashi.bean.json;

/**
 * 提交用户部门id
 */
public class JsBranchId {
    private String BranchId;

    public JsBranchId(String branchID) {
        BranchId = branchID;
    }

    public String getBranchID() {
        return BranchId;
    }

    public void setBranchID(String branchID) {
        BranchId = branchID;
    }
}
