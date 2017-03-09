package com.bing.lan.fm.ui.top.bean;

import java.util.List;

/**
 * @author jk
 * @time 2017/3/7  19:39
 * @desc ${TODD}
 */
public class DatasBean {

    private int count;
    private int ret;
    private String title;
    private List<ListBeanX> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
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
