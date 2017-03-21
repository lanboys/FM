package com.bing.lan.inke.yingke.bean;

import java.util.List;

/**
 * Created by kay on 16/12/8.
 */

public class SearchUserWarper {
    private String title;
    private List<SearchUserBean> users;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SearchUserBean> getUsers() {
        return users;
    }

    public void setUsers(List<SearchUserBean> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SearchUserWarper{" +
                "title='" + title + '\'' +
                ", users=" + users +
                '}';
    }
}
