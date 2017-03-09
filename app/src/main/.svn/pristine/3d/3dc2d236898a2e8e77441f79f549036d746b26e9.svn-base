package com.bing.lan.fm.ui.hot.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @time 2017/3/5  17:41
 */
public class ListItemDiscoverBean implements Serializable {

    private String bubbleText;
    private String contentType;
    private int contentUpdatedAt;
    private String coverPath;
    private boolean enableShare;
    private int id;
    private boolean isExternalUrl;
    private PropertiesBean properties;
    private String sharePic;
    private String subtitle;
    private String title;
    private String url;

    public String getBubbleText() {
        return bubbleText;
    }

    public void setBubbleText(String bubbleText) {
        this.bubbleText = bubbleText;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getContentUpdatedAt() {
        return contentUpdatedAt;
    }

    public void setContentUpdatedAt(int contentUpdatedAt) {
        this.contentUpdatedAt = contentUpdatedAt;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public boolean isEnableShare() {
        return enableShare;
    }

    public void setEnableShare(boolean enableShare) {
        this.enableShare = enableShare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsExternalUrl() {
        return isExternalUrl;
    }

    public void setIsExternalUrl(boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

    public PropertiesBean getProperties() {
        return properties;
    }

    public void setProperties(PropertiesBean properties) {
        this.properties = properties;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class PropertiesBean {

        /**
         * contentType : album
         * isPaid : false
         * key : ranking:album:subscribed:30:0
         * rankingListId : 21
         */

        private String contentType;
        private boolean isPaid;
        private String key;
        private int rankingListId;

        public static PropertiesBean objectFromData(String str) {

            return new Gson().fromJson(str, PropertiesBean.class);
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getRankingListId() {
            return rankingListId;
        }

        public void setRankingListId(int rankingListId) {
            this.rankingListId = rankingListId;
        }
    }
}
