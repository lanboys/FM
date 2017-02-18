package com.bing.lan.fm.ui.main;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IMainContract {

    interface IMainView<T extends IMainPresenter>
            extends IBaseActivityContract.IBaseActivityView<T> {

    }

    interface IMainPresenter<T extends IMainView, M extends IMainModule>
            extends IBaseActivityContract.IBaseActivityPresenter<T, M> {

    }

    interface IMainModule extends IBaseActivityContract.IBaseActivityModule {

    }
}
