package com.bing.lan.inke.yingke.bean;

/**
 * Description :
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/11 0011
 */

public class GiftReceive extends  Gift {

    private String user;
    private int counts;
    private  long timeTemp;

    public long getTimeTemp() {
        return timeTemp;
    }

    public void setTimeTemp(long timeTemp) {
        this.timeTemp = timeTemp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public GiftReceive() {

    }

    @Override
    public String toString() {
        return "GiftReceive{" +
                "user='" + user + '\'' +
                ", counts=" + counts +
                '}';
    }
}
