package com.bing.lan.comm.config;

public class AppConfig {

    /**
     * sharePreference 文件名
     */
    public static final String SHARED_PREFER_FILE = "shared_prefer_file";
    /**
     * 缓存过期时间
     */
    public static final long SAFE_TIMER = 10000;

    /**
     * 无图模式
     */
    public static final String SETTING_NO_IMAGE = "no_image";
    /**
     * debug调试模式，所以只在debug模式下使用的功能使用该变量进行判断
     */
    public static final boolean DEBUG = true;

    /**
     * 日间/夜间模式存储的键值
     */
    public static final String DAY_NIGHT_MODE = "day_night_mode";


    /**
     * 日志前缀
     */
    public static final String TAG_PREFIX = "fmapp-->";


    public static final String DB_NAME = "fm.db";
}
