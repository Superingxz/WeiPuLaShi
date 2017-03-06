package com.xolo.weipulashi.bean.json;

/**
 * Created by Administrator on 2017/2/16.
 * BranchID	Int	是		机构ID
 Phone	String	否		会员手机号
 EmpName	String	否		会员真实名称
 StartDate	DateTime	否	1900-01-01	开始时间
 EndDate	DateTime	否	1900-01-01	结束时间
 PageSize	Int	是		页码大小（不传参数情况下默认每页显示10条）
 PageIndex	Int	是		当前页（不传参数情况下默认显示第一页数据）
 */

public class JsGetAssistDuijiangList {
    private  int BranchID;
    private String phone;
    private  String  EmpName;
    private  String  StartDate;
    private  String  EndDate;
    private  String  PageSize;
    private  String  PageIndex;

    public JsGetAssistDuijiangList(int branchID, String phone, String empName, String startDate, String endDate, String pageSize, String pageIndex) {
        BranchID = branchID;
        this.phone = phone;
        EmpName = empName;
        StartDate = startDate;
        EndDate = endDate;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }

    public int getBranchID() {
        return BranchID;
    }

    public void setBranchID(int branchID) {
        BranchID = branchID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }
}
