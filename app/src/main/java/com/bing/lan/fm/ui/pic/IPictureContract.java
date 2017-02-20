package com.bing.lan.fm.ui.pic;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IPictureContract {

    interface IPictureView
            extends IBaseActivityContract.IBaseActivityView<IPicturePresenter> {

    }

    interface IPicturePresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IPictureView, IPictureModule> {

    }

    interface IPictureModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
