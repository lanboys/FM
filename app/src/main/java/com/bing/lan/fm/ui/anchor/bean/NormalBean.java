package com.bing.lan.fm.ui.anchor.bean;

import java.util.List;

/**
 * @author lihoujing2ken
 * @time 2017/3/2  15:17
 * @desc 2级数据
 */
public class NormalBean {
    private int id;
    private String name;
    private String title;
    private List<ListBeanX> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }
}
