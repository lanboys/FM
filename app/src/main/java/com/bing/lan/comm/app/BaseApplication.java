package com.bing.lan.comm.app;

import android.app.Application;

import com.bing.lan.comm.utils.AppUtil;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:26
 */
public class BaseApplication extends Application {

    //1.创建一个静态的事件总线
    public static Bus sBus;

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
        // ErrorReport.getInstance().init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);



    }
}
