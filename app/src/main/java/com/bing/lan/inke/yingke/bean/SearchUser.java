package com.bing.lan.inke.yingke.bean;



public class SearchUser {

    /**
     * birth : 1990-12-17
     * description : 感恩！
     * emotion : 保密
     * gender : 0
     * gmutex : 0
     * hometown : 火星&
     * id : 68841384
     * inke_verify : 0
     * level : 43
     * location : 长沙市
     * nick : 乖乖。
     * portrait : http://img2.inke.cn/MTQ4MDQ5OTAyNDA1NSM1MiNqcGc=.jpg
     * profession : 买衣服淘宝🔍L50 STUDIO
     * rank_veri : 7
     * sex : 0
     * third_platform : 0
     * veri_info : 名媛
     * verified : 0
     * verified_reason :
     */

    private String birth;
    private String description;
    private String emotion;
    private int gender;
    private int gmutex;
    private String hometown;
    private int id;
    private int inke_verify;
    private int level;
    private String location;
    private String nick;
    private String portrait;
    private String profession;
    private int rank_veri;
    private int sex;
    private String third_platform;
    private String veri_info;
    private int verified;
    private String verified_reason;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getGmutex() {
        return gmutex;
    }

    public void setGmutex(int gmutex) {
        this.gmutex = gmutex;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInke_verify() {
        return inke_verify;
    }

    public void setInke_verify(int inke_verify) {
        this.inke_verify = inke_verify;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getRank_veri() {
        return rank_veri;
    }

    public void setRank_veri(int rank_veri) {
        this.rank_veri = rank_veri;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getThird_platform() {
        return third_platform;
    }

    public void setThird_platform(String third_platform) {
        this.third_platform = third_platform;
    }

    public String getVeri_info() {
        return veri_info;
    }

    public void setVeri_info(String veri_info) {
        this.veri_info = veri_info;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    @Override
    public String toString() {
        return "SearchUser{" +
                "birth='" + birth + '\'' +
                ", description='" + description + '\'' +
                ", emotion='" + emotion + '\'' +
                ", gender=" + gender +
                ", gmutex=" + gmutex +
                ", hometown='" + hometown + '\'' +
                ", id=" + id +
                ", inke_verify=" + inke_verify +
                ", level=" + level +
                ", location='" + location + '\'' +
                ", nick='" + nick + '\'' +
                ", portrait='" + portrait + '\'' +
                ", profession='" + profession + '\'' +
                ", rank_veri=" + rank_veri +
                ", sex=" + sex +
                ", third_platform='" + third_platform + '\'' +
                ", veri_info='" + veri_info + '\'' +
                ", verified=" + verified +
                ", verified_reason='" + verified_reason + '\'' +
                '}';
    }
}
