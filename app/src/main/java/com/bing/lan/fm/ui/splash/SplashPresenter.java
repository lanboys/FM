package com.bing.lan.fm.ui.splash;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class SplashPresenter
        extends BaseActivityPresenter<ISplashContract.ISplashView, ISplashContract.ISplashModule>
        implements ISplashContract.ISplashPresenter<ISplashContract.ISplashView, ISplashContract.ISplashModule> {

    @Override
    public void onStart(Object... params) {
        mView.startAnimation();
    }

    public void animationFinished() {
        mView.animationFinished();
    }

    @Override
    public void onSuccess(int action, Object data) {

    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
