package com.bing.lan.fm.ui.search.bean;

import com.bing.lan.fm.bean.ResultBean;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/15  22:52
 */
public class SearchHintWordResult extends ResultBean {

    private List<SearchWordBean> list;

    public List<SearchWordBean> getList() {
        return list;
    }

    public void setList(List<SearchWordBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SearchWordResult{" +
                "list=" + list +
                '}';
    }

    public static class SearchWordBean {

        /**
         * guideWord : 大家都在搜：蒙曼品唐诗
         * searchWord : 蒙曼品唐诗
         */

        private String guideWord;
        private String searchWord;

        @Override
        public String toString() {
            return "SearchWordBean{" +
                    "guideWord='" + guideWord + '\'' +
                    ", searchWord='" + searchWord + '\'' +
                    '}';
        }

        public static SearchWordBean objectFromData(String str) {

            return new Gson().fromJson(str, SearchWordBean.class);
        }

        public String getGuideWord() {
            return guideWord;
        }

        public void setGuideWord(String guideWord) {
            this.guideWord = guideWord;
        }

        public String getSearchWord() {
            return searchWord;
        }

        public void setSearchWord(String searchWord) {
            this.searchWord = searchWord;
        }
    }
}
