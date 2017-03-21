package com.bing.lan.inke.yingke.bean;


import java.io.Serializable;

public  class CreatorBean implements Serializable{

    /**
     * id : 602112
     * level : 138
     * gender : 0
     * nick : 王小喵
     * portrait : MTQ3ODg2MDI5NDIwNiMzNTMjanBn.jpg
     */

    private int id;
    private int level;
    private int gender;
    private String nick;
    private String portrait;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }


    @Override
    public String toString() {
        return "CreatorBean{" +
                "id=" + id +
                ", level=" + level +
                ", gender=" + gender +
                ", nick='" + nick + '\'' +
                ", portrait='" + portrait + '\'' +
                '}';
    }
}
