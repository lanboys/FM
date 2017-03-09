package com.bing.lan.fm.ui.anchordetail;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IAnchorDetailContract {

    interface IAnchorDetailView
            extends IBaseActivityContract.IBaseActivityView<IAnchorDetailPresenter> {

    }

    interface IAnchorDetailPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAnchorDetailView, IAnchorDetailModule> {

    }

    interface IAnchorDetailModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
