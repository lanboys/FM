package com.bing.lan.inke.yingke.event;



public class ShowCloseEvent {
    boolean isShow = false;

    public ShowCloseEvent(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }
}
