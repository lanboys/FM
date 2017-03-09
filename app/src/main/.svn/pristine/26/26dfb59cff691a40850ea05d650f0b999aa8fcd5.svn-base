/**
 * 作者：artzok on 2016/9/6 12:14
 * 邮箱：artzok@163.com
 */
package com.bing.lan.comm.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bing.lan.comm.config.AppConfig;

/**
 * @author 赵坤
 * @email artzok@163.com
 */
public class SPUtil {

    private static SharedPreferences sp;

    private static SharedPreferences getSp() {
        if (sp == null)
            sp = AppUtil.getAppContext().getSharedPreferences(
                    AppConfig.SHARED_PREFER_FILE, Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * get param of boolean from config.xml file
     *
     * @param key     corresponding value's key
     * @return corresponding value
     */
    public static boolean getBoolean(String key) {
        return getSp().getBoolean(key, false);
    }

    /**
     * set param of boolean to config.xml file
     *
     * @param key     corresponding value's key
     * @param value   corresponding value
     */
    public static void putBoolean( String key, boolean value) {
        getSp().edit().putBoolean(key, value).apply();
    }

    /**
     * get a string value of a certain key
     *
     * @param key     someone certain key
     * @return except value or a null if not find
     */
    public static String getString(String key) {
        return getSp().getString(key, "");
    }

    /**
     * store a string value of a certain key
     *
     * @param key     certain key
     * @param value   correspond value
     */
    public static void putString(String key, String value) {
        getSp().edit().putString(key, value).apply();
    }

    /**
     * get a integer value of a certain key
     *
     * @param key     someone certain key
     * @return except value or a 0 if not find
     */
    public static int getInt(String key) {
        return getSp().getInt(key, 0);
    }

    /**
     * store a integer value of a certain key
     *
     * @param key     certain key
     * @param value   correspond value
     */
    public static void putInt(String key, int value) {
        getSp().edit().putInt(key, value).apply();
    }
}
