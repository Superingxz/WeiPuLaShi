package com.xolo.weipulashi.bean.get;

/**
 * 创库
 */

public class WareHouse {
    private boolean isSelect;
    /**
     * id : 943
     * Code : CK00943
     * Name : 温州辉腾商贸有限公司_仓库
     */

    private int id;
    private String Code;
    private String Name;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
