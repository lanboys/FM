package com.bing.lan.fm.ui.music.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/27  0:35
 */
public class PlayResult {

    /**
     * trackInfo : {"trackId":31353083,"uid":1615410,"playUrl64":"http://fdfs.xmcdn.com/group25/M01/B9/ED/wKgJNliyV8vQBHsfAAv5jJdI4To126.mp3","playUrl32":"http://fdfs.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8vhBxtCAAX85bjOjKU647.mp3","downloadUrl":"http://download.xmcdn.com/group25/M0A/B9/F2/wKgJMViyXEOAENQmAAX_MWpQS-w408.aac","playPathAacv164":"http://audio.xmcdn.com/group25/M01/B9/EE/wKgJNliyV8ugp_QeAAwk-EA7fz4853.m4a","playPathAacv224":"http://audio.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8rgTyfwAASnKx3q-DI155.m4a","downloadAacUrl":"http://download.xmcdn.com/group25/M01/B9/DE/wKgJMViyV8rgTyfwAASnKx3q-DI155.m4a","title":"来来来，闲来无事唱歌给你们听。小虎队《爱》翻唱：朱大宇","duration":98,"isPaid":false,"isFree":false,"isAuthorized":false,"priceTypeId":0,"sampleDuration":0,"priceTypeEnum":0,"priceTypes":[],"categoryId":4,"processState":2,"createdAt":1488082977000,"coverSmall":"http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_web_meduim.jpg","coverMiddle":"http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_web_large.jpg","coverLarge":"http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_mobile_large.jpg","isPublic":true,"images":["http://fdfs.xmcdn.com/group10/M0A/C0/8B/wKgDaVZi8xDjQ2gSAADcgqAhuoc093_mobile_large.jpg"],"status":1,"downloadSize":393009,"downloadAacSize":304939,"categoryName":"娱乐","bulletSwitchType":1,"playPathHq":"","shortRichIntro":"新闻娱乐脱口秀，有原创有集纳，总之怎么好玩怎么来，尝试新风格！"}
     * albumInfo : {"albumId":270535,"categoryId":4,"title":"妙宇连朱","coverOrigin":"http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502.jpg","coverSmall":"http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_small.jpg","coverMiddle":"http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_meduim.jpg","coverLarge":"http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_large.jpg","coverWebLarge":"http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_web_large.jpg","createdAt":1407157048000,"updatedAt":1488084554000,"uid":1615410,"intro":"新闻娱乐脱口秀，有原创有集纳，总之怎么好玩怎么来，尝试新风格！","tags":"搞笑,新闻娱乐脱口秀,笑话段子,脱口秀","hasNew":false,"isFavorite":false,"isPaid":false,"status":1,"serializeStatus":0,"isAuthorized":false}
     * recAlbumsPanelTitle : 相关推荐
     * associationAlbumsInfo : [{"albumId":336435,"title":"上班脱口秀","coverSmall":"http://fdfs.xmcdn.com/group10/M06/6A/3A/wKgDaVc5hEahA8uNAAZo6BT6o2o969_mobile_small.png","coverMiddle":"http://fdfs.xmcdn.com/group10/M06/6A/3A/wKgDaVc5hEahA8uNAAZo6BT6o2o969_mobile_small.png","updatedAt":1487910974000,"uid":6534662,"recSrc":"cf3","recTrack":"ra.ItemRec.5","intro":"趣侃热点故事,加料平淡日常","isPaid":false},{"albumId":6653307,"title":"读懂职场BOSS心：2017跟对老板吃肉","coverSmall":"http://fdfs.xmcdn.com/group22/M09/68/09/wKgJLliZY1igPjUXAAGxk8YcAw0999_mobile_small.jpg","coverMiddle":"http://fdfs.xmcdn.com/group22/M09/68/09/wKgJLliZY1igPjUXAAGxk8YcAw0999_mobile_small.jpg","updatedAt":1488045849000,"uid":66436930,"recSrc":"cf3","recTrack":"ra.ItemRec.5","intro":"在复杂的职场环境中，你会遇到各种类型的大小老板。 好老板是人生奋斗的加速器，身在职场的你，是不是常常在感叹\u201c老板的心很难懂\u201d？ 有时候读懂老板的心比做好工作更重要！ 跟对老板吃肉，跟错老板吃土！ 你想知道你的老板是哪种类型吗？ 你想知道你的老板可靠吗？ 你想知道怎么做才能读懂老板的心思？ 老板心思千千万，到底怎样才能出任CEO，走上人生新巅峰呢？ 不论你是初出校门的职场新手，还是老道的资深上班族，只要你有这些困惑，都可以从节目中找到答案\u2014\u2014 听雷明教你读懂老板，玩转职场：和珅为何巨贪而不被杀、比马云王健林更神的首富牟其中、如何理解和应对老板的管理方法、创业老板该怎么跟、老板没文化该怎么做\u2026\u2026 掌握老板小心思，轻松职场博弈不是梦！","isPaid":true,"priceTypeEnum":2,"priceTypeId":2,"price":99,"discountedPrice":36,"displayPrice":"99.00喜点","displayDiscountedPrice":"36.00喜点"},{"albumId":2924,"title":"小强来了","coverSmall":"http://fdfs.xmcdn.com/group1/M00/0C/88/wKgDrVEnChTSSYSkABFVY39EtH4018_mobile_small.jpg","coverMiddle":"http://fdfs.xmcdn.com/group1/M00/0C/88/wKgDrVEnChTSSYSkABFVY39EtH4018_mobile_small.jpg","updatedAt":1488063510000,"uid":1017918,"recSrc":"cf3","recTrack":"ra.ItemRec.5","intro":"网罗天下笑话,发动快乐引擎","isPaid":false}]
     * userInfo : {"uid":1615410,"nickname":"朱宇","isVerified":true,"smallLogo":"http://fdfs.xmcdn.com/group4/M09/37/C3/wKgDs1QmSqmy3NmtAAMWPcm1R20991_mobile_small.jpg","followers":71897,"tracks":431,"albums":5,"ptitle":"新闻娱乐脱口秀","personDescribe":"新闻娱乐脱口秀","isOpenAskAndAnswer":true}
     * commentInfo : {"list":[{"id":40373768,"track_id":31353083,"uid":1615410,"nickname":"朱宇","smallHeader":"http://fdfs.xmcdn.com/group4/M09/37/C3/wKgDs1QmSqmy3NmtAAMWPcm1R20991_mobile_small.jpg","content":"哈哈 传到 喜马拉雅 音损有点厉害","createdAt":1488083126000,"updatedAt":1488083126000,"parentId":40373768,"likes":39},{"id":40375139,"track_id":31353083,"uid":5080394,"nickname":"开灰机的nono","smallHeader":"http://fdfs.xmcdn.com/group23/M02/8C/5B/wKgJL1hCBSXBnuM3AAFbluoyhrw533_mobile_small.jpg","content":"好听！！","createdAt":1488084804000,"updatedAt":1488084804000,"parentId":40375139,"likes":11},{"id":40375580,"track_id":31353083,"uid":37654308,"nickname":"汉桑城_me","smallHeader":"http://fdfs.xmcdn.com/group13/M04/A9/A0/wKgDXlZUM9PDNJV7AAAZxfj0kQg289_mobile_small.jpg","content":"内容不够，唱歌来凑！","createdAt":1488085552000,"updatedAt":1488085552000,"parentId":40375580,"likes":10},{"id":40380663,"track_id":31353083,"uid":24068715,"nickname":"绣气泡泡","smallHeader":"http://fdfs.xmcdn.com/group24/M02/73/41/wKgJNVidOmXQAIJ2AADPmC1rX68267_mobile_small.jpg","content":"厉害了我的朱大宇[太开心]","createdAt":1488093552000,"updatedAt":1488093552000,"parentId":40380663,"likes":7},{"id":40402314,"track_id":31353083,"uid":31407012,"nickname":"我等燕归来_je","smallHeader":"http://fdfs.xmcdn.com/group15/M06/4D/CA/wKgDaFcaPzaSzyeWAAGoipFBRKw990_mobile_small.jpg","content":"周末还录歌与我们分享，谢谢","createdAt":1488117615000,"updatedAt":1488117615000,"parentId":40402314,"likes":2}],"pageId":1,"pageSize":5,"maxPageId":20,"totalCount":97}
     * countInfo : {"albumPlays":{"2924":23359961,"270535":15015457,"336435":13112342,"6653307":17816},"albumTracks":{"2924":559,"270535":417,"336435":340,"6653307":7},"albumSubscribes":{"270535":53150}}
     * trackRewardInfo : {"uid":1615410,"isOpen":true,"numOfRewards":16,"rewords":[{"id":1476479,"uid":28452096,"nickname":"0橙子北北0","amount":"2","paymentTime":1488120996000,"sn":1476479,"smallLogo":"http://fdfs.xmcdn.com/group14/M08/77/4F/wKgDY1YDpnOziQ5lAAD-8I_KI24072_mobile_small.jpg"},{"id":1476256,"uid":40472994,"nickname":"行走着_gv","amount":"2","paymentTime":1488118836000,"sn":1476256,"smallLogo":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJric0bbMUQpVxyQCAnMG3PLfnXtDha9Mo4ICicgmeTIMiar9UxHmYL3Mvmrz0icZSV24gYVx66ZM30Kw/0"},{"id":1475956,"uid":8412421,"nickname":"文公子的娘","amount":"2","paymentTime":1488116087000,"sn":1475956,"smallLogo":"http://fdfs.xmcdn.com/group17/M0B/72/38/wKgJKVejXi-wf1bxAAEyTM2byNo593_mobile_small.jpg"},{"id":1475648,"uid":37921302,"nickname":"微笑面对人生_oc","amount":"5","paymentTime":1488112327000,"sn":1475648,"smallLogo":"http://qzapp.qlogo.cn/qzapp/100261563/967F3DDC3F2A9455AF16B6D14E037350/100"},{"id":1475515,"uid":66586830,"nickname":"魔惑_po","amount":"2","paymentTime":1488110269000,"sn":1475515,"smallLogo":"http://wx.qlogo.cn/mmopen/tQ5Cfwb3MPzjyxfdbgtDK0FQia3muLlrG2NN3sXY1ibjqFdLoURSRRplnAAJMUtl9cMBhwuTXq6OoueD35JeZbjYibicr7Msh6ym/0"},{"id":1475249,"uid":31650153,"nickname":"七月流火lh","amount":"5","paymentTime":1488106104000,"sn":1475249,"smallLogo":"http://fdfs.xmcdn.com/group14/M03/E0/29/wKgDZFaNplaz4gsaAACahVPI7O4083_mobile_small.jpg"}]}
     * otherInfo : {"isFavorite":false,"isLike":true,"isFollowed":false}
     */

