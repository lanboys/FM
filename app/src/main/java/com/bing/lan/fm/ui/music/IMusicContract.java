package com.bing.lan.fm.ui.music;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IMusicContract {

    interface IMusicView
            extends IBaseActivityContract.IBaseActivityView<IMusicPresenter> {

    }

    interface IMusicPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IMusicView, IMusicModule> {

    }

    interface IMusicModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
