package com.bing.lan.fm.ui.splash1;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface ISplashContract {

    interface ISplashView extends IBaseFragmentContract.IBaseFragmentView<ISplashPresenter> {
        void startAnimation();

        void animationFinished();
    }

    interface ISplashPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<ISplashView, ISplashModule> {

        void animationFinished();
    }

    interface ISplashModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
