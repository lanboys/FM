package com.bing.lan.inke.yingke.bean;

import java.util.List;



public class SearchRecomd {
    private  String title;
    private List<LivesBean> lives;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LivesBean> getLives() {
        return lives;
    }

    public void setLives(List<LivesBean> lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "SearchRecomd{" +
                "title='" + title + '\'' +
                ", lives=" + lives +
                '}';
    }
}