    private TrackInfoBean trackInfo;
    private AlbumInfoBean albumInfo;
    private String recAlbumsPanelTitle;
    private UserInfoBean userInfo;
    private CommentInfoBean commentInfo;
    private CountInfoBean countInfo;
    private TrackRewardInfoBean trackRewardInfo;
    private OtherInfoBean otherInfo;
    private List<AssociationAlbumsInfoBean> associationAlbumsInfo;

    @Override
    public String toString() {
        return "PlayResult{" +
                "albumInfo=" + albumInfo +
                ", trackInfo=" + trackInfo +
                ", recAlbumsPanelTitle='" + recAlbumsPanelTitle + '\'' +
                ", userInfo=" + userInfo +
                ", commentInfo=" + commentInfo +
                ", countInfo=" + countInfo +
                ", trackRewardInfo=" + trackRewardInfo +
                ", otherInfo=" + otherInfo +
                ", associationAlbumsInfo=" + associationAlbumsInfo +
                '}';
    }

    public static PlayResult objectFromData(String str) {

        return new Gson().fromJson(str, PlayResult.class);
    }

    public TrackInfoBean getTrackInfo() {
        return trackInfo;
    }

    public void setTrackInfo(TrackInfoBean trackInfo) {
        this.trackInfo = trackInfo;
    }

