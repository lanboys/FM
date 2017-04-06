package com.bing.lan.inke.yingke.bean;

import java.util.List;



public class SocketDate {

    private int state;//后端返回的状态100 表示成功

    private int type;// 1.代表返回的是首次登录 2.代表是聊天信息 3.代表的是礼物

    private String groud;// 哪一个直播间

    private String userName;//自己用户

    private String sendToUserName; //发送给目标用户

    private String msg;//

    private String msgType;// xxx@xxx

    private List<Gift> gifts;

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSendToUserName() {
        return sendToUserName;
    }

    public void setSendToUserName(String sendToUserName) {
        this.sendToUserName = sendToUserName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroud() {
        return groud;
    }

    public void setGroud(String groud) {
        this.groud = groud;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "SocketDate{" +
                "state=" + state +
                ", type=" + type +
                ", groud='" + groud + '\'' +
                ", userName='" + userName + '\'' +
                ", sendToUserName='" + sendToUserName + '\'' +
                ", msg='" + msg + '\'' +
                ", msgType='" + msgType + '\'' +
                ", gifts=" + gifts +
                '}';
    }
}
