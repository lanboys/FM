package com.bing.lan.inke.yingke.bean;

import java.util.List;



public class Gifts {

    List<Gift> gifts;

    String error_msg;

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    @Override
    public String toString() {
        return "Gifts{" +
                "gifts=" + gifts +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
