package com.bing.lan.comm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.bing.lan.comm.utils.StringUtil.bytesToHexString;

/**
 * @author 蓝兵
 * @time 2017/2/9  9:14
 */
public class MD5Util {

    protected static final LogUtil log = LogUtil.getLogUtil(MD5Util.class, LogUtil.LOG_VERBOSE);

    public static String MD5(String str) {
        String key;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            byte[] bytes = digest.digest();
            key = bytesToHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            key = String.valueOf(str.hashCode());
        }
        log.i("MD5(): key " + key);
        return key;
    }
}
