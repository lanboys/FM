package com.bing.lan.fm.ui.hot.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @time 2017/3/5  17:35
 */
public class ListItemGuessBean implements Serializable, IAlbum {

    /**
     * albumCoverUrl290 : http://fdfs.xmcdn.com/group16/M06/96/3A/wKgDaldlAxnBY7P6AABHJRP50Nk811_mobile_meduim.jpg
     * albumId : 4547118
     * commentsCount : 0
     * coverLarge : http://fdfs.xmcdn.com/group16/M06/96/3A/wKgDaldlAxnBY7P6AABHJRP50Nk811_mobile_large.jpg
     * coverMiddle : http://fdfs.xmcdn.com/group16/M06/96/3A/wKgDaldlAxnBY7P6AABHJRP50Nk811_mobile_meduim.jpg
     * coverSmall : http://fdfs.xmcdn.com/group16/M06/96/3A/wKgDaldlAxnBY7P6AABHJRP50Nk811_mobile_small.jpg
     * id : 4547118
     * intro : 玩转粤语口头禅001点睇
     * isFinished : 0
     * isPaid : false
     * nickname : 玩转粤语
     * playsCounts : 43250
     * priceTypeId : 0
     * provider : recsys
     * recSrc : URcf
     * recTrack : mr.discoverF.2
     * serialState : 0
     * tags :
     * title : 玩转粤语学习口头禅
     * trackId : 17239437
     * trackTitle : 对粤你想“搞掂”广东人吗？先学广东话吧。你想“学识”广东话吗？那就先讲口头禅...
     * tracks : 6
     * uid : 43692281
     * discountedPrice : 99.0
     * displayDiscountedPrice : 99.00喜点
     * displayPrice : 99.00喜点
     * price : 99.0
     * priceTypeEnum : 2
     * score : 5.0
     */

    private String albumCoverUrl290;
    private long albumId;
    private int commentsCount;
    private String coverLarge;
    private String coverMiddle;
    private String coverSmall;
    private int id;
    private String intro;
    private int isFinished;
    private boolean isPaid;
    private String nickname;
    private int playsCounts;
    private int priceTypeId;
    private String provider;
    private String recSrc;
    private String recTrack;
    private int serialState;
    private String tags;
    private String title;
    private int trackId;
    private String trackTitle;
    private int tracks;
    private int uid;
    private double discountedPrice;
    private String displayDiscountedPrice;
    private String displayPrice;
    private double price;
    private int priceTypeEnum;
    private double score;

    public static ListItemGuessBean objectFromData(String str) {

        return new Gson().fromJson(str, ListItemGuessBean.class);
    }

    public String getAlbumCoverUrl290() {
        return albumCoverUrl290;
    }

    public void setAlbumCoverUrl290(String albumCoverUrl290) {
        this.albumCoverUrl290 = albumCoverUrl290;
    }

    public long getAlbumId() { 
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
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

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPlaysCounts() {
        return playsCounts;
    }

    public void setPlaysCounts(int playsCounts) {
        this.playsCounts = playsCounts;
    }

    public int getPriceTypeId() {
        return priceTypeId;
    }

    public void setPriceTypeId(int priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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

    public int getSerialState() {
        return serialState;
    }

    public void setSerialState(int serialState) {
        this.serialState = serialState;
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

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getDisplayDiscountedPrice() {
        return displayDiscountedPrice;
    }

    public void setDisplayDiscountedPrice(String displayDiscountedPrice) {
        this.displayDiscountedPrice = displayDiscountedPrice;
    }

    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriceTypeEnum() {
        return priceTypeEnum;
    }

    public void setPriceTypeEnum(int priceTypeEnum) {
        this.priceTypeEnum = priceTypeEnum;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

