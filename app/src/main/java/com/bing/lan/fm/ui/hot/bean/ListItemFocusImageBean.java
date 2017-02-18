package com.bing.lan.fm.ui.hot.bean;

/**
 * @author 蓝兵
 * @time 2017/2/17  15:27
 */
public class ListItemFocusImageBean {
    //广告轮播图

    /**
     * id : 14620
     * shortTitle : 【首页】蒙曼品最美唐诗（付费精品）
     * longTitle : 【首页】蒙曼品最美唐诗（付费精品）
     * pic : http://fdfs.xmcdn.com/group24/M02/8D/40/wKgJNVildqfxciPUAAFtWYynfmI404_android_large.jpg
     * type : 2
     * uid : 71357374
     * albumId : 6693171
     * isShare : true
     * is_External_url : false
     * focusCurrentId : 57210
     */

    private int id;
    private String shortTitle;
    private String longTitle;
    private String pic;
    private int type;
    private int uid;
    private int albumId;
    private boolean isShare;
    private boolean is_External_url;
    private int focusCurrentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public boolean isIsShare() {
        return isShare;
    }

    public void setIsShare(boolean isShare) {
        this.isShare = isShare;
    }

    public boolean isIs_External_url() {
        return is_External_url;
    }

    public void setIs_External_url(boolean is_External_url) {
        this.is_External_url = is_External_url;
    }

    public int getFocusCurrentId() {
        return focusCurrentId;
    }

    public void setFocusCurrentId(int focusCurrentId) {
        this.focusCurrentId = focusCurrentId;
    }
}