    public AlbumInfoBean getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(AlbumInfoBean albumInfo) {
        this.albumInfo = albumInfo;
    }

    public String getRecAlbumsPanelTitle() {
        return recAlbumsPanelTitle;
    }

    public void setRecAlbumsPanelTitle(String recAlbumsPanelTitle) {
        this.recAlbumsPanelTitle = recAlbumsPanelTitle;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public CommentInfoBean getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(CommentInfoBean commentInfo) {
        this.commentInfo = commentInfo;
    }

    public CountInfoBean getCountInfo() {
        return countInfo;
    }

    public void setCountInfo(CountInfoBean countInfo) {
        this.countInfo = countInfo;
    }

    public TrackRewardInfoBean getTrackRewardInfo() {
        return trackRewardInfo;
    }

    public void setTrackRewardInfo(TrackRewardInfoBean trackRewardInfo) {
        this.trackRewardInfo = trackRewardInfo;
    }

    public OtherInfoBean getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfoBean otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<AssociationAlbumsInfoBean> getAssociationAlbumsInfo() {
        return associationAlbumsInfo;
    }

    public void setAssociationAlbumsInfo(List<AssociationAlbumsInfoBean> associationAlbumsInfo) {
        this.associationAlbumsInfo = associationAlbumsInfo;
    }


    public static class AlbumInfoBean {

        /**
         * albumId : 270535
         * categoryId : 4
         * title : 妙宇连朱
         * coverOrigin : http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502.jpg
         * coverSmall : http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_small.jpg
         * coverMiddle : http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_meduim.jpg
         * coverLarge : http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_mobile_large.jpg
         * coverWebLarge : http://fdfs.xmcdn.com/group11/M07/CD/67/wKgDbVZi8wLwDwRiAADcgqAhuoc502_web_large.jpg
         * createdAt : 1407157048000
         * updatedAt : 1488084554000
         * uid : 1615410
         * intro : 新闻娱乐脱口秀，有原创有集纳，总之怎么好玩怎么来，尝试新风格！
         * tags : 搞笑,新闻娱乐脱口秀,笑话段子,脱口秀
         * hasNew : false
         * isFavorite : false
         * isPaid : false
         * status : 1
         * serializeStatus : 0
         * isAuthorized : false
         */

        private int albumId;
        private int categoryId;
        private String title;
        private String coverOrigin;
        private String coverSmall;
        private String coverMiddle;
        private String coverLarge;
        private String coverWebLarge;
        private long createdAt;
        private long updatedAt;
        private int uid;
        private String intro;
        private String tags;
        private boolean hasNew;
        private boolean isFavorite;
        private boolean isPaid;
        private int status;
        private int serializeStatus;
        private boolean isAuthorized;

        public static AlbumInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, AlbumInfoBean.class);
        }

