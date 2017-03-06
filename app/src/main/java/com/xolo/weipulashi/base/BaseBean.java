package com.xolo.weipulashi.base;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class BaseBean<T>{
    private String res;
    private String msg;
    private List<T> dt;

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDt() {
        return dt;
    }

    public void setDt(List<T> dt) {
        this.dt = dt;
    }
}
