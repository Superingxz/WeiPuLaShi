package com.xolo.weipulashi.bean.get;

/**
 * Created by Administrator on 2017/2/16.
 */

public class OrderState {
    private  boolean isSelect;
    private  String  content;
    private  int id;

    public OrderState(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