        public int getAlbumId() {
            return albumId;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverOrigin() {
            return coverOrigin;
        }

        public void setCoverOrigin(String coverOrigin) {
            this.coverOrigin = coverOrigin;
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

        public String getCoverWebLarge() {
            return coverWebLarge;
        }

        public void setCoverWebLarge(String coverWebLarge) {
            this.coverWebLarge = coverWebLarge;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
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

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public boolean isHasNew() {
            return hasNew;
        }

        public void setHasNew(boolean hasNew) {
            this.hasNew = hasNew;
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSerializeStatus() {
            return serializeStatus;
        }

        public void setSerializeStatus(int serializeStatus) {
            this.serializeStatus = serializeStatus;
        }

        public boolean isIsAuthorized() {
            return isAuthorized;
        }

        public void setIsAuthorized(boolean isAuthorized) {
            this.isAuthorized = isAuthorized;
        }
    }

    public static class UserInfoBean {

        /**
         * uid : 1615410
         * nickname : 朱宇
         * isVerified : true
         * smallLogo : http://fdfs.xmcdn.com/group4/M09/37/C3/wKgDs1QmSqmy3NmtAAMWPcm1R20991_mobile_small.jpg
         * followers : 71897
         * tracks : 431
         * albums : 5
         * ptitle : 新闻娱乐脱口秀
         * personDescribe : 新闻娱乐脱口秀
         * isOpenAskAndAnswer : true
         */

        private int uid;
        private String nickname;
        private boolean isVerified;
        private String smallLogo;
        private int followers;
        private int tracks;
        private int albums;
        private String ptitle;
        private String personDescribe;
        private boolean isOpenAskAndAnswer;

        public static UserInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, UserInfoBean.class);
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public boolean isIsVerified() {
            return isVerified;
        }

        public void setIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
        }

        public String getSmallLogo() {
            return smallLogo;
        }

        public void setSmallLogo(String smallLogo) {
            this.smallLogo = smallLogo;
        }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public int getTracks() {
            return tracks;
        }

        public void setTracks(int tracks) {
            this.tracks = tracks;
        }

        public int getAlbums() {
            return albums;
        }

        public void setAlbums(int albums) {
            this.albums = albums;
        }

        public String getPtitle() {
            return ptitle;
        }

        public void setPtitle(String ptitle) {
            this.ptitle = ptitle;
        }

        public String getPersonDescribe() {
            return personDescribe;
        }

        public void setPersonDescribe(String personDescribe) {
            this.personDescribe = personDescribe;
        }

        public boolean isIsOpenAskAndAnswer() {
            return isOpenAskAndAnswer;
        }

        public void setIsOpenAskAndAnswer(boolean isOpenAskAndAnswer) {
            this.isOpenAskAndAnswer = isOpenAskAndAnswer;
        }
    }

    public static class CommentInfoBean {

        /**
         * list : [{"id":40373768,"track_id":31353083,"uid":1615410,"nickname":"朱宇","smallHeader":"http://fdfs.xmcdn.com/group4/M09/37/C3/wKgDs1QmSqmy3NmtAAMWPcm1R20991_mobile_small.jpg","content":"哈哈 传到 喜马拉雅 音损有点厉害","createdAt":1488083126000,"updatedAt":1488083126000,"parentId":40373768,"likes":39},{"id":40375139,"track_id":31353083,"uid":5080394,"nickname":"开灰机的nono","smallHeader":"http://fdfs.xmcdn.com/group23/M02/8C/5B/wKgJL1hCBSXBnuM3AAFbluoyhrw533_mobile_small.jpg","content":"好听！！","createdAt":1488084804000,"updatedAt":1488084804000,"parentId":40375139,"likes":11},{"id":40375580,"track_id":31353083,"uid":37654308,"nickname":"汉桑城_me","smallHeader":"http://fdfs.xmcdn.com/group13/M04/A9/A0/wKgDXlZUM9PDNJV7AAAZxfj0kQg289_mobile_small.jpg","content":"内容不够，唱歌来凑！","createdAt":1488085552000,"updatedAt":1488085552000,"parentId":40375580,"likes":10},{"id":40380663,"track_id":31353083,"uid":24068715,"nickname":"绣气泡泡","smallHeader":"http://fdfs.xmcdn.com/group24/M02/73/41/wKgJNVidOmXQAIJ2AADPmC1rX68267_mobile_small.jpg","content":"厉害了我的朱大宇[太开心]","createdAt":1488093552000,"updatedAt":1488093552000,"parentId":40380663,"likes":7},{"id":40402314,"track_id":31353083,"uid":31407012,"nickname":"我等燕归来_je","smallHeader":"http://fdfs.xmcdn.com/group15/M06/4D/CA/wKgDaFcaPzaSzyeWAAGoipFBRKw990_mobile_small.jpg","content":"周末还录歌与我们分享，谢谢","createdAt":1488117615000,"updatedAt":1488117615000,"parentId":40402314,"likes":2}]
         * pageId : 1
         * pageSize : 5
         * maxPageId : 20
         * totalCount : 97
         */

        private int pageId;
        private int pageSize;
        private int maxPageId;
        private int totalCount;
        private List<ListBean> list;

        public static CommentInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, CommentInfoBean.class);
        }

