package com.bing.lan.inke.yingke.bean;

import java.io.Serializable;

/**
 * Created by kay on 17/1/18.
 */

public class Inout implements Serializable{

    /**
     * gold : 397465
     * point : 21833417
     */

    /**映票*/
    private int gold;
    /**映客号*/
    private int point;

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Inout{" +
                "gold=" + gold +
                ", point=" + point +
                '}';
    }
}
