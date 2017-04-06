package com.bing.lan.fm.ui.recommend.bean;


public class DataBean {
    /**
     * album : {"albumId":248886,"category_id":4,"coverLarge":"http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg","coverMiddle":"http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg","cover_path":"http://fdfs.xmcdn.com/group15/M04/04/29/wKgDZVbFih_RIPSfAAC51SmGivk452.jpg","info":"都市的喧嚣，热闹的街道，坐下来喝杯茶，静下心来，享受小武带给你的轻松与惬意。都市聊聊吧，撩拨你的心弦！ 第一期节目：马文婚变，谁是谁非；京东一哥，恋上女神；武大校花赏樱花，清秀淡雅文艺范儿。 ","isDeleted":false,"isFinished":0,"tags":"热门评述,十里铺人民广播,热点话题,娱乐八卦,明星","title":"名人风范","tracks":118,"uid":3121164}
     * hate_reasons : {"ba":"不感兴趣","bc":"内容质量差","dl":"不喜欢本期节目","his":"听过了","wt":"不喜欢主播"}
     * recSrc : level
     * recTrack : sq.coldBootResult.feed
     * rec_reason : 兴趣
     * rec_type : track
     * track : {"count_comment":24,"count_like":59,"count_share":1,"coverPath":"http://fdfs.xmcdn.com/group24/M05/BC/95/wKgJNVizdyWAJHM_AAMf6VtU4nI972.jpg","cover_large_path":"http://fdfs.xmcdn.com/group24/M05/BC/95/wKgJNVizdyWAJHM_AAMf6VtU4nI972_mobile_large.jpg","cover_middle_path":"http://fdfs.xmcdn.com/group24/M05/BC/95/wKgJNVizdyWAJHM_AAMf6VtU4nI972_web_large.jpg","cover_small_path":"http://fdfs.xmcdn.com/group24/M05/BC/95/wKgJNVizdyWAJHM_AAMf6VtU4nI972_mobile_small.jpg","download_path":"http://download.xmcdn.com/group24/M05/BC/95/wKgJNVizdyCxU23ZADWu6ShN0Xg708.aac","duration":879,"isDeleted":false,"is_paid":false,"playCounts":21234,"play_path_32":"http://fdfs.xmcdn.com/group24/M05/BC/95/wKgJNVizdyexNnCrADWtD9oUPh8673.mp3","play_path_64":"http://fdfs.xmcdn.com/group24/M05/BC/41/wKgJMFizdyzyADriAGtZ3y4bbJE123.mp3","play_path_aac_v164":"http://audio.xmcdn.com/group24/M05/BC/95/wKgJNVizdyLjfSL0AGyl6CAb8C8152.m4a","play_path_aac_v224":"http://audio.xmcdn.com/group24/M05/BC/40/wKgJMFizdxyhrAi-ACmMPnDL7zc976.m4a","tags":"娱乐,真人秀,脱口秀","title":"黄磊：似水年华，陪伴是最长情的告白【名人风范】☆周一☆","trackId":31415725,"uptrackAt":1488156708000}
     */

    private AlbumBean album;
    private HateReasonsBean hate_reasons;
    private String recSrc;
    private String recTrack;
    private String rec_reason;
    private String rec_type;
    private TrackBean track;

    public AlbumBean getAlbum() {
        return album;
    }

    public void setAlbum(AlbumBean album) {
        this.album = album;
    }

    public HateReasonsBean getHate_reasons() {
        return hate_reasons;
    }

    public void setHate_reasons(HateReasonsBean hate_reasons) {
        this.hate_reasons = hate_reasons;
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

    public String getRec_reason() {
        return rec_reason;
    }

    public void setRec_reason(String rec_reason) {
        this.rec_reason = rec_reason;
    }

    public String getRec_type() {
        return rec_type;
    }

    public void setRec_type(String rec_type) {
        this.rec_type = rec_type;
    }

    public TrackBean getTrack() {
        return track;
    }

    public void setTrack(TrackBean track) {
        this.track = track;
    }

}
