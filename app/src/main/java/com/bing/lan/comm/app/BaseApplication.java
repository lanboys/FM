package com.bing.lan.comm.app;

import android.app.Application;

import com.bing.lan.comm.utils.AppUtil;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

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

        AppUtil.initGlobal(this, getApplicationContext());
        ZXingLibrary.initDisplayOpinion(this);
//        ImageUtil.prepare(getApplicationContext());




        if (sBus==null) {
            //ANY是说该事件总线 在哪条线程中运行 无所谓
            sBus=new Bus(ThreadEnforcer.ANY);
        }


        // ErrorReport.getInstance().init(this);


    }
}
