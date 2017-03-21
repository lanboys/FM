package com.bing.lan.comm.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.bing.lan.comm.utils.AppUtil;
import com.orhanobut.logger.Logger;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;

import cn.jpush.android.api.JPushInterface;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:26
 */
public class BaseApplication extends Application {

    //1.创建一个静态的事件总线
    public static Bus sBus;

    // TODO: 2017/3/17 要注意减小包
    //不加会导致报错 因为方法数太多了
    //  java.lang.NoClassDefFoundError: okhttp3.OkHttpClient$Builder
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //全局初始化
        AppUtil.initGlobal(this, getApplicationContext());
        //二维码
        ZXingLibrary.initDisplayOpinion(this);

        //otto
        if (sBus == null) {
            //ANY是说该事件总线 在哪条线程中运行 无所谓
            sBus = new Bus(ThreadEnforcer.ANY);
        }

        //图片加载
        // Fresco.initialize(this);

        //错误报告
        //ErrorReport.getInstance().init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        // File cache = getCacheFile();
        //
        // DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
        //         .setBaseDirectoryPath(cache)
        //         .build();
        //
        // ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
        //         .setDownsampleEnabled(true)
        //         .setMainDiskCacheConfig(diskCacheConfig)
        //         .build();
        //
        // Fresco.initialize(this, config);
        Logger.init("hked");
    }

    private File getCacheFile() {
        File sd = Environment.getExternalStorageDirectory();
        File cache = new File(sd, "mm");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }
}
