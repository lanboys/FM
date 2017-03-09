package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.fm.ui.search.bean.SearchHotWordResult;

import java.util.List;

public interface ISearchContract {

    interface ISearchView
            extends IBaseActivityContract.IBaseActivityView<ISearchPresenter> {

        void updateSearchWord(String searchWord);

        void updateFlowContainer2(List<SearchHotWordResult.HotWordListBean> hotWordListBeen);
    }

    interface ISearchPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<ISearchView, ISearchModule> {

    }

    interface ISearchModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
