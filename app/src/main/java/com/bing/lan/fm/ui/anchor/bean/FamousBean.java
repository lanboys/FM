package com.bing.lan.fm.ui.anchor.bean;

import java.util.List;

/**
 * @author lihoujing2ken
 * @time 2017/3/2  15:10
 * @desc 1级数据
 */
public class FamousBean {
    private int id;
    private String title;
    private int displayStyle;
    private List<ListBeanA> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(int displayStyle) {
        this.displayStyle = displayStyle;
    }

    public List<ListBeanA> getList() {
        return list;
    }

    public void setList(List<ListBeanA> list) {
        this.list = list;
    }

}
