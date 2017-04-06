package com.bing.lan.inke.yingke.util;

import de.tavendo.autobahn.WebSocketConnection;


public class WebSocketManger {

    /**WebSocket 工具类*/
    private static WebSocketConnection mConnect = new WebSocketConnection();

    private WebSocketManger() {
    }
    public  static WebSocketConnection getInstance() {
        return mConnect;
    }

}