        public int getPageId() {
            return pageId;
        }

        public void setPageId(int pageId) {
            this.pageId = pageId;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getMaxPageId() {
            return maxPageId;
        }

        public void setMaxPageId(int maxPageId) {
            this.maxPageId = maxPageId;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            /**
             * id : 40373768
             * track_id : 31353083
             * uid : 1615410
             * nickname : 朱宇
             * smallHeader : http://fdfs.xmcdn.com/group4/M09/37/C3/wKgDs1QmSqmy3NmtAAMWPcm1R20991_mobile_small.jpg
             * content : 哈哈 传到 喜马拉雅 音损有点厉害
             * createdAt : 1488083126000
             * updatedAt : 1488083126000
             * parentId : 40373768
             * likes : 39
             */

            private int id;
            private int track_id;
            private int uid;
            private String nickname;
            private String smallHeader;
            private String content;
            private long createdAt;
            private long updatedAt;
            private int parentId;
            private int likes;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTrack_id() {
                return track_id;
            }

            public void setTrack_id(int track_id) {
                this.track_id = track_id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSmallHeader() {
                return smallHeader;
            }

            public void setSmallHeader(String smallHeader) {
                this.smallHeader = smallHeader;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(long createdAt) {
                this.createdAt = createdAt;
            }

            public long getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(long updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }
        }
    }

    public static class CountInfoBean {

        /**
         * albumPlays : {"2924":23359961,"270535":15015457,"336435":13112342,"6653307":17816}
         * albumTracks : {"2924":559,"270535":417,"336435":340,"6653307":7}
         * albumSubscribes : {"270535":53150}
         */

        private AlbumPlaysBean albumPlays;
        private AlbumTracksBean albumTracks;
        private AlbumSubscribesBean albumSubscribes;

        public static CountInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, CountInfoBean.class);
        }

        public AlbumPlaysBean getAlbumPlays() {
            return albumPlays;
        }

        public void setAlbumPlays(AlbumPlaysBean albumPlays) {
            this.albumPlays = albumPlays;
        }

        public AlbumTracksBean getAlbumTracks() {
            return albumTracks;
        }

        public void setAlbumTracks(AlbumTracksBean albumTracks) {
            this.albumTracks = albumTracks;
        }

        public AlbumSubscribesBean getAlbumSubscribes() {
            return albumSubscribes;
        }

        public void setAlbumSubscribes(AlbumSubscribesBean albumSubscribes) {
            this.albumSubscribes = albumSubscribes;
        }

        public static class AlbumPlaysBean {

            /**
             * 2924 : 23359961
             * 270535 : 15015457
             * 336435 : 13112342
             * 6653307 : 17816
             */

            @SerializedName("2924")
            private int value2924;
            @SerializedName("270535")
            private int value270535;
            @SerializedName("336435")
            private int value336435;
            @SerializedName("6653307")
            private int value6653307;

            public static AlbumPlaysBean objectFromData(String str) {

                return new Gson().fromJson(str, AlbumPlaysBean.class);
            }

            public int getValue2924() {
                return value2924;
            }

            public void setValue2924(int value2924) {
                this.value2924 = value2924;
            }

            public int getValue270535() {
                return value270535;
            }

            public void setValue270535(int value270535) {
                this.value270535 = value270535;
            }

            public int getValue336435() {
                return value336435;
            }

            public void setValue336435(int value336435) {
                this.value336435 = value336435;
            }

            public int getValue6653307() {
                return value6653307;
            }

            public void setValue6653307(int value6653307) {
                this.value6653307 = value6653307;
            }
        }

        public static class AlbumTracksBean {

            /**
             * 2924 : 559
             * 270535 : 417
             * 336435 : 340
             * 6653307 : 7
             */

            @SerializedName("2924")
            private int value2924;
            @SerializedName("270535")
            private int value270535;
            @SerializedName("336435")
            private int value336435;
            @SerializedName("6653307")
            private int value6653307;

            public static AlbumTracksBean objectFromData(String str) {

                return new Gson().fromJson(str, AlbumTracksBean.class);
            }

            public int getValue2924() {
                return value2924;
            }

            public void setValue2924(int value2924) {
                this.value2924 = value2924;
            }

            public int getValue270535() {
                return value270535;
            }

            public void setValue270535(int value270535) {
                this.value270535 = value270535;
            }

            public int getValue336435() {
                return value336435;
            }

            public void setValue336435(int value336435) {
                this.value336435 = value336435;
            }

            public int getValue6653307() {
                return value6653307;
            }

            public void setValue6653307(int value6653307) {
                this.value6653307 = value6653307;
            }
        }

        public static class AlbumSubscribesBean {

            /**
             * 270535 : 53150
             */

            @SerializedName("270535")
            private int value270535;

            public static AlbumSubscribesBean objectFromData(String str) {

                return new Gson().fromJson(str, AlbumSubscribesBean.class);
            }

            public int getValue270535() {
                return value270535;
            }

            public void setValue270535(int value270535) {
                this.value270535 = value270535;
            }
        }
    }

