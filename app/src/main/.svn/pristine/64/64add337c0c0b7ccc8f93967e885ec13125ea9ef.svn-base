package com.bing.lan.comm.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonLoader {

    Context mContext;

    public JsonLoader(Context context) {
        mContext = context;
    }

    private String loadLocalJson(String fileName) {
        try {

            InputStreamReader isr = new InputStreamReader(mContext.getAssets().open(fileName), "utf-8");
            //从assets获取json文件
            BufferedReader bfr = new BufferedReader(isr);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bfr.readLine()) != null) {
                stringBuilder.append(line);
            }//将JSON数据转化为字符串
            Log.d("qh", stringBuilder.toString());
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

    // public TabContentBean genContentWithLocalJson() {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     TabContentBean bean =null;
    //     try {
    //          bean = objectMapper.readValue(loadLocalJson(), TabContentBean.class);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return  bean;
    // }
}
