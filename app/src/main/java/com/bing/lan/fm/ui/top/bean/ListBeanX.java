package com.bing.lan.fm.ui.top.bean;

import java.util.List;


public class ListBeanX {
    /**
     * calcPeriod : 日榜
     * categoryId : 0
     * contentType : track
     * coverPath : http://fdfs.xmcdn.com/group10/M00/6B/55/wKgDaVc6hsyxQ8nWAABWkqxY2as287.png
     * firstId : 31977652
     * firstKResults : [{"contentType":"track","id":31977652,"title":"《摸金天师》第1021章 一个大帝的力量精华（搜凶宅笔录，超好听~）"},{"contentType":"track","id":31945594,"title":"新的一周，早安我的朋友们~第586章 离京前的安排"},{"contentType":"track","id":31962149,"title":"如何对付房产中介？"}]
     * firstTitle : 《摸金天师》第1021章 一个大帝的力量精华（搜凶宅笔录，超好听~）
     * isAllPaid : false
     * key : 1_57_ranking:track:scoreByTime:1:0
     * list : []
     * maxPageId : 0
     * orderNum : 0
     * pageId : 0
     * pageSize : 1
     * period : 1
     * rankingListId : 57
     * rankingRule : scoreByTime
     * ret : 0
     * subtitle : 真相来了！榜单出炉，不服来辩！
     * title : 最火节目飙升榜
     * top : 100
     * totalCount : 0
     */

    private String calcPeriod;
    private int categoryId;
    private String contentType;
    private String coverPath;
    private int firstId;
    private String firstTitle;
    private boolean isAllPaid;
    private String key;
    private int maxPageId;
    private int orderNum;
    private int pageId;
    private int pageSize;
    private int period;
    private int rankingListId;
    private String rankingRule;
    private int ret;
    private String subtitle;
    private String title;
    private int top;
    private int totalCount;
    private List<FirstKResultsBean> firstKResults;
    private List<?> list;

    public String getCalcPeriod() {
        return calcPeriod;
    }

    public void setCalcPeriod(String calcPeriod) {
        this.calcPeriod = calcPeriod;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public boolean isIsAllPaid() {
        return isAllPaid;
    }

    public void setIsAllPaid(boolean isAllPaid) {
        this.isAllPaid = isAllPaid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getMaxPageId() {
        return maxPageId;
    }

    public void setMaxPageId(int maxPageId) {
        this.maxPageId = maxPageId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getRankingListId() {
        return rankingListId;
    }

    public void setRankingListId(int rankingListId) {
        this.rankingListId = rankingListId;
    }

    public String getRankingRule() {
        return rankingRule;
    }

    public void setRankingRule(String rankingRule) {
        this.rankingRule = rankingRule;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
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

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<FirstKResultsBean> getFirstKResults() {
        return firstKResults;
    }

    public void setFirstKResults(List<FirstKResultsBean> firstKResults) {
        this.firstKResults = firstKResults;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

}