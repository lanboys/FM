package com.bing.lan.inke.yingke.util;

import com.orhanobut.logger.Logger;



public class LoggerUtil {

   static  boolean isDebug = true;
   public static void t(String content){
      if(isDebug){
          Logger.i("result = %s ",content);
      }
   }
}
