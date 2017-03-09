package com.bing.lan.fm.ui.hot.bean;

import com.bing.lan.fm.bean.ResultBean;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotResult1 extends ResultBean {

    private HotInfoBean<ListItemEditorBean> cityColumn;
    private HotInfoBean<ListItemDiscoverBean> discoveryColumns;
    private HotInfoBean<ListItemGuessBean> guess;
    private HotRecommendsBean hotRecommends;
    private HotInfoBean<ListItemEditorBean> member;
    private HotInfoBean<ListItemEditorBean> paidArea;

    @Override
    public String toString() {
        return "HotResult1{" +
                "cityColumn=" + cityColumn +
                ", discoveryColumns=" + discoveryColumns +
                ", guess=" + guess +
                ", hotRecommends=" + hotRecommends +
                ", member=" + member +
                ", paidArea=" + paidArea +
                '}';
    }

    public HotInfoBean<ListItemEditorBean> getCityColumn() {
        return cityColumn;
    }

    public void setCityColumn(HotInfoBean<ListItemEditorBean> cityColumn) {
        this.cityColumn = cityColumn;
    }

    public HotInfoBean<ListItemDiscoverBean> getDiscoveryColumns() {
        return discoveryColumns;
    }

    public void setDiscoveryColumns(HotInfoBean<ListItemDiscoverBean> discoveryColumns) {
        this.discoveryColumns = discoveryColumns;
    }

    public HotInfoBean<ListItemGuessBean> getGuess() {
        return guess;
    }

    public void setGuess(HotInfoBean<ListItemGuessBean> guess) {
        this.guess = guess;
    }

    public HotRecommendsBean getHotRecommends() {
        return hotRecommends;
    }

    public void setHotRecommends(HotRecommendsBean hotRecommends) {
        this.hotRecommends = hotRecommends;
    }

    public HotInfoBean<ListItemEditorBean> getMember() {
        return member;
    }

    public void setMember(HotInfoBean<ListItemEditorBean> member) {
        this.member = member;
    }

    public HotInfoBean<ListItemEditorBean> getPaidArea() {
        return paidArea;
    }

    public void setPaidArea(HotInfoBean<ListItemEditorBean> paidArea) {
        this.paidArea = paidArea;
    }

    public static class HotRecommendsBean {

        @SerializedName("ret")
        private int retX;
        private String title;
        private List<HotInfoBean<ListItemEditorBean>> list;

        public static HotRecommendsBean objectFromData(String str) {

            return new Gson().fromJson(str, HotRecommendsBean.class);
        }

        public int getRetX() {
            return retX;
        }

        public void setRetX(int retX) {
            this.retX = retX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<HotInfoBean<ListItemEditorBean>> getList() {
            return list;
        }

        public void setList(List<HotInfoBean<ListItemEditorBean>> list) {
            this.list = list;
        }
    }
}