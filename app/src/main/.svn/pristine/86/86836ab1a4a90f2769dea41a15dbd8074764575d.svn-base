package com.bing.lan.comm.di;

import android.app.Activity;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.utils.LogUtil;

import dagger.Module;
import dagger.Provides;

/**
 * @author 蓝兵
 * @time 2017/2/10  9:07
 */
@Module
public class DiModule {

    private Activity mActivity;
    private Class clz;
    private Object[] mObjects;

    public DiModule(Object... objects) {
        mObjects = objects;
    }

    // public DiModule(Activity activity, Class clz) {
    //     this.mActivity = activity;
    //     this.clz = clz;
    // }

    @Provides
    public ApiService provideApiService() {
        return ApiManager.getApiService();
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil((Class) mObjects[0], 1);
    }
}
