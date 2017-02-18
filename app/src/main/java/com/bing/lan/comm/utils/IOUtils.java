package com.bing.lan.comm.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtil.getLogUtil(IOUtils.class).e(e.getMessage());
            }
        }
        return true;
    }

    public static boolean close(Closeable in, Closeable out) {
        return close(in) && close(out);
    }
}
