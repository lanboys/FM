package com.bing.lan.fm.ui.home.bean;

import com.bing.lan.fm.bean.ResultBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/15  23:00
 */
public class HomeTabsResult extends ResultBean {

    /**
     * tabs : {"count":5,"first":1,"list":[{"title":"推荐","contentType":"recSys","url":""},{"title":"热门","contentType":"recommend","url":""},{"title":"分类","contentType":"category"},{"title":"榜单","contentType":"ranking"},{"title":"主播","contentType":"anchor"}]}
     */

    private TabsBean tabs;

    public static HomeTabsResult objectFromData(String str) {

        return new Gson().fromJson(str, HomeTabsResult.class);
    }

    @Override
    public String toString() {
        return "HomeTabsResult{" +
                "tabs=" + tabs +
                '}';
    }

    public TabsBean getTabs() {
        return tabs;
    }

    public void setTabs(TabsBean tabs) {
        this.tabs = tabs;
    }

    public static class TabsBean {

        /**
         * count : 5
         * first : 1
         * list : [{"title":"推荐","contentType":"recSys"},{"title":"热门","contentType":"recommend","url":""},{"title":"分类","contentType":"category"},{"title":"榜单","contentType":"ranking"},{"title":"主播","contentType":"anchor"}]
         */

        private int count;
        private int first;
        private List<TabBean> list;

        public static TabsBean objectFromData(String str) {

            return new Gson().fromJson(str, TabsBean.class);
        }

        @Override
        public String toString() {
            return "TabsBean{" +
                    "count=" + count +
                    ", first=" + first +
                    ", list=" + list +
                    '}';
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public List<TabBean> getList() {
            return list;
        }

        public void setList(List<TabBean> list) {
            this.list = list;
        }

        public static class TabBean {

            /**
             * title : 推荐
             * contentType : recSys
             * url :
             */

            private String title;
            private String contentType;
            private String url;

            public static TabBean objectFromData(String str) {

                return new Gson().fromJson(str, TabBean.class);
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
