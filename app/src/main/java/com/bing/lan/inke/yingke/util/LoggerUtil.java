package com.bing.lan.inke.yingke.util;

import com.orhanobut.logger.Logger;

/**
 * Created by kay on 16/12/15.
 */

public class LoggerUtil {

   static  boolean isDebug = true;
   public static void t(String content){
      if(isDebug){
          Logger.i("result = %s ",content);
      }
   }
}
