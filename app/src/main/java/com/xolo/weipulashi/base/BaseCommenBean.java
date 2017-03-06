package com.xolo.weipulashi.base;

import java.util.List;

/**
 * Created by Administrator on 2017/2/5.
 */

public class BaseCommenBean<T>{
    private String res;
    private String msg;
    private T dt;

    public T getT() {
        return dt;
    }

    public void setT(T t) {
        this.dt = t;
    }

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


}
