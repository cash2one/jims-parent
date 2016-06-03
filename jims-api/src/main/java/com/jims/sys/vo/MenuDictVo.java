package com.jims.sys.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MenuDictVo implements Serializable{

    private String id;
    private String text;        // 菜单名称
    private String state="open";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
