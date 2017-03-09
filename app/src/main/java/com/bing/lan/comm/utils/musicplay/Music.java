package com.bing.lan.comm.utils.musicplay;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author 蓝兵
 * @time 2017/3/1  22:54
 */
public class Music extends RealmObject /*implements Serializable*/ {
    /**
     * 注意：如果你创建Model并运行过，然后修改了Model。那么就
     * 需要升级数据库，否则会抛异常。升级方式后面会提到。
     */

    /**
     * //@Required  ——表示该字段非空
     *
     * 使用@Required可用于用于强行要求其属性不能为空，只能用于
     * Boolean, Byte, Short, Integer, Long, Float, Double,
     * String, byte[] 和 Date。在其它类型属性上使用
     * //@Required  修饰会导致编译失败。
     */
    //@PrimaryKey ——表示该字段是主键

    // @Required
    public String url;
    @PrimaryKey
    public long albumId;// 专辑id
    public long lastPlayPosition;// 上次播放时间
    public long lastPlayDateMillis;
    public long duration;
    public String nickname; //专辑名称

    public String coverSmall; //图片
    public String coverMiddle;
    public String coverLarge;
    public String title; // 专辑??
    public long trackId; //


    // @PrimaryKey
    // public long id;

    public Music( ) {


    }
    public Music(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Music{" +
                "albumId=" + albumId +
                ", url='" + url + '\'' +
                ", lastPlayPosition=" + lastPlayPosition +
                ", duration=" + duration +
                // ", id=" + id +
                '}';
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getLastPlayDateMillis() {
        return lastPlayDateMillis;
    }

    public void setLastPlayDateMillis(long lastPlayDateMillis) {
        this.lastPlayDateMillis = lastPlayDateMillis;
    }

    public long getLastPlayPosition() {
        return lastPlayPosition;
    }

    public void setLastPlayPosition(long lastPlayPosition) {
        this.lastPlayPosition = lastPlayPosition;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
