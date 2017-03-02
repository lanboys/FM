package com.bing.lan.fm.ui.music;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IActivityTestContract {

    interface IActivityTestView
            extends IBaseActivityContract.IBaseActivityView<IActivityTestPresenter> {

    }

    interface IActivityTestPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IActivityTestView, IActivityTestModule> {

    }

    interface IActivityTestModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
