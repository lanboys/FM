package com.bing.lan.fm.ui.top.bean;

/**
 * @author jk
 * @time 2017/3/8  20:07
 * @desc ${TODD}
 */
public class ListBean {
    /**
     * activityId : 153
     * focusCurrentId : 9488
     * id : 3791
     * isShare : true
     * is_External_url : false
     * longTitle : 全球华语播客巅峰榜（第89期）
     * pic : http://fdfs.xmcdn.com/group18/M06/B5/5C/wKgJKli8y-2j6ipLAAEW5hv2d7A403_android_large.jpg
     * shortTitle : 全球华语播客巅峰榜
     * type : 8
     * url : http://m.ximalaya.com/xmposter/top_list?app=iting
     */

    private int activityId;
    private int focusCurrentId;
    private int id;
    private boolean isShare;
    private boolean is_External_url;
    private String longTitle;
    private String pic;
    private String shortTitle;
    private int type;
    private String url;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getFocusCurrentId() {
        return focusCurrentId;
    }

    public void setFocusCurrentId(int focusCurrentId) {
        this.focusCurrentId = focusCurrentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
