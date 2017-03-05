package com.bing.lan.fm.ui.hot.bean;

import com.bing.lan.fm.bean.ResultBean;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotResult1 extends ResultBean {

    private CityColumnBean cityColumn;
    private HotInfoBean<ListItemDiscoverBean> discoveryColumns;
    private HotInfoBean<ListItemGuessBean> guess;
    private HotRecommendsBean hotRecommends;
    private MemberBean member;
    private PaidAreaBean paidArea;

    @Override
    public String toString() {
        return "HotResult1{" +
                "cityColumn=" + cityColumn +
                ", discoveryColumns=" + discoveryColumns +
                ", guess=" + guess +
                ", hotRecommends=" + hotRecommends +
                ", member=" + member +
                ", paidArea=" + paidArea +
                '}';
    }

    public static HotResult1 objectFromData(String str) {

        return new Gson().fromJson(str, HotResult1.class);
    }

    public CityColumnBean getCityColumn() {
        return cityColumn;
    }

    public void setCityColumn(CityColumnBean cityColumn) {
        this.cityColumn = cityColumn;
    }

    public HotInfoBean<ListItemDiscoverBean> getDiscoveryColumns() {
        return discoveryColumns;
    }

    public void setDiscoveryColumns(HotInfoBean<ListItemDiscoverBean> discoveryColumns) {
        this.discoveryColumns = discoveryColumns;
    }

    public HotInfoBean<ListItemGuessBean> getGuess() {
        return guess;
    }

    public void setGuess(HotInfoBean<ListItemGuessBean> guess) {
        this.guess = guess;
    }

    public HotRecommendsBean getHotRecommends() {
        return hotRecommends;
    }

    public void setHotRecommends(HotRecommendsBean hotRecommends) {
        this.hotRecommends = hotRecommends;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public PaidAreaBean getPaidArea() {
        return paidArea;
    }

    public void setPaidArea(PaidAreaBean paidArea) {
        this.paidArea = paidArea;
    }

    public static class CityColumnBean {

        private String code;
        private String contentType;
        private int count;
        private boolean hasMore;
        private String title;
        private List<ListBean> list;

        public static CityColumnBean objectFromData(String str) {

            return new Gson().fromJson(str, CityColumnBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            /**
             * albumCoverUrl290 : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg
             * albumId : 3895053
             * commentsCount : 0
             * coverLarge : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_large.jpg
             * coverMiddle : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_meduim.jpg
             * coverSmall : http://fdfs.xmcdn.com/group13/M06/25/AF/wKgDXlbrvDbypGQiAAGICY85-lQ841_mobile_small.jpg
             * id : 3895053
             * intro : 客家人过“土地公诞”
             * isFinished : 0
             * isPaid : false
             * nickname : 大雄带你睇广东
             * playsCounts : 90893
             * priceTypeId : 0
             * provider : man
             * serialState : 0
             * tags : 广东,岭南,文化
             * title : 大雄带你睇广东
             * trackId : 31582255
             * trackTitle : 客家人过“土地公诞”
             * tracks : 139
             * uid : 45009420
             */

            private String albumCoverUrl290;
            private int albumId;
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
            private int serialState;
            private String tags;
            private String title;
            private int trackId;
            private String trackTitle;
            private int tracks;
            private int uid;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public String getAlbumCoverUrl290() {
                return albumCoverUrl290;
            }

            public void setAlbumCoverUrl290(String albumCoverUrl290) {
                this.albumCoverUrl290 = albumCoverUrl290;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
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
        }
    }

    public static class HotRecommendsBean {

        @SerializedName("ret")
        private int retX;
        private String title;
        private List<ListBeanXXXX> list;

        public static HotRecommendsBean objectFromData(String str) {

            return new Gson().fromJson(str, HotRecommendsBean.class);
        }

        public int getRetX() {
            return retX;
        }

        public void setRetX(int retX) {
            this.retX = retX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBeanXXXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXXXX> list) {
            this.list = list;
        }

        public static class ListBeanXXXX {

            private int categoryId;
            private int categoryType;
            private String contentType;
            private int count;
            private boolean filterSupported;
            private boolean hasMore;
            private boolean isFinished;
            private boolean isPaid;
            private String title;
            private List<ListBeanXXX> list;

            public static ListBeanXXXX objectFromData(String str) {

                return new Gson().fromJson(str, ListBeanXXXX.class);
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public int getCategoryType() {
                return categoryType;
            }

            public void setCategoryType(int categoryType) {
                this.categoryType = categoryType;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public boolean isFilterSupported() {
                return filterSupported;
            }

            public void setFilterSupported(boolean filterSupported) {
                this.filterSupported = filterSupported;
            }

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public boolean isIsFinished() {
                return isFinished;
            }

            public void setIsFinished(boolean isFinished) {
                this.isFinished = isFinished;
            }

            public boolean isIsPaid() {
                return isPaid;
            }

            public void setIsPaid(boolean isPaid) {
                this.isPaid = isPaid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBeanXXX> getList() {
                return list;
            }

            public void setList(List<ListBeanXXX> list) {
                this.list = list;
            }

            public static class ListBeanXXX {

                /**
                 * albumCoverUrl290 : http://fdfs.xmcdn.com/group9/M08/9D/32/wKgDYlduJAOQuuPmAAIFw8pL7F4453_mobile_meduim.jpg
                 * albumId : 4313745
                 * commentsCount : 17
                 * coverLarge : http://fdfs.xmcdn.com/group9/M08/9D/32/wKgDYlduJAOQuuPmAAIFw8pL7F4453_mobile_large.jpg
                 * coverMiddle : http://fdfs.xmcdn.com/group9/M08/9D/32/wKgDYlduJAOQuuPmAAIFw8pL7F4453_mobile_meduim.jpg
                 * coverSmall : http://fdfs.xmcdn.com/group9/M08/9D/32/wKgDYlduJAOQuuPmAAIFw8pL7F4453_mobile_small.jpg
                 * discountedPrice : 0.3
                 * displayDiscountedPrice : 0.30喜点/集
                 * displayPrice : 0.30喜点/集
                 * id : 4313745
                 * intro : 刑侦反腐小说:解密官场潜规则
                 * isFinished : 2
                 * isPaid : true
                 * nickname : 全勇
                 * playsCounts : 108839
                 * price : 0.3
                 * priceTypeEnum : 1
                 * priceTypeId : 1
                 * provider : man
                 * score : 4.8
                 * serialState : 2
                 * tags : 官场商战,都市生活
                 * title : 《政法书记》官场现形记
                 * trackId : 16322529
                 * trackTitle : 真实呈现政法书记揭黑、打黑全过程
                 * tracks : 52
                 * uid : 14528933
                 */

                private String albumCoverUrl290;
                private int albumId;
                private int commentsCount;
                private String coverLarge;
                private String coverMiddle;
                private String coverSmall;
                private double discountedPrice;
                private String displayDiscountedPrice;
                private String displayPrice;
                private int id;
                private String intro;
                private int isFinished;
                private boolean isPaid;
                private String nickname;
                private int playsCounts;
                private double price;
                private int priceTypeEnum;
                private int priceTypeId;
                private String provider;
                private double score;
                private int serialState;
                private String tags;
                private String title;
                private int trackId;
                private String trackTitle;
                private int tracks;
                private int uid;

                public static ListBeanXXX objectFromData(String str) {

                    return new Gson().fromJson(str, ListBeanXXX.class);
                }

                public String getAlbumCoverUrl290() {
                    return albumCoverUrl290;
                }

                public void setAlbumCoverUrl290(String albumCoverUrl290) {
                    this.albumCoverUrl290 = albumCoverUrl290;
                }

                public int getAlbumId() {
                    return albumId;
                }

                public void setAlbumId(int albumId) {
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

                public double getScore() {
                    return score;
                }

                public void setScore(double score) {
                    this.score = score;
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
            }
        }
    }

    public static class MemberBean {

        /**
         * hasMore : true
         * list : [{"bannerUrl":"http://fdfs.xmcdn.com/group20/M07/0A/70/wKgJLFd9ip6Q5YApAAO6_tJi7MQ099_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":16,"nickname":"NJ语瞳","ownerTitle":["喜马签约主播","百思官方主播","开心一刻主播","语你相伴创始"],"subTitle":"陪你走过春夏秋冬，温暖你的每一个梦。 ","title":"专属语你","uid":29668435},{"bannerUrl":"http://fdfs.xmcdn.com/group23/M0B/13/4A/wKgJNFhmISrRdfYhAADJ5S7kc_k814_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":15,"nickname":"科学队长","ownerTitle":["科学家天团"],"subTitle":"教授博导给小朋友讲科学","title":"科学队长，你耳边的科学家","uid":59687486},{"bannerUrl":"http://fdfs.xmcdn.com/group20/M09/E5/EB/wKgJJ1fO6sbjNgT-AAE04GeNhPI824_mobile_x_large.jpg","isAuthorizedMember":false,"memberProductId":13,"nickname":"小姿","ownerTitle":["二级心理咨询师","我知你心创始人","喜马情感女神","湖南广电主持人","网络红人"],"subTitle":"识破男人心，女人心，小姿做你的专属守护！","title":"小姿私密答","uid":9989998}]
         * ret : 0
         * title : 付费会员
         */

        private boolean hasMore;
        @SerializedName("ret")
        private int retX;
        private String title;
        private List<ListBeanXXXXX> list;

        public static MemberBean objectFromData(String str) {

            return new Gson().fromJson(str, MemberBean.class);
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public int getRetX() {
            return retX;
        }

        public void setRetX(int retX) {
            this.retX = retX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBeanXXXXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXXXXX> list) {
            this.list = list;
        }

        public static class ListBeanXXXXX {

            /**
             * bannerUrl : http://fdfs.xmcdn.com/group20/M07/0A/70/wKgJLFd9ip6Q5YApAAO6_tJi7MQ099_mobile_x_large.jpg
             * isAuthorizedMember : false
             * memberProductId : 16
             * nickname : NJ语瞳
             * ownerTitle : ["喜马签约主播","百思官方主播","开心一刻主播","语你相伴创始"]
             * subTitle : 陪你走过春夏秋冬，温暖你的每一个梦。
             * title : 专属语你
             * uid : 29668435
             */

            private String bannerUrl;
            private boolean isAuthorizedMember;
            private int memberProductId;
            private String nickname;
            private String subTitle;
            private String title;
            private int uid;
            private List<String> ownerTitle;

            public static ListBeanXXXXX objectFromData(String str) {

                return new Gson().fromJson(str, ListBeanXXXXX.class);
            }

            public String getBannerUrl() {
                return bannerUrl;
            }

            public void setBannerUrl(String bannerUrl) {
                this.bannerUrl = bannerUrl;
            }

            public boolean isIsAuthorizedMember() {
                return isAuthorizedMember;
            }

            public void setIsAuthorizedMember(boolean isAuthorizedMember) {
                this.isAuthorizedMember = isAuthorizedMember;
            }

            public int getMemberProductId() {
                return memberProductId;
            }

            public void setMemberProductId(int memberProductId) {
                this.memberProductId = memberProductId;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public List<String> getOwnerTitle() {
                return ownerTitle;
            }

            public void setOwnerTitle(List<String> ownerTitle) {
                this.ownerTitle = ownerTitle;
            }
        }
    }

    public static class PaidAreaBean {

        /**
         * categoryId : 33
         * categoryType : 1
         * contentType : album
         * count : 1000
         * filterSupported : false
         * hasMore : true
         * isFinished : false
         * isPaid : true
         * list : [{"albumCoverUrl290":"http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_meduim.jpg","albumId":6949655,"commentsCount":0,"coverLarge":"http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_large.jpg","coverMiddle":"http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_meduim.jpg","coverSmall":"http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_small.jpg","discountedPrice":49,"displayDiscountedPrice":"49.00喜点","displayPrice":"99.00喜点","id":6949655,"intro":"五代宰相 亲传防小人宝典","isFinished":0,"isPaid":true,"nickname":"魏新","playsCounts":4656,"price":99,"priceTypeEnum":2,"priceTypeId":2,"provider":"recsys","recSrc":"PB","recTrack":"mr.payBest.1001","score":0,"serialState":0,"tags":"百家讲坛,魏新,小人","title":"百家讲坛魏新：防小人宝典","trackId":31727242,"trackTitle":"懂小人者治天下。 百家讲坛主讲人魏新，带你品读史上最成功的官僚和他的《荣枯鉴》...","tracks":5,"uid":75022709},{"albumCoverUrl290":"http://fdfs.xmcdn.com/group22/M04/C8/65/wKgJLli2aHqAH1EDAAMpvBJ5_h8604_mobile_meduim.jpg","albumId":6921828,"commentsCount":31,"coverLarge":"http://fdfs.xmcdn.com/group22/M04/C8/65/wKgJLli2aHqAH1EDAAMpvBJ5_h8604_mobile_large.jpg","coverMiddle":"http://fdfs.xmcdn.com/group22/M04/C8/65/wKgJLli2aHqAH1EDAAMpvBJ5_h8604_mobile_meduim.jpg","coverSmall":"http://fdfs.xmcdn.com/group22/M04/C8/65/wKgJLli2aHqAH1EDAAMpvBJ5_h8604_mobile_small.jpg","discountedPrice":199,"displayDiscountedPrice":"199.00喜点","displayPrice":"365.00喜点","id":6921828,"intro":"何当共读香芸帙，最是诗情画意时。一生不可错过的百首唯美诗词。","isFinished":0,"isPaid":true,"nickname":"郦波","playsCounts":527488,"price":365,"priceTypeEnum":2,"priceTypeId":2,"provider":"recsys","recSrc":"PB","recTrack":"mr.payBest.1001","score":5,"serialState":0,"tags":"中国诗词大会,郦波,诗词","title":"郦波：一生不可错过的唯美诗词","trackId":31571911,"trackTitle":"《中国诗词大会》精彩继续，郦波老师开启诗词美学之旅。 带你品味一生中不可错过的...","tracks":5,"uid":74049971},{"albumCoverUrl290":"http://fdfs.xmcdn.com/group24/M08/57/B8/wKgJMFiVpg3i_AmbAAFGh6zwpLI814_mobile_meduim.jpg","albumId":6525618,"commentsCount":104,"coverLarge":"http://fdfs.xmcdn.com/group24/M08/57/B8/wKgJMFiVpg3i_AmbAAFGh6zwpLI814_mobile_large.jpg","coverMiddle":"http://fdfs.xmcdn.com/group24/M08/57/B8/wKgJMFiVpg3i_AmbAAFGh6zwpLI814_mobile_meduim.jpg","coverSmall":"http://fdfs.xmcdn.com/group24/M08/57/B8/wKgJMFiVpg3i_AmbAAFGh6zwpLI814_mobile_small.jpg","discountedPrice":168,"displayDiscountedPrice":"168.00喜点","displayPrice":"299.00喜点","id":6525618,"intro":"做你人生的超级演说家","isFinished":0,"isPaid":true,"nickname":"寇乃馨","playsCounts":326002,"price":299,"priceTypeEnum":2,"priceTypeId":2,"provider":"recsys","recSrc":"PB","recTrack":"mr.payBest.1001","score":4.8,"serialState":0,"tags":"寇乃馨,演说,演讲","title":"寇乃馨：演说，改变你的命运！","trackId":0,"trackTitle":"做你人生的超级演说家","tracks":12,"uid":71241360}]
         * title : 精品
         */

        private int categoryId;
        private int categoryType;
        private String contentType;
        private int count;
        private boolean filterSupported;
        private boolean hasMore;
        private boolean isFinished;
        private boolean isPaid;
        private String title;
        private List<ListBeanXXXXXX> list;

        public static PaidAreaBean objectFromData(String str) {

            return new Gson().fromJson(str, PaidAreaBean.class);
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isFilterSupported() {
            return filterSupported;
        }

        public void setFilterSupported(boolean filterSupported) {
            this.filterSupported = filterSupported;
        }

        public boolean isHasMore() {
            return hasMore;
        }

        public void setHasMore(boolean hasMore) {
            this.hasMore = hasMore;
        }

        public boolean isIsFinished() {
            return isFinished;
        }

        public void setIsFinished(boolean isFinished) {
            this.isFinished = isFinished;
        }

        public boolean isIsPaid() {
            return isPaid;
        }

        public void setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBeanXXXXXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXXXXXX> list) {
            this.list = list;
        }

        public static class ListBeanXXXXXX {

            /**
             * albumCoverUrl290 : http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_meduim.jpg
             * albumId : 6949655
             * commentsCount : 0
             * coverLarge : http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_large.jpg
             * coverMiddle : http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_meduim.jpg
             * coverSmall : http://fdfs.xmcdn.com/group20/M06/AE/90/wKgJJ1i45XmyFYKwAAF-wYXdMBw001_mobile_small.jpg
             * discountedPrice : 49.0
             * displayDiscountedPrice : 49.00喜点
             * displayPrice : 99.00喜点
             * id : 6949655
             * intro : 五代宰相 亲传防小人宝典
             * isFinished : 0
             * isPaid : true
             * nickname : 魏新
             * playsCounts : 4656
             * price : 99.0
             * priceTypeEnum : 2
             * priceTypeId : 2
             * provider : recsys
             * recSrc : PB
             * recTrack : mr.payBest.1001
             * score : 0.0
             * serialState : 0
             * tags : 百家讲坛,魏新,小人
             * title : 百家讲坛魏新：防小人宝典
             * trackId : 31727242
             * trackTitle : 懂小人者治天下。 百家讲坛主讲人魏新，带你品读史上最成功的官僚和他的《荣枯鉴》...
             * tracks : 5
             * uid : 75022709
             */

            private String albumCoverUrl290;
            private int albumId;
            private int commentsCount;
            private String coverLarge;
            private String coverMiddle;
            private String coverSmall;
            private double discountedPrice;
            private String displayDiscountedPrice;
            private String displayPrice;
            private int id;
            private String intro;
            private int isFinished;
            private boolean isPaid;
            private String nickname;
            private int playsCounts;
            private double price;
            private int priceTypeEnum;
            private int priceTypeId;
            private String provider;
            private String recSrc;
            private String recTrack;
            private double score;
            private int serialState;
            private String tags;
            private String title;
            private int trackId;
            private String trackTitle;
            private int tracks;
            private int uid;

            public static ListBeanXXXXXX objectFromData(String str) {

                return new Gson().fromJson(str, ListBeanXXXXXX.class);
            }

            public String getAlbumCoverUrl290() {
                return albumCoverUrl290;
            }

            public void setAlbumCoverUrl290(String albumCoverUrl290) {
                this.albumCoverUrl290 = albumCoverUrl290;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
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

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
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
        }
    }
}