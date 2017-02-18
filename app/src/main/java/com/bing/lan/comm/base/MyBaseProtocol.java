package com.bing.lan.comm.base;

import android.os.SystemClock;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.FileUtils;
import com.bing.lan.comm.utils.IOUtils;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.config.AppConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// import com.squareup.okhttp.OkHttpClient;
// import com.squareup.okhttp.Request;
// import com.squareup.okhttp.Response;

/**
 * 访问协议的分装,但是是一个基类
 */

public abstract class MyBaseProtocol<T> {

    public LogUtil log = LogUtil.getLogUtil(MyBaseProtocol.class, 1);

    public T getParseResults(String url) throws IOException {

        //内存
        T t;
        t = getCacheData(url);
        if (t != null)
            return t;

        //SD卡
        t = getSDData(url);
        if (t != null) {
            return t;
        }

        //网络
        t = getDataFromNet(url);
        return t;
    }

    private T getDataFromNet(String url) throws IOException {
        //1.创建OkHttpClient实例对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.请求数据
        Request request = new Request.Builder().get().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        //判断是否链接成功
        if (response.isSuccessful()) {
            T t;
            BufferedWriter bufferedWriter = null;
            try {
                //链接服务器成功
                String jsonStr = response.body().string();
                log.d("getDataFromNet(): " + jsonStr);
                t = json2BeanOrList(jsonStr);
                // 1 得到key
                String key = getKeyParams(url);
                // 2保存内存
                AppUtil.putGlobal(key, jsonStr);
                //保存SD卡
                String path = FileUtils.getDir("json");
                File cacherSd = new File(path, key);
                bufferedWriter = new BufferedWriter(new FileWriter(cacherSd));
                bufferedWriter.write(SystemClock.currentThreadTimeMillis() + "");
                bufferedWriter.newLine();
                bufferedWriter.write(jsonStr);

                return t;
            } catch (Exception e) {
                log.e("getDataFromNet: 网络获取的json解析异常 ", e);
            } finally {
                IOUtils.close(bufferedWriter);
            }
        }
        return null;
    }

    //保存在SD卡中
    private T getSDData(String url) {
        BufferedReader reader = null;
        try {
            //保存在Sd卡中文件当中
            String path = FileUtils.getDir("json");
            // 1 得到key
            String key = getKeyParams(url);
            File cacheSd = new File(path, key);
            // 先判断这个文件是否有值
            if (cacheSd.exists()) {
                reader = new BufferedReader(new FileReader(cacheSd));
                String StrTimer = reader.readLine();
                long oldTimer = Long.parseLong(StrTimer);
                long DirTimer = SystemClock.currentThreadTimeMillis() - oldTimer;
                if (DirTimer < AppConfig.SAFE_TIMER) {
                    //说明没有过期
                    String JsonStr = reader.readLine();
                    T t = json2BeanOrList(JsonStr);
                    //保存一份到内存
                    AppUtil.putGlobal(key, JsonStr);

                    return t;
                }
            }
            // 判断这个事件是否过期
        } catch (Exception e) {
            log.e("getCacheData: SD卡json解析异常 ", e);
        } finally {
            IOUtils.close(reader);
        }
        return null;
    }

    private T getCacheData(String url) {
        T t;

        // 1 得到key
        String key = getKeyParams(url);

        // 2在这个hash中看是否能够找到这个key,如果能够找到,如果内存中有
        String JsonStr = (String) AppUtil.getGlobal(key, null);
        if (JsonStr != null) {
            try {
                t = json2BeanOrList(JsonStr);
                return t;
            } catch (Exception e) {
                log.e("getCacheData: 内存json解析异常 ", e);
            }
        }
        return null;
    }

    private String getKeyParams(String url) {
        return FileUtils.url2FileName(url);
    }

    /**
     * 重写解析方式
     *
     * @param jsonStr
     * @return
     */
    protected abstract T json2BeanOrList(String jsonStr) throws Exception;
}