    public static class TrackRewardInfoBean {

        /**
         * uid : 1615410
         * isOpen : true
         * numOfRewards : 16
         * rewords : [{"id":1476479,"uid":28452096,"nickname":"0橙子北北0","amount":"2","paymentTime":1488120996000,"sn":1476479,"smallLogo":"http://fdfs.xmcdn.com/group14/M08/77/4F/wKgDY1YDpnOziQ5lAAD-8I_KI24072_mobile_small.jpg"},{"id":1476256,"uid":40472994,"nickname":"行走着_gv","amount":"2","paymentTime":1488118836000,"sn":1476256,"smallLogo":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJric0bbMUQpVxyQCAnMG3PLfnXtDha9Mo4ICicgmeTIMiar9UxHmYL3Mvmrz0icZSV24gYVx66ZM30Kw/0"},{"id":1475956,"uid":8412421,"nickname":"文公子的娘","amount":"2","paymentTime":1488116087000,"sn":1475956,"smallLogo":"http://fdfs.xmcdn.com/group17/M0B/72/38/wKgJKVejXi-wf1bxAAEyTM2byNo593_mobile_small.jpg"},{"id":1475648,"uid":37921302,"nickname":"微笑面对人生_oc","amount":"5","paymentTime":1488112327000,"sn":1475648,"smallLogo":"http://qzapp.qlogo.cn/qzapp/100261563/967F3DDC3F2A9455AF16B6D14E037350/100"},{"id":1475515,"uid":66586830,"nickname":"魔惑_po","amount":"2","paymentTime":1488110269000,"sn":1475515,"smallLogo":"http://wx.qlogo.cn/mmopen/tQ5Cfwb3MPzjyxfdbgtDK0FQia3muLlrG2NN3sXY1ibjqFdLoURSRRplnAAJMUtl9cMBhwuTXq6OoueD35JeZbjYibicr7Msh6ym/0"},{"id":1475249,"uid":31650153,"nickname":"七月流火lh","amount":"5","paymentTime":1488106104000,"sn":1475249,"smallLogo":"http://fdfs.xmcdn.com/group14/M03/E0/29/wKgDZFaNplaz4gsaAACahVPI7O4083_mobile_small.jpg"}]
         */

