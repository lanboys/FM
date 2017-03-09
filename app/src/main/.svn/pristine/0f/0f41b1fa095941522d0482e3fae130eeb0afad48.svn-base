package com.bing.lan.comm.base.mvp.activity;

import com.bing.lan.comm.base.mvp.IBaseContract.IBaseModule;
import com.bing.lan.comm.base.mvp.IBaseContract.IBasePresenter;
import com.bing.lan.comm.base.mvp.IBaseContract.IBaseView;

public interface IBaseActivityContract {

      interface IBaseActivityView<T extends IBaseActivityPresenter> extends IBaseView<T> {


    }

      interface IBaseActivityPresenter<T extends IBaseActivityView, M extends IBaseActivityModule>
            extends IBasePresenter<T, M> {

    }

      interface IBaseActivityModule extends IBaseModule {

    }
}
