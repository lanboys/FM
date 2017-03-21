package com.bing.lan.inke.yingke.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description :
 * Author : liujun
 * Email  : liujin2son@163.com
 * Date   : 2017/3/4 0004
 */

public class TimerUtil {

    /***
     * @return 2017-03-4
     */
    public static String getDatefromSystem() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String date = sdf.format(curDate);
        return date;
    }
}
