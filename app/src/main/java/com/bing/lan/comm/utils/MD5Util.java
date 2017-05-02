package com.bing.lan.comm.utils;

import android.support.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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


    //将这个byte[]的每个元素 转换为字符串
    private static String bytesToHexString(@NonNull byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        log.d("bytesToHexString(): " + bytes.length);
        log.d("bytesToHexString(): " + Arrays.toString(bytes));
        for (byte bt : bytes) {
            //将int 转换成 16 进制字符串 如: int(0011 0010)---> String("32")
            String hex = Integer.toHexString(0xFF & bt);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
            log.i("bytesToHexString(): " + sb);
        }
        return sb.toString();
    }
}
