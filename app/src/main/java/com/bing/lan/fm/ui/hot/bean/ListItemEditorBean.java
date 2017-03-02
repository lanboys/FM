package com.bing.lan.fm.ui.hot.bean;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @time 2017/2/17  15:12
 */

public class ListItemEditorBean implements Serializable{
    // 小编推荐
    /**
     * id : 2881558
     * albumId : 2881558
     * uid : 32160470
     * intro : 【0215收盘战绩】调整有利于未来行情发展！(王斌)
     * nickname : 王斌3490755
     * albumCoverUrl290 : http://fdfs.xmcdn.com/group7/M01/84/D3/wKgDWldPoPPhRhU4AADOZqEzMqk903_mobile_meduim.jpg
     * coverSmall : http://fdfs.xmcdn.com/group7/M01/84/D3/wKgDWldPoPPhRhU4AADOZqEzMqk903_mobile_small.jpg
     * coverMiddle : http://fdfs.xmcdn.com/group7/M01/84/D3/wKgDWldPoPPhRhU4AADOZqEzMqk903_mobile_meduim.jpg
     * coverLarge : http://fdfs.xmcdn.com/group7/M01/84/D3/wKgDWldPoPPhRhU4AADOZqEzMqk903_mobile_large.jpg
     * title : 今日股市
     * tags :
     * tracks : 1064
     * playsCounts : 11101671
     * isFinished : 0
     * serialState : 0
     * trackId : 30566881
     * trackTitle : 【0215收盘战绩】调整有利于未来行情发展！(王斌)
     * recSrc : UCR
     * recTrack : mr.discoverF.2
     * provider : recsys
     * isPaid : false
     * commentsCount : 0
     * priceTypeId : 0
     * price : 68.0
     * discountedPrice : 36.0
     * score : 5.0
     * displayPrice : 68.00喜点
     * displayDiscountedPrice : 36.00喜点
     * priceTypeEnum : 2
     */

    private int id;
    private int albumId;
    private int uid;
    private String intro;
    private String nickname;
    private String albumCoverUrl290;
    private String coverSmall;
    private String coverMiddle;
    private String coverLarge;
    private String title;
    private String tags;
    private int tracks;
    private int playsCounts;
    private int isFinished;
    private int serialState;
    private int trackId;
    private String trackTitle;
    private String recSrc;
    private String recTrack;
    private String provider;
    private boolean isPaid;
    private int commentsCount;
    private int priceTypeId;
    private double price;
    private double discountedPrice;
    private double score;
    private String displayPrice;
    private String displayDiscountedPrice;
    private int priceTypeEnum;

    @Override
    public String toString() {
        return "ListItemEditorBean{" +
                "albumId=" + albumId +
                ", intro='" + intro + '\'' +
                ", coverLarge='" + coverLarge + '\'' +
                ", trackId=" + trackId +
                ", trackTitle='" + trackTitle + '\'' +
                ", tags='" + tags + '\'' +
                ", albumCoverUrl290='" + albumCoverUrl290 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAlbumCoverUrl290() {
        return albumCoverUrl290;
    }

    public void setAlbumCoverUrl290(String albumCoverUrl290) {
        this.albumCoverUrl290 = albumCoverUrl290;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getPlaysCounts() {
        return playsCounts;
    }

    public void setPlaysCounts(int playsCounts) {
        this.playsCounts = playsCounts;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public int getSerialState() {
        return serialState;
    }

    public void setSerialState(int serialState) {
        this.serialState = serialState;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getRecSrc() {
        return recSrc;
    }

    public void setRecSrc(String recSrc) {
        this.recSrc = recSrc;
    }

    public String getRecTrack() {
        return recTrack;
    }

    public void setRecTrack(String recTrack) {
        this.recTrack = recTrack;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public String getDisplayDiscountedPrice() {
        return displayDiscountedPrice;
    }

    public void setDisplayDiscountedPrice(String displayDiscountedPrice) {
        this.displayDiscountedPrice = displayDiscountedPrice;
    }

    public int getPriceTypeEnum() {
        return priceTypeEnum;
    }

    public void setPriceTypeEnum(int priceTypeEnum) {
        this.priceTypeEnum = priceTypeEnum;
    }
}

