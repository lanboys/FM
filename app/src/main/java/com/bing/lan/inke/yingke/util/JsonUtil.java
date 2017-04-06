package com.bing.lan.inke.yingke.util;

import android.text.TextUtils;

import com.google.gson.Gson;



public class JsonUtil {
    static Gson gson;

    public static <T> T parse(String json, Class<T> tClass) {

        if (TextUtils.isEmpty(json)) {
            return null;
        }
        if (gson == null) {
            gson = new Gson();
        }
        T t = gson.fromJson(json, tClass);
        return t;
    }

    public  static String toJson(Object obj){
        if(obj==null)
            return null;
        if (gson == null) {
            gson = new Gson();
        }
        return  gson.toJson(obj);
    }

}
