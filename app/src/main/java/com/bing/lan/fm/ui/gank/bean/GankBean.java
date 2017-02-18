package com.bing.lan.fm.ui.gank.bean;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/17  22:42
 */
public class GankBean {

    /**
     * error : false
     * results : [{"_id":"58a10619421aa901f7902c6a","createdAt":"2017-02-13T09:04:25.996Z","desc":"2-13","publishedAt":"2017-02-13T11:54:17.922Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-13-16464498_1247940031909047_2763412151866490880_n.jpg","used":true,"who":"daimajia"},{"_id":"589d31a2421aa9270bc7332e","createdAt":"2017-02-10T11:21:06.747Z","desc":"2-10","publishedAt":"2017-02-10T11:38:22.122Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-10-16465759_171779496648995_128281069584646144_n.jpg","used":true,"who":"代码家"},{"_id":"589bb252421aa92dc0dfd3bf","createdAt":"2017-02-09T08:05:38.361Z","desc":"2-9","publishedAt":"2017-02-09T11:46:26.70Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-09-16583339_172818256542563_353855393375453184_n.jpg","used":true,"who":"daimajia"},{"_id":"589a7161421aa92db8a0041b","createdAt":"2017-02-08T09:16:17.678Z","desc":"2-8","publishedAt":"2017-02-08T11:39:51.371Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-08-16230686_191036801373876_4789664128824246272_n.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public static GankBean objectFromData(String str) {

        return new Gson().fromJson(str, GankBean.class);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @SuppressLint("ParcelCreator")
    public static class ResultsBean implements AsymmetricItem {

        /**
         * _id : 58a10619421aa901f7902c6a
         * createdAt : 2017-02-13T09:04:25.996Z
         * desc : 2-13
         * publishedAt : 2017-02-13T11:54:17.922Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-02-13-16464498_1247940031909047_2763412151866490880_n.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public static ResultsBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultsBean.class);
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public int getColumnSpan() {
            return 3;
        }

        @Override
        public int getRowSpan() {
            return 3;
        }

        @Override
        public int describeContents() {
            return 5;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }
}
