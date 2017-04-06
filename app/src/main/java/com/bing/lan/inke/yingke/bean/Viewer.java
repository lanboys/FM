package com.bing.lan.inke.yingke.bean;



public class Viewer {


    /**
     * birth :
     * description :
     * emotion : 恋爱中
     * ext : {"cl":"#F3D088","light":"http://img2.inke.cn/MTQ4Mjk5NTI3ODUxMiMyOSNqcGc=.jpg"}
     * gender : 1
     * gmutex : 0
     * hometown :
     * id : 9488016
     * inke_verify : 0
     * level : 55
     * location : 南京市
     * nick : Devi鱼刺
     * portrait : ODU1NjYxNDYzMDYzMDc2.jpg
     * profession :
     * rank_veri : 8
     * sex : 1
     * third_platform : 0
     * veri_info : 型男
     * verified : 0
     * verified_reason :
     */

    private String birth;
    private String description;
    private String emotion;
    /**
     * cl : #F3D088
     * light : http://img2.inke.cn/MTQ4Mjk5NTI3ODUxMiMyOSNqcGc=.jpg
     */

    private ExtBean ext;
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

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
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

    public static class ExtBean {
        private String cl;
        private String light;

        public String getCl() {
            return cl;
        }

        public void setCl(String cl) {
            this.cl = cl;
        }

        public String getLight() {
            return light;
        }

        public void setLight(String light) {
            this.light = light;
        }
    }


    @Override
    public String toString() {
        return "Viewer{" +
                "birth='" + birth + '\'' +
                ", description='" + description + '\'' +
                ", emotion='" + emotion + '\'' +
                ", ext=" + ext +
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
