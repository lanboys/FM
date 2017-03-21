package com.bing.lan.inke.yingke.bean;

import java.io.Serializable;

/**
 * Created by kay on 16/11/14.
 */
public class LivesBean implements Serializable {

    /**
     * city : 哈尔滨市
     * group : 0
     * id : 1479541998100370
     * link : 0
     * multi : 0
     * name :
     * online_users : 18379
     * optimal : 0
     * rotate : 0
     * share_addr : http://mlive4.inke.cn/share/live.html?uid=4262984&liveid=1479541998100370&ctime=1479541998
     * slot : 3
     * stream_addr : http://pull99.a8.com/live/1479541998100370.flv
     * version : 0
     */

    private String city;
    private int group;
    private String id;
    private int link;
    private int multi;
    //外层的底部
    private String name;
    private int online_users;
    private int optimal;
    private int rotate;
    private String share_addr;
    private int slot;
    private String stream_addr;
    private int version;
    private CreatorBean creator;

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLink() {
        return link;
    }

    public void setLink(int link) {
        this.link = link;
    }

    public int getMulti() {
        return multi;
    }

    public void setMulti(int multi) {
        this.multi = multi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOnline_users() {
        return online_users;
    }

    public void setOnline_users(int online_users) {
        this.online_users = online_users;
    }

    public int getOptimal() {
        return optimal;
    }

    public void setOptimal(int optimal) {
        this.optimal = optimal;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public String getShare_addr() {
        return share_addr;
    }

    public void setShare_addr(String share_addr) {
        this.share_addr = share_addr;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getStream_addr() {
        return stream_addr;
    }

    public void setStream_addr(String stream_addr) {
        this.stream_addr = stream_addr;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LivesBean{" +
                "city='" + city + '\'' +
                ", group=" + group +
                ", id='" + id + '\'' +
                ", link=" + link +
                ", multi=" + multi +
                ", name='" + name + '\'' +
                ", online_users=" + online_users +
                ", optimal=" + optimal +
                ", rotate=" + rotate +
                ", share_addr='" + share_addr + '\'' +
                ", slot=" + slot +
                ", stream_addr='" + stream_addr + '\'' +
                ", version=" + version +
                ", creator=" + creator +
                '}';
    }
}
