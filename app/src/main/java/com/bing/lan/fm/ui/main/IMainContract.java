package com.bing.lan.fm.ui.main;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;

public interface IMainContract {

    interface IMainView<T extends IMainPresenter>
            extends IBaseActivityContract.IBaseActivityView<T> {

          void updateTitle(String title) ;

          void replaceFragment(BaseFragment instance);



        }

    interface IMainPresenter<T extends IMainView, M extends IMainModule>
            extends IBaseActivityContract.IBaseActivityPresenter<T, M> {

    }

    interface IMainModule extends IBaseActivityContract.IBaseActivityModule {

    }
}
