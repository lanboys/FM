package com.bing.lan.fm.ui.hot.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/17  15:15
 */
public class HotInfoBean<T> {

    /**
     * ret : 0
     * title : 精品听单
     * hasMore : true
     * list : [{"columnType":1,"specialId":913,"title":"醉爱民谣：十座城，十首歌","subtitle":"《歌手》火了赵雷，也红了《成都》\r\n\u201c和我在成都的街头走一走\r\n直到所有的灯都熄灭了也不停留\r\n成都 带不走的 只有你\u201d\r\n每个人的心里都有一座带不走也留不下的城\r\n这十首民谣里唱的，有你心里的那座吗？\r\n\r\n\u2014\u2014致那个带不走也留不下的城市，和你","footnote":"10首声音","coverPath":"http://fdfs.xmcdn.com/group22/M03/75/88/wKgJM1idg17iev0HAASoSIVEDBg778_mobile_small.jpg","contentType":"2"},{"columnType":1,"specialId":912,"title":"\u201c中国诗词大会\u201d绝美诗词14首","subtitle":"在刚刚过去的这个春节里，《中国诗词大会》也许是最火的电视节目之一\r\n\u201c愿得一心人，白头不相离\u201d\r\n\u201c从此无心爱良夜，任他明月下西楼\u201d\r\n\u201c一川烟草，满城风絮，梅子黄时雨\u201d\u2026\u2026 \r\n\r\n\r\n\u201c中国诗词大会\u201d上的绝美古诗词，总有一句，能触动你的心弦","footnote":"14首声音","coverPath":"http://fdfs.xmcdn.com/group22/M09/75/C5/wKgJLlidgi-TvvhIAABy42ggYCI214_mobile_small.jpg","contentType":"2"}]
     */

    @SerializedName("ret")
    private int retX;
    private String title;
    private boolean hasMore;
    private List<T> list;

    public static HotInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, HotInfoBean.class);
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

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "HotInfoBean{" +
                "hasMore=" + hasMore +
                ", retX=" + retX +
                ", title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
