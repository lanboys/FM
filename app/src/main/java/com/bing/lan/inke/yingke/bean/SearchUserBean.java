package com.bing.lan.inke.yingke.bean;



public class SearchUserBean {
    private String reason;
    private SearchUser user;
    private String live_id;

    public String getLive_id() {
        return live_id;
    }

    public void setLive_id(String live_id) {
        this.live_id = live_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public SearchUser getUser() {
        return user;
    }

    public void setUser(SearchUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SearchUserBean{" +
                "reason='" + reason + '\'' +
                ", user=" + user +
                '}';
    }
}
