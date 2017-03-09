package com.bing.lan.fm.ui.recommend.bean;

import java.util.List;

/**
 * @author jk
 * @time 2017/3/6  16:09
 * @desc ${TODD}
 */
public class RecBean {

    /**
     * data :
     * head_msg : 为您推荐了8条内容，下拉获取更多
     * msg : 25ms
     * msg_content : 以上为刚更新的8条推荐，点击获取更多
     * msg_pos : 8
     * ret : 0
     */

    private String head_msg;
    private String msg;
    private String msg_content;
    private int msg_pos;
    private int ret;
    private List<DataBean> data;



    public String getHead_msg() {
        return head_msg;
    }

    public void setHead_msg(String head_msg) {
        this.head_msg = head_msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public int getMsg_pos() {
        return msg_pos;
    }

    public void setMsg_pos(int msg_pos) {
        this.msg_pos = msg_pos;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

}
