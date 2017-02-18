package com.bing.lan.fm.ui.hot.bean;
import com.bing.lan.fm.bean.ResultBean;

//
// import com.bing.lan.fm.bean.ResultBean;
// import com.google.gson.Gson;
// import com.google.gson.annotations.SerializedName;
//
// import java.util.List;
//
// /**
//  * @author 蓝兵
//  * @time 2017/2/15  23:07
//  */
public class HotColumnsResult extends ResultBean {

}
//
//     private MemberBean member;
//     private DiscoveryColumnsBean discoveryColumns;
//     private GuessBean guess;
//     private CityColumnBean cityColumn;
//     private HotRecommendsBean hotRecommends;
//     private PaidAreaBean paidArea;
//
//     public static HotColumnsResult objectFromData(String str) {
//
//         return new Gson().fromJson(str, HotColumnsResult.class);
//     }
//
//     public MemberBean getMember() {
//         return member;
//     }
//
//     public void setMember(MemberBean member) {
//         this.member = member;
//     }
//
//     public DiscoveryColumnsBean getDiscoveryColumns() {
//         return discoveryColumns;
//     }
//
//     public void setDiscoveryColumns(DiscoveryColumnsBean discoveryColumns) {
//         this.discoveryColumns = discoveryColumns;
//     }
//
//     public GuessBean getGuess() {
//         return guess;
//     }
//
//     public void setGuess(GuessBean guess) {
//         this.guess = guess;
//     }
//
//     public CityColumnBean getCityColumn() {
//         return cityColumn;
//     }
//
//     public void setCityColumn(CityColumnBean cityColumn) {
//         this.cityColumn = cityColumn;
//     }
//
//     public HotRecommendsBean getHotRecommends() {
//         return hotRecommends;
//     }
//
//     public void setHotRecommends(HotRecommendsBean hotRecommends) {
//         this.hotRecommends = hotRecommends;
//     }
//
//     public PaidAreaBean getPaidArea() {
//         return paidArea;
//     }
//
//     public void setPaidArea(PaidAreaBean paidArea) {
//         this.paidArea = paidArea;
//     }
//
//     public static class MemberBean {
//
//         /**
//          * ret : 0
//          * title : 付费会员
//          * hasMore : true
//          * list : [{"uid":29668435,"title":"专属语你","subTitle":"陪你走过春夏秋冬，温暖你的每一个梦。 ","nickname":"NJ语瞳","ownerTitle":["喜马签约主播","百思官方主播","开心一刻主播","语你相伴创始"],"bannerUrl":"http://fdfs.xmcdn.com/group20/M07/0A/70/wKgJLFd9ip6Q5YApAAO6_tJi7MQ099_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":16},{"uid":59687486,"title":"科学队长，你耳边的科学家","subTitle":"教授博导给小朋友讲科学","nickname":"科学队长","ownerTitle":["科学家天团"],"bannerUrl":"http://fdfs.xmcdn.com/group23/M0B/13/4A/wKgJNFhmISrRdfYhAADJ5S7kc_k814_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":15},{"uid":9989998,"title":"小姿私密答","subTitle":"识破男人心，女人心，小姿做你的专属守护！","nickname":"小姿","ownerTitle":["二级心理咨询师","我知你心创始人","喜马情感女神","湖南广电主持人","网络红人"],"bannerUrl":"http://fdfs.xmcdn.com/group20/M09/E5/EB/wKgJJ1fO6sbjNgT-AAE04GeNhPI824_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":13}]
//          */
//
//         @SerializedName("ret")
//         private int retX;
//         private String title;
//         private boolean hasMore;
//         private List<ListBean> list;
//
//         public static MemberBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, MemberBean.class);
//         }
//
//         public int getRetX() {
//             return retX;
//         }
//
//         public void setRetX(int retX) {
//             this.retX = retX;
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public boolean isHasMore() {
//             return hasMore;
//         }
//
//         public void setHasMore(boolean hasMore) {
//             this.hasMore = hasMore;
//         }
//
//         public List<ListBean> getList() {
//             return list;
//         }
//
//         public void setList(List<ListBean> list) {
//             this.list = list;
//         }
//
//         public static class ListBean {
//
//             /**
//              * uid : 29668435
//              * title : 专属语你
//              * subTitle : 陪你走过春夏秋冬，温暖你的每一个梦。
//              * nickname : NJ语瞳
//              * ownerTitle : ["喜马签约主播","百思官方主播","开心一刻主播","语你相伴创始"]
//              * bannerUrl : http://fdfs.xmcdn.com/group20/M07/0A/70/wKgJLFd9ip6Q5YApAAO6_tJi7MQ099_mobile_x_large.jpg
//              * isAuthorizedMember : false
//              * memberProductId : 16
//              */
//
//             private int uid;
//             private String title;
//             private String subTitle;
//             private String nickname;
//             private String bannerUrl;
//             private boolean isAuthorizedMember;
//             private int memberProductId;
//             private List<String> ownerTitle;
//
//             public static ListBean objectFromData(String str) {
//
//                 return new Gson().fromJson(str, ListBean.class);
//             }
//
//             public int getUid() {
//                 return uid;
//             }
//
//             public void setUid(int uid) {
//                 this.uid = uid;
//             }
//
//             public String getTitle() {
//                 return title;
//             }
//
//             public void setTitle(String title) {
//                 this.title = title;
//             }
//
//             public String getSubTitle() {
//                 return subTitle;
//             }
//
//             public void setSubTitle(String subTitle) {
//                 this.subTitle = subTitle;
//             }
//
//             public String getNickname() {
//                 return nickname;
//             }
//
//             public void setNickname(String nickname) {
//                 this.nickname = nickname;
//             }
//
//             public String getBannerUrl() {
//                 return bannerUrl;
//             }
//
//             public void setBannerUrl(String bannerUrl) {
//                 this.bannerUrl = bannerUrl;
//             }
//
//             public boolean isIsAuthorizedMember() {
//                 return isAuthorizedMember;
//             }
//
//             public void setIsAuthorizedMember(boolean isAuthorizedMember) {
//                 this.isAuthorizedMember = isAuthorizedMember;
//             }
//
//             public int getMemberProductId() {
//                 return memberProductId;
//             }
//
//             public void setMemberProductId(int memberProductId) {
//                 this.memberProductId = memberProductId;
//             }
//
//             public List<String> getOwnerTitle() {
//                 return ownerTitle;
//             }
//
//             public void setOwnerTitle(List<String> ownerTitle) {
//                 this.ownerTitle = ownerTitle;
//             }
//         }
//     }
//
//     public static class DiscoveryColumnsBean {
//
//         /**
//          * ret : 0
//          * title : 发现新奇
//          */
//
//         @SerializedName("ret")
//         private int retX;
//         private String title;
//         private List<ListBeanX> list;
//
//         public static DiscoveryColumnsBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, DiscoveryColumnsBean.class);
//         }
//
//         public int getRetX() {
//             return retX;
//         }
//
//         public void setRetX(int retX) {
//             this.retX = retX;
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public List<ListBeanX> getList() {
//             return list;
//         }
//
//         public void setList(List<ListBeanX> list) {
//             this.list = list;
//         }
//
//         public static class ListBeanX {
//
//             /**
//              * id : 225
//              * title : 经典必听
//              * subtitle :
//              * bubbleText :
//              * coverPath : http://fdfs.xmcdn.com/group24/M02/61/80/wKgJNViX12XyYb9iAABXgLz8WRA459.jpg
//              * contentType : ranking_list
//              * url :
//              * sharePic :
//              * enableShare : false
//              * isExternalUrl : false
//              * contentUpdatedAt : 0
//              * properties : {"contentType":"album","rankingListId":21,"isPaid":false,"key":"ranking:album:subscribed:30:0"}
//              */
//
//             private int id;
//             private String title;
//             private String subtitle;
//             private String bubbleText;
//             private String coverPath;
//             private String contentType;
//             private String url;
//             private String sharePic;
//             private boolean enableShare;
//             private boolean isExternalUrl;
//             private int contentUpdatedAt;
//             private PropertiesBean properties;
//
//             public static ListBeanX objectFromData(String str) {
//
//                 return new Gson().fromJson(str, ListBeanX.class);
//             }
//
//             public int getId() {
//                 return id;
//             }
//
//             public void setId(int id) {
//                 this.id = id;
//             }
//
//             public String getTitle() {
//                 return title;
//             }
//
//             public void setTitle(String title) {
//                 this.title = title;
//             }
//
//             public String getSubtitle() {
//                 return subtitle;
//             }
//
//             public void setSubtitle(String subtitle) {
//                 this.subtitle = subtitle;
//             }
//
//             public String getBubbleText() {
//                 return bubbleText;
//             }
//
//             public void setBubbleText(String bubbleText) {
//                 this.bubbleText = bubbleText;
//             }
//
//             public String getCoverPath() {
//                 return coverPath;
//             }
//
//             public void setCoverPath(String coverPath) {
//                 this.coverPath = coverPath;
//             }
//
//             public String getContentType() {
//                 return contentType;
//             }
//
//             public void setContentType(String contentType) {
//                 this.contentType = contentType;
//             }
//
//             public String getUrl() {
//                 return url;
//             }
//
//             public void setUrl(String url) {
//                 this.url = url;
//             }
//
//             public String getSharePic() {
//                 return sharePic;
//             }
//
//             public void setSharePic(String sharePic) {
//                 this.sharePic = sharePic;
//             }
//
//             public boolean isEnableShare() {
//                 return enableShare;
//             }
//
//             public void setEnableShare(boolean enableShare) {
//                 this.enableShare = enableShare;
//             }
//
//             public boolean isIsExternalUrl() {
//                 return isExternalUrl;
//             }
//
//             public void setIsExternalUrl(boolean isExternalUrl) {
//                 this.isExternalUrl = isExternalUrl;
//             }
//
//             public int getContentUpdatedAt() {
//                 return contentUpdatedAt;
//             }
//
//             public void setContentUpdatedAt(int contentUpdatedAt) {
//                 this.contentUpdatedAt = contentUpdatedAt;
//             }
//
//             public PropertiesBean getProperties() {
//                 return properties;
//             }
//
//             public void setProperties(PropertiesBean properties) {
//                 this.properties = properties;
//             }
//
//             public static class PropertiesBean {
//
//                 /**
//                  * contentType : album
//                  * rankingListId : 21
//                  * isPaid : false
//                  * key : ranking:album:subscribed:30:0
//                  */
//
//                 private String contentType;
//                 private int rankingListId;
//                 private boolean isPaid;
//                 private String key;
//
//                 public static PropertiesBean objectFromData(String str) {
//
//                     return new Gson().fromJson(str, PropertiesBean.class);
//                 }
//
//                 public String getContentType() {
//                     return contentType;
//                 }
//
//                 public void setContentType(String contentType) {
//                     this.contentType = contentType;
//                 }
//
//                 public int getRankingListId() {
//                     return rankingListId;
//                 }
//
//                 public void setRankingListId(int rankingListId) {
//                     this.rankingListId = rankingListId;
//                 }
//
//                 public boolean isIsPaid() {
//                     return isPaid;
//                 }
//
//                 public void setIsPaid(boolean isPaid) {
//                     this.isPaid = isPaid;
//                 }
//
//                 public String getKey() {
//                     return key;
//                 }
//
//                 public void setKey(String key) {
//                     this.key = key;
//                 }
//             }
//         }
//     }
//
//     public static class GuessBean {
//
//         private boolean hasMore;
//         private int loopCount;
//         private String title;
//         private List<ListItemBean> list;
//
//         public static GuessBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, GuessBean.class);
//         }
//
//         public boolean isHasMore() {
//             return hasMore;
//         }
//
//         public void setHasMore(boolean hasMore) {
//             this.hasMore = hasMore;
//         }
//
//         public int getLoopCount() {
//             return loopCount;
//         }
//
//         public void setLoopCount(int loopCount) {
//             this.loopCount = loopCount;
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public List<ListItemBean1> getList() {
//             return list;
//         }
//
//         public void setList(List<ListItemBean1> list) {
//             this.list = list;
//         }
//
//     }
//
//     public static class CityColumnBean {
//
//         /**
//          * hasMore : true
//          * title : 听广州
//          * count : 564
//          * list : [{"id":3895053,"albumId":3895053,"uid":45009420,"intro":"不要有\u201c机\u201d饭局","nickname":"大雄带你睇广东","albumCoverUrl290":"http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg","coverSmall":"http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_small.jpg","coverMiddle":"http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg","coverLarge":"http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_large.jpg","title":"大雄带你睇广东","tags":"广东,岭南,文化","tracks":147,"playsCounts":77850,"isFinished":0,"serialState":0,"trackId":30557116,"trackTitle":"不要有\u201c机\u201d饭局","provider":"man","isPaid":false,"commentsCount":0,"priceTypeId":0},{"id":4011068,"albumId":4011068,"uid":46106366,"intro":"粤读OS第99期：广东话的哄人（凼人）怎么说？","nickname":"雪梨广东话","albumCoverUrl290":"http://fdfs.xmcdn.com/group12/M05/99/74/wKgDW1dp-oyT5CLHAABz5zNgnv0758_mobile_meduim.jpg","coverSmall":"http://fdfs.xmcdn.com/group12/M05/99/74/wKgDW1dp-oyT5CLHAABz5zNgnv0758_mobile_small.jpg","coverMiddle":"http://fdfs.xmcdn.com/group12/M05/99/74/wKgDW1dp-oyT5CLHAABz5zNgnv0758_mobile_meduim.jpg","coverLarge":"http://fdfs.xmcdn.com/group12/M05/99/74/wKgDW1dp-oyT5CLHAABz5zNgnv0758_mobile_large.jpg","title":"粤读OS--日常广东话教学","tags":"","tracks":98,"playsCounts":554417,"isFinished":0,"serialState":0,"trackId":28833572,"trackTitle":"粤读OS第99期：广东话的哄人（凼人）怎么说？","provider":"man","isPaid":false,"commentsCount":0,"priceTypeId":0},{"id":5784585,"albumId":5784585,"uid":61269566,"intro":"2月15日UC早头条语音播报","nickname":"UC头条","albumCoverUrl290":"http://fdfs.xmcdn.com/group24/M05/7A/D0/wKgJMFh3hyLjbPq1AAMgIp09z54154_mobile_meduim.png","coverSmall":"http://fdfs.xmcdn.com/group24/M05/7A/D0/wKgJMFh3hyLjbPq1AAMgIp09z54154_mobile_small.png","coverMiddle":"http://fdfs.xmcdn.com/group24/M05/7A/D0/wKgJMFh3hyLjbPq1AAMgIp09z54154_mobile_meduim.png","coverLarge":"http://fdfs.xmcdn.com/group24/M05/7A/D0/wKgJMFh3hyLjbPq1AAMgIp09z54154_mobile_large.png","title":"早头条","tags":"","tracks":82,"playsCounts":64898,"isFinished":0,"serialState":0,"trackId":30543900,"trackTitle":"2月15日UC早头条语音播报","provider":"search","isPaid":false,"commentsCount":0,"priceTypeId":0}]
//          * contentType : album
//          * code : 43_440000_4401
//          */
//
//         private boolean hasMore;
//         private String title;
//         private int count;
//         private String contentType;
//         private String code;
//         private List<ListBeanXXX> list;
//
//         public static CityColumnBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, CityColumnBean.class);
//         }
//
//         public boolean isHasMore() {
//             return hasMore;
//         }
//
//         public void setHasMore(boolean hasMore) {
//             this.hasMore = hasMore;
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public int getCount() {
//             return count;
//         }
//
//         public void setCount(int count) {
//             this.count = count;
//         }
//
//         public String getContentType() {
//             return contentType;
//         }
//
//         public void setContentType(String contentType) {
//             this.contentType = contentType;
//         }
//
//         public String getCode() {
//             return code;
//         }
//
//         public void setCode(String code) {
//             this.code = code;
//         }
//
//         public List<ListBeanXXX> getList() {
//             return list;
//         }
//
//         public void setList(List<ListBeanXXX> list) {
//             this.list = list;
//         }
//
//         public static class ListBeanXXX {
//
//             /**
//              * id : 3895053
//              * albumId : 3895053
//              * uid : 45009420
//              * intro : 不要有“机”饭局
//              * nickname : 大雄带你睇广东
//              * albumCoverUrl290 : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg
//              * coverSmall : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_small.jpg
//              * coverMiddle : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg
//              * coverLarge : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_large.jpg
//              * title : 大雄带你睇广东
//              * tags : 广东,岭南,文化
//              * tracks : 147
//              * playsCounts : 77850
//              * isFinished : 0
//              * serialState : 0
//              * trackId : 30557116
//              * trackTitle : 不要有“机”饭局
//              * provider : man
//              * isPaid : false
//              * commentsCount : 0
//              * priceTypeId : 0
//              */
//
//             private int id;
//             private int albumId;
//             private int uid;
//             private String intro;
//             private String nickname;
//             private String albumCoverUrl290;
//             private String coverSmall;
//             private String coverMiddle;
//             private String coverLarge;
//             private String title;
//             private String tags;
//             private int tracks;
//             private int playsCounts;
//             private int isFinished;
//             private int serialState;
//             private int trackId;
//             private String trackTitle;
//             private String provider;
//             private boolean isPaid;
//             private int commentsCount;
//             private int priceTypeId;
//
//             public static ListBeanXXX objectFromData(String str) {
//
//                 return new Gson().fromJson(str, ListBeanXXX.class);
//             }
//
//             public int getId() {
//                 return id;
//             }
//
//             public void setId(int id) {
//                 this.id = id;
//             }
//
//             public int getAlbumId() {
//                 return albumId;
//             }
//
//             public void setAlbumId(int albumId) {
//                 this.albumId = albumId;
//             }
//
//             public int getUid() {
//                 return uid;
//             }
//
//             public void setUid(int uid) {
//                 this.uid = uid;
//             }
//
//             public String getIntro() {
//                 return intro;
//             }
//
//             public void setIntro(String intro) {
//                 this.intro = intro;
//             }
//
//             public String getNickname() {
//                 return nickname;
//             }
//
//             public void setNickname(String nickname) {
//                 this.nickname = nickname;
//             }
//
//             public String getAlbumCoverUrl290() {
//                 return albumCoverUrl290;
//             }
//
//             public void setAlbumCoverUrl290(String albumCoverUrl290) {
//                 this.albumCoverUrl290 = albumCoverUrl290;
//             }
//
//             public String getCoverSmall() {
//                 return coverSmall;
//             }
//
//             public void setCoverSmall(String coverSmall) {
//                 this.coverSmall = coverSmall;
//             }
//
//             public String getCoverMiddle() {
//                 return coverMiddle;
//             }
//
//             public void setCoverMiddle(String coverMiddle) {
//                 this.coverMiddle = coverMiddle;
//             }
//
//             public String getCoverLarge() {
//                 return coverLarge;
//             }
//
//             public void setCoverLarge(String coverLarge) {
//                 this.coverLarge = coverLarge;
//             }
//
//             public String getTitle() {
//                 return title;
//             }
//
//             public void setTitle(String title) {
//                 this.title = title;
//             }
//
//             public String getTags() {
//                 return tags;
//             }
//
//             public void setTags(String tags) {
//                 this.tags = tags;
//             }
//
//             public int getTracks() {
//                 return tracks;
//             }
//
//             public void setTracks(int tracks) {
//                 this.tracks = tracks;
//             }
//
//             public int getPlaysCounts() {
//                 return playsCounts;
//             }
//
//             public void setPlaysCounts(int playsCounts) {
//                 this.playsCounts = playsCounts;
//             }
//
//             public int getIsFinished() {
//                 return isFinished;
//             }
//
//             public void setIsFinished(int isFinished) {
//                 this.isFinished = isFinished;
//             }
//
//             public int getSerialState() {
//                 return serialState;
//             }
//
//             public void setSerialState(int serialState) {
//                 this.serialState = serialState;
//             }
//
//             public int getTrackId() {
//                 return trackId;
//             }
//
//             public void setTrackId(int trackId) {
//                 this.trackId = trackId;
//             }
//
//             public String getTrackTitle() {
//                 return trackTitle;
//             }
//
//             public void setTrackTitle(String trackTitle) {
//                 this.trackTitle = trackTitle;
//             }
//
//             public String getProvider() {
//                 return provider;
//             }
//
//             public void setProvider(String provider) {
//                 this.provider = provider;
//             }
//
//             public boolean isIsPaid() {
//                 return isPaid;
//             }
//
//             public void setIsPaid(boolean isPaid) {
//                 this.isPaid = isPaid;
//             }
//
//             public int getCommentsCount() {
//                 return commentsCount;
//             }
//
//             public void setCommentsCount(int commentsCount) {
//                 this.commentsCount = commentsCount;
//             }
//
//             public int getPriceTypeId() {
//                 return priceTypeId;
//             }
//
//             public void setPriceTypeId(int priceTypeId) {
//                 this.priceTypeId = priceTypeId;
//             }
//         }
//     }
//
//     public static class HotRecommendsBean {
//
//         @SerializedName("ret")
//         private int retX;
//         private String title;
//         private List<ListBeanXXXXX> list;
//
//         public static HotRecommendsBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, HotRecommendsBean.class);
//         }
//
//         public int getRetX() {
//             return retX;
//         }
//
//         public void setRetX(int retX) {
//             this.retX = retX;
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public List<ListBeanXXXXX> getList() {
//             return list;
//         }
//
//         public void setList(List<ListBeanXXXXX> list) {
//             this.list = list;
//         }
//
//         public static class ListBeanXXXXX {
//
//             /**
//              * title : 听头条
//              * contentType : album
//              * isFinished : false
//              * categoryId : 1
//              */
//
//             private String title;
//             private String contentType;
//             private boolean isFinished;
//             private int categoryId;
//             private int categoryType;
//             private int count;
//             private boolean hasMore;
//             private boolean filterSupported;
//             private boolean isPaid;
//             private List<ListBeanXXXX> list;
//
//             public static ListBeanXXXXX objectFromData(String str) {
//
//                 return new Gson().fromJson(str, ListBeanXXXXX.class);
//             }
//
//             public String getTitle() {
//                 return title;
//             }
//
//             public void setTitle(String title) {
//                 this.title = title;
//             }
//
//             public String getContentType() {
//                 return contentType;
//             }
//
//             public void setContentType(String contentType) {
//                 this.contentType = contentType;
//             }
//
//             public boolean isIsFinished() {
//                 return isFinished;
//             }
//
//             public void setIsFinished(boolean isFinished) {
//                 this.isFinished = isFinished;
//             }
//
//             public int getCategoryId() {
//                 return categoryId;
//             }
//
//             public void setCategoryId(int categoryId) {
//                 this.categoryId = categoryId;
//             }
//
//             public int getCategoryType() {
//                 return categoryType;
//             }
//
//             public void setCategoryType(int categoryType) {
//                 this.categoryType = categoryType;
//             }
//
//             public int getCount() {
//                 return count;
//             }
//
//             public void setCount(int count) {
//                 this.count = count;
//             }
//
//             public boolean isHasMore() {
//                 return hasMore;
//             }
//
//             public void setHasMore(boolean hasMore) {
//                 this.hasMore = hasMore;
//             }
//
//             public boolean isFilterSupported() {
//                 return filterSupported;
//             }
//
//             public void setFilterSupported(boolean filterSupported) {
//                 this.filterSupported = filterSupported;
//             }
//
//             public boolean isIsPaid() {
//                 return isPaid;
//             }
//
//             public void setIsPaid(boolean isPaid) {
//                 this.isPaid = isPaid;
//             }
//
//             public List<ListBeanXXXX> getList() {
//                 return list;
//             }
//
//             public void setList(List<ListBeanXXXX> list) {
//                 this.list = list;
//             }
//
//             public static class ListBeanXXXX {
//
//                 /**
//                  * id : 6162556
//                  * albumId : 6162556
//                  * uid : 64599177
//                  * intro : 当狂放的赛马遇到绅士的英国…
//                  * nickname : 英国报姐
//                  * albumCoverUrl290 : http://fdfs.xmcdn.com/group21/M05/CC/01/wKgJKFhUCQ3RJ7J_AAD8Xa-NoZ0113_mobile_meduim.jpg
//                  * coverSmall : http://fdfs.xmcdn.com/group21/M05/CC/01/wKgJKFhUCQ3RJ7J_AAD8Xa-NoZ0113_mobile_small.jpg
//                  * coverMiddle : http://fdfs.xmcdn.com/group21/M05/CC/01/wKgJKFhUCQ3RJ7J_AAD8Xa-NoZ0113_mobile_meduim.jpg
//                  * coverLarge : http://fdfs.xmcdn.com/group21/M05/CC/01/wKgJKFhUCQ3RJ7J_AAD8Xa-NoZ0113_mobile_large.jpg
//                  * title : 报姐说腐国
//                  * tags :
//                  * tracks : 6
//                  * playsCounts : 78624
//                  * isFinished : 0
//                  * serialState : 0
//                  * trackId : 30506188
//                  * trackTitle : 当狂放的赛马遇到绅士的英国…
//                  * provider : man
//                  * isPaid : false
//                  * commentsCount : 0
//                  * priceTypeId : 0
//                  */
//
//                 private int id;
//                 private int albumId;
//                 private int uid;
//                 private String intro;
//                 private String nickname;
//                 private String albumCoverUrl290;
//                 private String coverSmall;
//                 private String coverMiddle;
//                 private String coverLarge;
//                 private String title;
//                 private String tags;
//                 private int tracks;
//                 private int playsCounts;
//                 private int isFinished;
//                 private int serialState;
//                 private int trackId;
//                 private String trackTitle;
//                 private String provider;
//                 private boolean isPaid;
//                 private int commentsCount;
//                 private int priceTypeId;
//
//                 public static ListBeanXXXX objectFromData(String str) {
//
//                     return new Gson().fromJson(str, ListBeanXXXX.class);
//                 }
//
//                 public int getId() {
//                     return id;
//                 }
//
//                 public void setId(int id) {
//                     this.id = id;
//                 }
//
//                 public int getAlbumId() {
//                     return albumId;
//                 }
//
//                 public void setAlbumId(int albumId) {
//                     this.albumId = albumId;
//                 }
//
//                 public int getUid() {
//                     return uid;
//                 }
//
//                 public void setUid(int uid) {
//                     this.uid = uid;
//                 }
//
//                 public String getIntro() {
//                     return intro;
//                 }
//
//                 public void setIntro(String intro) {
//                     this.intro = intro;
//                 }
//
//                 public String getNickname() {
//                     return nickname;
//                 }
//
//                 public void setNickname(String nickname) {
//                     this.nickname = nickname;
//                 }
//
//                 public String getAlbumCoverUrl290() {
//                     return albumCoverUrl290;
//                 }
//
//                 public void setAlbumCoverUrl290(String albumCoverUrl290) {
//                     this.albumCoverUrl290 = albumCoverUrl290;
//                 }
//
//                 public String getCoverSmall() {
//                     return coverSmall;
//                 }
//
//                 public void setCoverSmall(String coverSmall) {
//                     this.coverSmall = coverSmall;
//                 }
//
//                 public String getCoverMiddle() {
//                     return coverMiddle;
//                 }
//
//                 public void setCoverMiddle(String coverMiddle) {
//                     this.coverMiddle = coverMiddle;
//                 }
//
//                 public String getCoverLarge() {
//                     return coverLarge;
//                 }
//
//                 public void setCoverLarge(String coverLarge) {
//                     this.coverLarge = coverLarge;
//                 }
//
//                 public String getTitle() {
//                     return title;
//                 }
//
//                 public void setTitle(String title) {
//                     this.title = title;
//                 }
//
//                 public String getTags() {
//                     return tags;
//                 }
//
//                 public void setTags(String tags) {
//                     this.tags = tags;
//                 }
//
//                 public int getTracks() {
//                     return tracks;
//                 }
//
//                 public void setTracks(int tracks) {
//                     this.tracks = tracks;
//                 }
//
//                 public int getPlaysCounts() {
//                     return playsCounts;
//                 }
//
//                 public void setPlaysCounts(int playsCounts) {
//                     this.playsCounts = playsCounts;
//                 }
//
//                 public int getIsFinished() {
//                     return isFinished;
//                 }
//
//                 public void setIsFinished(int isFinished) {
//                     this.isFinished = isFinished;
//                 }
//
//                 public int getSerialState() {
//                     return serialState;
//                 }
//
//                 public void setSerialState(int serialState) {
//                     this.serialState = serialState;
//                 }
//
//                 public int getTrackId() {
//                     return trackId;
//                 }
//
//                 public void setTrackId(int trackId) {
//                     this.trackId = trackId;
//                 }
//
//                 public String getTrackTitle() {
//                     return trackTitle;
//                 }
//
//                 public void setTrackTitle(String trackTitle) {
//                     this.trackTitle = trackTitle;
//                 }
//
//                 public String getProvider() {
//                     return provider;
//                 }
//
//                 public void setProvider(String provider) {
//                     this.provider = provider;
//                 }
//
//                 public boolean isIsPaid() {
//                     return isPaid;
//                 }
//
//                 public void setIsPaid(boolean isPaid) {
//                     this.isPaid = isPaid;
//                 }
//
//                 public int getCommentsCount() {
//                     return commentsCount;
//                 }
//
//                 public void setCommentsCount(int commentsCount) {
//                     this.commentsCount = commentsCount;
//                 }
//
//                 public int getPriceTypeId() {
//                     return priceTypeId;
//                 }
//
//                 public void setPriceTypeId(int priceTypeId) {
//                     this.priceTypeId = priceTypeId;
//                 }
//             }
//         }
//     }
//
//     public static class PaidAreaBean {
//
//         /**
//          * title : 精品
//          * contentType : album
//          * isFinished : false
//          * categoryId : 33
//          * categoryType : 1
//          * count : 1000
//          * hasMore : true
//          * <p>
//          * isPaid : true
//          */
//
//         private String title;
//         private String contentType;
//         private boolean isFinished;
//         private int categoryId;
//         private int categoryType;
//         private int count;
//         private boolean hasMore;
//         private boolean filterSupported;
//         private boolean isPaid;
//         private List<ListBeanXXXXXX> list;
//
//         public static PaidAreaBean objectFromData(String str) {
//
//             return new Gson().fromJson(str, PaidAreaBean.class);
//         }
//
//         public String getTitle() {
//             return title;
//         }
//
//         public void setTitle(String title) {
//             this.title = title;
//         }
//
//         public String getContentType() {
//             return contentType;
//         }
//
//         public void setContentType(String contentType) {
//             this.contentType = contentType;
//         }
//
//         public boolean isIsFinished() {
//             return isFinished;
//         }
//
//         public void setIsFinished(boolean isFinished) {
//             this.isFinished = isFinished;
//         }
//
//         public int getCategoryId() {
//             return categoryId;
//         }
//
//         public void setCategoryId(int categoryId) {
//             this.categoryId = categoryId;
//         }
//
//         public int getCategoryType() {
//             return categoryType;
//         }
//
//         public void setCategoryType(int categoryType) {
//             this.categoryType = categoryType;
//         }
//
//         public int getCount() {
//             return count;
//         }
//
//         public void setCount(int count) {
//             this.count = count;
//         }
//
//         public boolean isHasMore() {
//             return hasMore;
//         }
//
//         public void setHasMore(boolean hasMore) {
//             this.hasMore = hasMore;
//         }
//
//         public boolean isFilterSupported() {
//             return filterSupported;
//         }
//
//         public void setFilterSupported(boolean filterSupported) {
//             this.filterSupported = filterSupported;
//         }
//
//         public boolean isIsPaid() {
//             return isPaid;
//         }
//
//         public void setIsPaid(boolean isPaid) {
//             this.isPaid = isPaid;
//         }
//
//         public List<ListBeanXXXXXX> getList() {
//             return list;
//         }
//
//         public void setList(List<ListBeanXXXXXX> list) {
//             this.list = list;
//         }
//
//         public static class ListBeanXXXXXX {
//
//             /**
//              * id : 5157453
//              * albumId : 5157453
//              * uid : 48708232
//              * intro : 马云及阿里巴巴合伙人首次回顾阿里巴巴创业历程，锤炼创业板斧
//              * nickname : 湖畔大学
//              * albumCoverUrl290 : http://fdfs.xmcdn.com/group25/M04/E2/91/wKgJMVhaQO2ijp5BAADNyqAw3rY454_mobile_meduim.jpg
//              * coverSmall : http://fdfs.xmcdn.com/group25/M04/E2/91/wKgJMVhaQO2ijp5BAADNyqAw3rY454_mobile_small.jpg
//              * coverMiddle : http://fdfs.xmcdn.com/group25/M04/E2/91/wKgJMVhaQO2ijp5BAADNyqAw3rY454_mobile_meduim.jpg
//              * coverLarge : http://fdfs.xmcdn.com/group25/M04/E2/91/wKgJMVhaQO2ijp5BAADNyqAw3rY454_mobile_large.jpg
//              * title : 湖畔大学三板斧：马云及阿里合伙人首次公开创业心法
//              * tags :
//              * tracks : 33
//              * playsCounts : 4846801
//              * isFinished : 0
//              * serialState : 0
//              * trackId : 30286046
//              * trackTitle : 【元宵免费听】郭广昌 | 复星高管要知道的那些事儿
//              * recSrc : PB
//              * recTrack : mr.payBest.1001
//              * provider : recsys
//              * isPaid : true
//              * commentsCount : 442
//              * priceTypeId : 2
//              * price : 99.0
//              * discountedPrice : 99.0
//              * score : 4.4
//              * displayPrice : 99.00喜点
//              * displayDiscountedPrice : 99.00喜点
//              * priceTypeEnum : 2
//              */
//
//             private int id;
//             private int albumId;
//             private int uid;
//             private String intro;
//             private String nickname;
//             private String albumCoverUrl290;
//             private String coverSmall;
//             private String coverMiddle;
//             private String coverLarge;
//             private String title;
//             private String tags;
//             private int tracks;
//             private int playsCounts;
//             private int isFinished;
//             private int serialState;
//             private int trackId;
//             private String trackTitle;
//             private String recSrc;
//             private String recTrack;
//             private String provider;
//             private boolean isPaid;
//             private int commentsCount;
//             private int priceTypeId;
//             private double price;
//             private double discountedPrice;
//             private double score;
//             private String displayPrice;
//             private String displayDiscountedPrice;
//             private int priceTypeEnum;
//
//             public static ListBeanXXXXXX objectFromData(String str) {
//
//                 return new Gson().fromJson(str, ListBeanXXXXXX.class);
//             }
//
//             public int getId() {
//                 return id;
//             }
//
//             public void setId(int id) {
//                 this.id = id;
//             }
//
//             public int getAlbumId() {
//                 return albumId;
//             }
//
//             public void setAlbumId(int albumId) {
//                 this.albumId = albumId;
//             }
//
//             public int getUid() {
//                 return uid;
//             }
//
//             public void setUid(int uid) {
//                 this.uid = uid;
//             }
//
//             public String getIntro() {
//                 return intro;
//             }
//
//             public void setIntro(String intro) {
//                 this.intro = intro;
//             }
//
//             public String getNickname() {
//                 return nickname;
//             }
//
//             public void setNickname(String nickname) {
//                 this.nickname = nickname;
//             }
//
//             public String getAlbumCoverUrl290() {
//                 return albumCoverUrl290;
//             }
//
//             public void setAlbumCoverUrl290(String albumCoverUrl290) {
//                 this.albumCoverUrl290 = albumCoverUrl290;
//             }
//
//             public String getCoverSmall() {
//                 return coverSmall;
//             }
//
//             public void setCoverSmall(String coverSmall) {
//                 this.coverSmall = coverSmall;
//             }
//
//             public String getCoverMiddle() {
//                 return coverMiddle;
//             }
//
//             public void setCoverMiddle(String coverMiddle) {
//                 this.coverMiddle = coverMiddle;
//             }
//
//             public String getCoverLarge() {
//                 return coverLarge;
//             }
//
//             public void setCoverLarge(String coverLarge) {
//                 this.coverLarge = coverLarge;
//             }
//
//             public String getTitle() {
//                 return title;
//             }
//
//             public void setTitle(String title) {
//                 this.title = title;
//             }
//
//             public String getTags() {
//                 return tags;
//             }
//
//             public void setTags(String tags) {
//                 this.tags = tags;
//             }
//
//             public int getTracks() {
//                 return tracks;
//             }
//
//             public void setTracks(int tracks) {
//                 this.tracks = tracks;
//             }
//
//             public int getPlaysCounts() {
//                 return playsCounts;
//             }
//
//             public void setPlaysCounts(int playsCounts) {
//                 this.playsCounts = playsCounts;
//             }
//
//             public int getIsFinished() {
//                 return isFinished;
//             }
//
//             public void setIsFinished(int isFinished) {
//                 this.isFinished = isFinished;
//             }
//
//             public int getSerialState() {
//                 return serialState;
//             }
//
//             public void setSerialState(int serialState) {
//                 this.serialState = serialState;
//             }
//
//             public int getTrackId() {
//                 return trackId;
//             }
//
//             public void setTrackId(int trackId) {
//                 this.trackId = trackId;
//             }
//
//             public String getTrackTitle() {
//                 return trackTitle;
//             }
//
//             public void setTrackTitle(String trackTitle) {
//                 this.trackTitle = trackTitle;
//             }
//
//             public String getRecSrc() {
//                 return recSrc;
//             }
//
//             public void setRecSrc(String recSrc) {
//                 this.recSrc = recSrc;
//             }
//
//             public String getRecTrack() {
//                 return recTrack;
//             }
//
//             public void setRecTrack(String recTrack) {
//                 this.recTrack = recTrack;
//             }
//
//             public String getProvider() {
//                 return provider;
//             }
//
//             public void setProvider(String provider) {
//                 this.provider = provider;
//             }
//
//             public boolean isIsPaid() {
//                 return isPaid;
//             }
//
//             public void setIsPaid(boolean isPaid) {
//                 this.isPaid = isPaid;
//             }
//
//             public int getCommentsCount() {
//                 return commentsCount;
//             }
//
//             public void setCommentsCount(int commentsCount) {
//                 this.commentsCount = commentsCount;
//             }
//
//             public int getPriceTypeId() {
//                 return priceTypeId;
//             }
//
//             public void setPriceTypeId(int priceTypeId) {
//                 this.priceTypeId = priceTypeId;
//             }
//
//             public double getPrice() {
//                 return price;
//             }
//
//             public void setPrice(double price) {
//                 this.price = price;
//             }
//
//             public double getDiscountedPrice() {
//                 return discountedPrice;
//             }
//
//             public void setDiscountedPrice(double discountedPrice) {
//                 this.discountedPrice = discountedPrice;
//             }
//
//             public double getScore() {
//                 return score;
//             }
//
//             public void setScore(double score) {
//                 this.score = score;
//             }
//
//             public String getDisplayPrice() {
//                 return displayPrice;
//             }
//
//             public void setDisplayPrice(String displayPrice) {
//                 this.displayPrice = displayPrice;
//             }
//
//             public String getDisplayDiscountedPrice() {
//                 return displayDiscountedPrice;
//             }
//
//             public void setDisplayDiscountedPrice(String displayDiscountedPrice) {
//                 this.displayDiscountedPrice = displayDiscountedPrice;
//             }
//
//             public int getPriceTypeEnum() {
//                 return priceTypeEnum;
//             }
//
//             public void setPriceTypeEnum(int priceTypeEnum) {
//                 this.priceTypeEnum = priceTypeEnum;
//             }
//         }
//     }
// }