        private int uid;
        private boolean isOpen;
        private int numOfRewards;
        private List<RewordsBean> rewords;

        public static TrackRewardInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, TrackRewardInfoBean.class);
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public boolean isIsOpen() {
            return isOpen;
        }

        public void setIsOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }

        public int getNumOfRewards() {
            return numOfRewards;
        }

        public void setNumOfRewards(int numOfRewards) {
            this.numOfRewards = numOfRewards;
        }

        public List<RewordsBean> getRewords() {
            return rewords;
        }

        public void setRewords(List<RewordsBean> rewords) {
            this.rewords = rewords;
        }

        public static class RewordsBean {

            /**
             * id : 1476479
             * uid : 28452096
             * nickname : 0橙子北北0
             * amount : 2
             * paymentTime : 1488120996000
             * sn : 1476479
             * smallLogo : http://fdfs.xmcdn.com/group14/M08/77/4F/wKgDY1YDpnOziQ5lAAD-8I_KI24072_mobile_small.jpg
             */

            private int id;
            private int uid;
            private String nickname;
            private String amount;
            private long paymentTime;
            private int sn;
            private String smallLogo;

            public static RewordsBean objectFromData(String str) {

                return new Gson().fromJson(str, RewordsBean.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public long getPaymentTime() {
                return paymentTime;
            }

            public void setPaymentTime(long paymentTime) {
                this.paymentTime = paymentTime;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public String getSmallLogo() {
                return smallLogo;
            }

            public void setSmallLogo(String smallLogo) {
                this.smallLogo = smallLogo;
            }
        }
    }

    public static class OtherInfoBean {

        /**
         * isFavorite : false
         * isLike : true
         * isFollowed : false
         */

        private boolean isFavorite;
        private boolean isLike;
        private boolean isFollowed;

        public static OtherInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, OtherInfoBean.class);
        }

        public boolean isIsFavorite() {
            return isFavorite;
        }

        public void setIsFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public boolean isIsFollowed() {
            return isFollowed;
        }

        public void setIsFollowed(boolean isFollowed) {
            this.isFollowed = isFollowed;
        }
    }

    public static class AssociationAlbumsInfoBean {

        /**
         * albumId : 336435
         * title : 上班脱口秀
         * coverSmall : http://fdfs.xmcdn.com/group10/M06/6A/3A/wKgDaVc5hEahA8uNAAZo6BT6o2o969_mobile_small.png
         * coverMiddle : http://fdfs.xmcdn.com/group10/M06/6A/3A/wKgDaVc5hEahA8uNAAZo6BT6o2o969_mobile_small.png
         * updatedAt : 1487910974000
         * uid : 6534662
         * recSrc : cf3
         * recTrack : ra.ItemRec.5
         * intro : 趣侃热点故事,加料平淡日常
         * isPaid : false
         * priceTypeEnum : 2
         * priceTypeId : 2
         * price : 99
         * discountedPrice : 36
         * displayPrice : 99.00喜点
         * displayDiscountedPrice : 36.00喜点
         */

        private int albumId;
        private String title;
        private String coverSmall;
        private String coverMiddle;
        private long updatedAt;
        private int uid;
        private String recSrc;
        private String recTrack;
        private String intro;
        private boolean isPaid;
        private int priceTypeEnum;
        private int priceTypeId;
        private int price;
        private int discountedPrice;
        private String displayPrice;
        private String displayDiscountedPrice;

        public static AssociationAlbumsInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, AssociationAlbumsInfoBean.class);
        }

        public int getAlbumId() {
            return albumId;
        }

        public void setAlbumId(int albumId) {
            this.albumId = albumId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public long getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(long updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public int getPriceTypeEnum() {
            return priceTypeEnum;
        }

        public void setPriceTypeEnum(int priceTypeEnum) {
            this.priceTypeEnum = priceTypeEnum;
        }

        public int getPriceTypeId() {
            return priceTypeId;
        }

        public void setPriceTypeId(int priceTypeId) {
            this.priceTypeId = priceTypeId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getDiscountedPrice() {
            return discountedPrice;
        }

        public void setDiscountedPrice(int discountedPrice) {
            this.discountedPrice = discountedPrice;
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
    }
}
