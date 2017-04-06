package com.bing.lan.inke.yingke.bean;

import java.util.List;


public class NearDates {



    private int expire_time;

    private List<NearBean> lives;

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public List<NearBean> getLives() {
        return lives;
    }

    public void setLives(List<NearBean> lives) {
        this.lives = lives;
    }
}
