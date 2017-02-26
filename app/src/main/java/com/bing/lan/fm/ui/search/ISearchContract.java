package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface ISearchContract {

    interface ISearchView
            extends IBaseActivityContract.IBaseActivityView<ISearchPresenter> {
          void updateSearchWord(String searchWord) ;

    }

    interface ISearchPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<ISearchView, ISearchModule> {

    }

    interface ISearchModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
