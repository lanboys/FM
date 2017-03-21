package com.bing.lan.inke.yingke.util;

import de.tavendo.autobahn.WebSocketConnection;

/**
 * Description :
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/8 0008
 */

public class WebSocketManger {

    /**WebSocket 工具类*/
    private static WebSocketConnection mConnect = new WebSocketConnection();

    private WebSocketManger() {
    }
    public  static WebSocketConnection getInstance() {
        return mConnect;
    }

}
