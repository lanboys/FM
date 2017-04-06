package com.bing.lan.fm.ui.top.bean;

import java.util.List;


public class TopBean {


    private FocusImagesBean focusImages;
    private String msg;
    private int ret;
    private List<DatasBean> datas;

    public FocusImagesBean getFocusImages() {
        return focusImages;
    }

    public void setFocusImages(FocusImagesBean focusImages) {
        this.focusImages = focusImages;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }
}
