package com.bing.lan.fm.ui.music.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/3/2  22:39
 */
public   class TrackInfoBean {

    /**
     * trackId : 31353083
     * uid : 1615410
     * playUrl64 : http://fdfs.xmcdn.com/group25/M01/B9/ED/wKgJNliyV8vQBHsfAAv5jJdI4To126.mp3
     * playUrl32 : http://fdfs.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8vhBxtCAAX85bjOjKU647.mp3
     * downloadUrl : http://download.xmcdn.com/group25/M0A/B9/F2/wKgJMViyXEOAENQmAAX_MWpQS-w408.aac
     * playPathAacv164 : http://audio.xmcdn.com/group25/M01/B9/EE/wKgJNliyV8ugp_QeAAwk-EA7fz4853.m4a
     * playPathAacv224 : http://audio.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8rgTyfwAASnKx3q-DI155.m4a
     * downloadAacUrl : http://download.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8rgTyfwAASnKx3q-DI155.m4a
     * title : 来来来，闲来无事唱歌给你们听。小虎队《爱》翻唱：朱大宇
     * duration : 98
     * isPaid : false
     * isFree : false
     * isAuthorized : false
     * priceTypeId : 0
     * sampleDuration : 0
     * priceTypeEnum : 0
     * priceTypes : []
     * categoryId : 4
     * processState : 2
     * createdAt : 1488082977000
     * coverSmall : http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_web_meduim.jpg
     * coverMiddle : http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_web_large.jpg
     * coverLarge : http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_mobile_large.jpg
     * isPublic : true
     * images : ["http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_mobile_large.jpg"]
     * status : 1
     * downloadSize : 393009
     * downloadAacSize : 304939
     * categoryName : 娱乐
     * bulletSwitchType : 1
     * playPathHq :
     * shortRichIntro : 新闻娱乐脱口秀，有原创有集纳，总之怎么好玩怎么来，尝试新风格！
     */

    private int trackId;
    private int uid;
    private String playUrl64;
    private String playUrl32;
    private String downloadUrl;
    private String playPathAacv164;
    private String playPathAacv224;
    private String downloadAacUrl;
    private String title;
    private int duration;
    private boolean isPaid;
    private boolean isFree;
    private boolean isAuthorized;
    private int priceTypeId;
    private int sampleDuration;
    private int priceTypeEnum;
    private int categoryId;
    private int processState;
    private long createdAt;
    private String coverSmall;
    private String coverMiddle;
    private String coverLarge;
    private boolean isPublic;
    private int status;
    private int downloadSize;
    private int downloadAacSize;
    private String categoryName;
    private int bulletSwitchType;
    private String playPathHq;
    private String shortRichIntro;
    private List<?> priceTypes;
    private List<String> images;

    @Override
    public String toString() {
        return "TrackInfoBean{" +
                "playUrl64='" + playUrl64 + '\'' +
                ", images=" + images +
                ", coverMiddle='" + coverMiddle + '\'' +
                ", coverLarge='" + coverLarge + '\'' +
                ", title='" + title + '\'' +
                ", trackId=" + trackId +
                ", uid=" + uid +
                ", status=" + status +
                ", playUrl32='" + playUrl32 + '\'' +
                ", playPathAacv224='" + playPathAacv224 + '\'' +
                '}';
    }

    public static TrackInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, TrackInfoBean.class);
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPlayUrl64() {
        return playUrl64;
    }

    public void setPlayUrl64(String playUrl64) {
        this.playUrl64 = playUrl64;
    }

    public String getPlayUrl32() {
        return playUrl32;
    }

    public void setPlayUrl32(String playUrl32) {
        this.playUrl32 = playUrl32;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getPlayPathAacv164() {
        return playPathAacv164;
    }

    public void setPlayPathAacv164(String playPathAacv164) {
        this.playPathAacv164 = playPathAacv164;
    }

    public String getPlayPathAacv224() {
        return playPathAacv224;
    }

    public void setPlayPathAacv224(String playPathAacv224) {
        this.playPathAacv224 = playPathAacv224;
    }

    public String getDownloadAacUrl() {
        return downloadAacUrl;
    }

    public void setDownloadAacUrl(String downloadAacUrl) {
        this.downloadAacUrl = downloadAacUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public boolean isIsAuthorized() {
        return isAuthorized;
    }

    public void setIsAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public int getSampleDuration() {
        return sampleDuration;
    }

    public void setSampleDuration(int sampleDuration) {
        this.sampleDuration = sampleDuration;
    }

    public int getPriceTypeEnum() {
        return priceTypeEnum;
    }

    public void setPriceTypeEnum(int priceTypeEnum) {
        this.priceTypeEnum = priceTypeEnum;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProcessState() {
        return processState;
    }

    public void setProcessState(int processState) {
        this.processState = processState;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMiddle() {
        return coverMiddle;
    }

    public void setCoverMiddle(String coverMiddle) {
        this.coverMiddle = coverMiddle;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public void setCoverLarge(String coverLarge) {
        this.coverLarge = coverLarge;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(int downloadSize) {
        this.downloadSize = downloadSize;
    }

    public int getDownloadAacSize() {
        return downloadAacSize;
    }

    public void setDownloadAacSize(int downloadAacSize) {
        this.downloadAacSize = downloadAacSize;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getBulletSwitchType() {
        return bulletSwitchType;
    }

    public void setBulletSwitchType(int bulletSwitchType) {
        this.bulletSwitchType = bulletSwitchType;
    }

    public String getPlayPathHq() {
        return playPathHq;
    }

    public void setPlayPathHq(String playPathHq) {
        this.playPathHq = playPathHq;
    }

    public String getShortRichIntro() {
        return shortRichIntro;
    }

    public void setShortRichIntro(String shortRichIntro) {
        this.shortRichIntro = shortRichIntro;
    }

    public List<?> getPriceTypes() {
        return priceTypes;
    }

    public void setPriceTypes(List<?> priceTypes) {
        this.priceTypes = priceTypes;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

