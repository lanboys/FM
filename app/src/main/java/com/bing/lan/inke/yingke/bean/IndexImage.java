package com.bing.lan.inke.yingke.bean;

import java.util.List;


public class IndexImage {


    /**
     * ticker : [{"image":"ODgyMjkxNDc3OTE4OTA3.jpg","link":"https://act.inke.cn/banner/201610/reward_nov.html?web_entrance_id=1","atom":0},{"image":"MTAzODMxNDc4NDg2MzU1.jpg","link":"https://act.inke.cn/banner/201610/rookie/rookie.html?web_entrance_id=1","atom":0},{"image":"OTE2NTgxNDc4MjUzMTEw.jpg","link":"https://act.inke.cn/banner/201610/sports-anchor.html?web_entrance_id=1","atom":0},{"image":"NzkwNDIxNDc3OTI1MTky.jpg","link":"https://act.inke.cn/banner/201610/new_tab3.html?web_entrance_id=1","atom":0},{"image":"Njc4ODcxNDczNzQ1MjEz.jpg","link":"https://act.inke.cn/banner/201609/inke_rules.html?web_entrance_id=1","atom":0}]
     * error_msg : 操作成功
     * dm_error : 0
     */

    private String error_msg;
    private int dm_error;
    /**
     * image : ODgyMjkxNDc3OTE4OTA3.jpg
     * link : https://act.inke.cn/banner/201610/reward_nov.html?web_entrance_id=1
     * atom : 0
     */

    private List<TickerBean> ticker;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getDm_error() {
        return dm_error;
    }

    public void setDm_error(int dm_error) {
        this.dm_error = dm_error;
    }

    public List<TickerBean> getTicker() {
        return ticker;
    }

    public void setTicker(List<TickerBean> ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "IndexImage{" +
                "error_msg='" + error_msg + '\'' +
                ", dm_error=" + dm_error +
                ", ticker=" + ticker +
                '}';
    }
}
