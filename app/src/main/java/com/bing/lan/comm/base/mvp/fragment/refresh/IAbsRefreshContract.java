package com.bing.lan.comm.base.mvp.fragment.refresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

import java.util.List;

/**
 * Created by 520 on 2017/1/11.
 */

public interface IAbsRefreshContract {

    interface IAbsRefreshView extends IBaseFragmentContract.IBaseFragmentView<IAbsRefreshPresenter> {

        void openRefreshUI();

        void closeRefreshUI();

        void updateHeaderBanner(List<?> imageUrls);

        void updateFooterView(AbsRefreshFragment.LoadDataResult result);

        <LISTVIEWBEAN> void updateListViewData(List<LISTVIEWBEAN> data);

        void setListViewSelectLastVisible();

        <LISTVIEWBEAN> void loadMoreListViewData(List<LISTVIEWBEAN> data);

        boolean isOpenFooterLoadMoreView();

        boolean isOpenHeaderBannerView();

        // <LISTVIEWBEAN> void updateListView(List<LISTVIEWBEAN> data);
    }

    interface IAbsRefreshPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IAbsRefreshView, IAbsRefreshModule> {

        void errorReloadData();

        // void updateLatest();
        //
        // void loadingLatest();
        //
        // void updateBefore();

        void updateData();

        void onListViewScrollStateChanged(AbsListView view, int scrollState);

        void onListViewItemClick(AdapterView<?> parent, View view, int position, long id);

        void loadMoreData();
    }

    interface IAbsRefreshModule extends IBaseFragmentContract.IBaseFragmentModule {

        void loadData(int action, int index, final IBaseContract.OnDataChangerListener loadDataListener);

        boolean isLoading();

        void releaseSubscribe();
    }
}
