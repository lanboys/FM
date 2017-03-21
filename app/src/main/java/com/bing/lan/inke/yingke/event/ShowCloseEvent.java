package com.bing.lan.inke.yingke.event;

/**
 * Created by kay on 16/12/30.
 */

public class ShowCloseEvent {
    boolean isShow = false;

    public ShowCloseEvent(boolean isShow) {
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }
}
