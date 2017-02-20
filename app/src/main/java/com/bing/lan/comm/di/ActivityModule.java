package com.bing.lan.comm.di;

import android.app.Activity;
import android.content.Intent;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.ui.main.IMainContract;
import com.bing.lan.fm.ui.main.MainActivity;
import com.bing.lan.fm.ui.main.MainModule;
import com.bing.lan.fm.ui.main.MainPresenter;
import com.bing.lan.fm.ui.pic.IPictureContract;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.bing.lan.fm.ui.pic.PictureModule;
import com.bing.lan.fm.ui.pic.PicturePresenter;
import com.bing.lan.fm.ui.splash.ISplashContract;
import com.bing.lan.fm.ui.splash.SplashActivity;
import com.bing.lan.fm.ui.splash.SplashModule;
import com.bing.lan.fm.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author 蓝兵
 * @time 2017/1/10  10:51
 */
@Module
public class ActivityModule {

    private Activity mActivity;
    private Intent mIntent;

    // protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    public ActivityModule(Activity activity, Intent intent) {
        this.mActivity = activity;
        this.mIntent = intent;
    }

    /**
     * 注入的类型必须完全一致
     *
     * @return
     */
    @Provides
    public ISplashContract.ISplashPresenter provideSplashPresenter() {
        SplashPresenter splashPresenter = new SplashPresenter();
        splashPresenter.setModule(new SplashModule());
        splashPresenter.onAttachView((SplashActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public IMainContract.IMainPresenter provideMainPresenter() {
        MainPresenter splashPresenter = new MainPresenter();
        splashPresenter.setModule(new MainModule());
        splashPresenter.onAttachView((MainActivity) mActivity);
        return splashPresenter;
    }
    @Provides
    public IPictureContract.IPicturePresenter providePicturePresenter() {
        PicturePresenter splashPresenter = new PicturePresenter();
        splashPresenter.setModule(new PictureModule());
        splashPresenter.onAttachView((PictureActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mActivity.getClass(), 1);
    }
}
