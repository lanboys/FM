package com.bing.lan.fm.ui.splash1;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class SplashPresenter extends
        BaseFragmentPresenter<ISplashContract.ISplashView, ISplashContract.ISplashModule>
        implements ISplashContract.ISplashPresenter {



    @Override
    public void onStart(Object... params) {
        mView.startAnimation();
    }

    public void animationFinished() {
        mView.animationFinished();
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }

    @Override
    public void onSuccess(int action, Object data) {
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }
}
