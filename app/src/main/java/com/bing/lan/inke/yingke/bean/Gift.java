package com.bing.lan.inke.yingke.bean;

import java.util.List;



public class Gift {


    /**
     * cl : [255,255,255]
     * exp : 10
     * gold : 1
     * icon : NTg4MzMxNDUxODkzMDkw.jpg
     * id : 131
     * image : OTc4MzYxNDUxODkzMDk4.jpg
     * name : 樱花雨
     * type : 1
     */

    private int exp;
    private int gold;
    private String icon;
    private int id;
    private String image;
    private String name;
    private int type;
    private List<Integer> cl;
    private boolean isSelect =false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getCl() {
        return cl;
    }

    public void setCl(List<Integer> cl) {
        this.cl = cl;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "exp=" + exp +
                ", gold=" + gold +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", cl=" + cl +
                '}';
    }
}
