package com.xolo.weipulashi.bean;

/**
 * Created by Administrator on 2017/2/5.
 */

public class Menu {
    private  int src;
    private  String  title;
    private  Class c;

    public Menu(int src, String title, Class c) {
        this.src = src;
        this.title = title;
        this.c = c;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }
}
