package com.bing.lan.fm.ui.recommend.bean;

import com.bing.lan.fm.ui.hot.bean.IAlbum;

import java.io.Serializable;


public class AlbumBean implements Serializable,IAlbum {
    /**
     * albumId : 248886
     * category_id : 4
     * coverLarge : http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg
     * coverMiddle : http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg
     * cover_path : http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg
     * info : 都市的喧嚣，热闹的街道，坐下来喝杯茶，静下心来，享受小武带给你的轻松与惬意。都市聊聊吧，撩拨你的心弦！ 第一期节目：马文婚变，谁是谁非；京东一哥，恋上女神；武大校花赏樱花，清秀淡雅文艺范儿。
     * isDeleted : false
     * isFinished : 0
     * tags : 热门评述,十里铺人民广播,热点话题,娱乐八卦,明星
     * title : 名人风范
     * tracks : 118
     * uid : 3121164
     */

    private long albumId;
    private int category_id;
    private String coverLarge;
    private String coverMiddle;
    private String cover_path;
    private String info;
    private boolean isDeleted;
    private int isFinished;
    private String tags;
    private String title;
    private int tracks;
    private int uid;

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public String getCoverMiddle() {
        return coverMiddle;
    }

    public void setCoverMiddle(String coverMiddle) {
        this.coverMiddle = coverMiddle;
    }

    public String getCover_path() {
        return cover_path;
    }

    public void setCover_path(String cover_path) {
        this.cover_path = cover_path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
