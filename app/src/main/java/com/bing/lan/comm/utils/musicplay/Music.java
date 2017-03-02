package com.bing.lan.comm.utils.musicplay;

import java.io.Serializable;

/**
 * @author 蓝兵
 * @time 2017/3/1  22:54
 */
public class Music  implements Serializable{
    public String Url;

    public Music(String url) {
        Url = url;
    }
}
