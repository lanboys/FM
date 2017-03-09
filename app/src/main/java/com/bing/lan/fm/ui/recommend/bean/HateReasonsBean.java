package com.bing.lan.fm.ui.recommend.bean;

/**
 * @author jk
 * @time 2017/3/6  16:27
 * @desc ${TODD}
 */
public class HateReasonsBean {
    /**
     * ba : 不感兴趣
     * bc : 内容质量差
     * dl : 不喜欢本期节目
     * his : 听过了
     * wt : 不喜欢主播
     */

    private String ba;
    private String bc;
    private String dl;
    private String his;
    private String wt;

    public String getBa() {
        return ba;
    }

    public void setBa(String ba) {
        this.ba = ba;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public String getHis() {
        return his;
    }

    public void setHis(String his) {
        this.his = his;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }
}
