package com.bing.lan.comm.utils;

import android.util.Log;

import com.bing.lan.comm.config.AppConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUtil {

    public static final int LOG_VERBOSE = 1;
    public static final int LOG_INFO = 2;
    public static final int LOG_DEBUG = 3;
    public static final int LOG_WARN = 4;
    public static final int LOG_ERROR = 5;
    public static final int LOG_NONE = 6;

    /* date formatter */
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.CHINA);

    /* global log level and default value is LOG_ERROR */
    private static int GLOBAL_LOG_LEVEL = LOG_ERROR;
    /* default prefix of tag */
    private static String TAG_PREFIX = AppConfig.TAG_PREFIX;
    private static boolean IS_OPEN = true;
    /* current class log level */
    private int mLogLevel;
    /* current class log tag */
    private String tag;

    private LogUtil(Class clz, int level) {
        tag = TAG_PREFIX + clz.getSimpleName();
        mLogLevel = level;
    }

    public static void setTagPrefix(String tagPrefix) {
        TAG_PREFIX = tagPrefix;
    }

    /**
     * set global log level
     */
    public static void setGlobalLogLevel(int level) {
        GLOBAL_LOG_LEVEL = level;
    }

    /**
     * log tag equal clz name and log level equal logLevel
     *
     * @param clz      xx.getClass() or xx.class
     * @param logLevel
     * @return
     */
    public static LogUtil getLogUtil(Class clz, int logLevel) {
        return new LogUtil(clz, logLevel);
    }

    /**
     * log tag equal clz name and log level equal GLOBAL_LOG_LEVEL
     */
    public static LogUtil getLogUtil(Class clz) {
        return getLogUtil(clz, GLOBAL_LOG_LEVEL);
    }

    private String getPriorityLetter(int level) {
        switch (level) {
            case LOG_VERBOSE:
                return "Verbose";
            case LOG_INFO:
                return "Info";
            case LOG_DEBUG:
                return "Debug";
            case LOG_WARN:
                return "Warning";
            case LOG_ERROR:
                return "Error";
            // case LOG_NONE:
            //     return "Not_Log";
        }
        return null;
    }

    private String getFormat(String msg, int level) {
        return String.format("%s %s: %s\n", formatter.format(new Date()), getPriorityLetter(level), msg);
    }

    /**
     * @param level
     * @return
     */
    private boolean ensureLevel(int level) {
        //日志总开关
        if (!IS_OPEN) {
            return false;
        }
        //确定打印级别,true,则打印
        return (GLOBAL_LOG_LEVEL <= level || mLogLevel < level);
    }

    private void verbose(String tag, String msg, boolean format) {
        if (!ensureLevel(LOG_VERBOSE))
            return;
        if (format)
            msg = getFormat(msg, LOG_VERBOSE);
        Log.v(tag, msg);
    }

    private void info(String tag, String msg, boolean format) {
        if (!ensureLevel(LOG_INFO))
            return;
        if (format)
            msg = getFormat(msg, LOG_INFO);
        Log.i(tag, msg);
    }

    private void debug(String tag, String msg, boolean format) {
        if (!ensureLevel(LOG_DEBUG))
            return;
        if (format)
            msg = getFormat(msg, LOG_DEBUG);
        Log.d(tag, msg);
    }

    private void warn(String tag, String msg, Throwable tr, boolean format) {
        if (!ensureLevel(LOG_WARN))
            return;
        if (format)
            msg = getFormat(msg, LOG_WARN);
        Log.w(tag, msg, tr);
    }

    private void error(String tag, String msg, Throwable tr, boolean format) {
        if (!ensureLevel(LOG_ERROR))
            return;
        if (format)
            msg = getFormat(msg, LOG_ERROR);
        Log.e(tag, msg, tr);
    }

    ////////////log verbose/////////////
    public void v(String msg) {
        v(tag, msg);
    }

    private void v(String tag, String msg) {
        verbose(tag, msg, false);
    }

    public void vfmat(String msg) {
        vfmat(tag, msg);
    }

    private void vfmat(String tag, String msg) {
        verbose(tag, msg, true);
    }

    ////////////log info/////////////
    public void i(String msg) {
        i(tag, msg);
    }

    private void i(String tag, String msg) {
        info(tag, msg, false);
    }

    public void ifmat(String msg) {
        ifmat(tag, msg);
    }

    private void ifmat(String tag, String msg) {
        info(tag, msg, true);
    }

    ////////////log debug/////////////
    public void d(String msg) {
        d(tag, msg);
    }

    private void d(String tag, String msg) {
        debug(tag, msg, false);
    }

    public void dfmat(String msg) {
        dfmat(tag, msg);
    }

    private void dfmat(String tag, String msg) {
        debug(tag, msg, true);
    }

    ////////////log warn/////////////
    public void w(String msg) {
        w(tag, msg);
    }

    private void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public void w(String msg, Throwable tr) {
        w(tag, msg, tr);
    }

    private void w(String tag, String msg, Throwable tr) {
        warn(tag, msg, tr, false);
    }

    public void wfmat(String msg) {
        wfmat(tag, msg);
    }

    private void wfmat(String tag, String msg) {
        wfmat(tag, msg, null);
    }

    public void wfmat(String msg, Throwable tr) {
        wfmat(tag, msg, tr);
    }

    private void wfmat(String tag, String msg, Throwable tr) {
        warn(tag, msg, tr, true);
    }

    ////////////log error/////////////
    public void e(String msg) {
        e(tag, msg, null);
    }

    private void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public void e(String msg, Throwable tr) {
        e(tag, msg, tr);
    }

    private void e(String tag, String msg, Throwable tr) {
        error(tag, msg, tr, false);
    }

    public void efmat(String msg) {
        efmat(tag, msg, null);
    }

    private void efmat(String tag, String msg) {
        efmat(tag, msg, null);
    }

    public void efmat(String msg, Throwable tr) {
        efmat(tag, msg, tr);
    }

    private void efmat(String tag, String msg, Throwable tr) {
        error(tag, msg, tr, true);
    }
}
