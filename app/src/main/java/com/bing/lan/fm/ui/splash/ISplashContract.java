package com.bing.lan.fm.ui.splash;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;



public interface ISplashContract {

    interface ISplashView<T extends ISplashPresenter> extends IBaseActivityContract.IBaseActivityView<T> {

        void startAnimation();

        void animationFinished();
    }

    interface ISplashPresenter<T extends ISplashView, M extends ISplashModule>
            extends IBaseActivityContract.IBaseActivityPresenter<T, M> {

        void animationFinished();

    }

    interface ISplashModule extends IBaseActivityContract.IBaseActivityModule {

    }
}
