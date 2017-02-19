package com.bing.lan.comm.base.mvp.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bing.lan.comm.base.mvp.IBaseContract.IBaseModule;
import com.bing.lan.comm.base.mvp.IBaseContract.IBasePresenter;
import com.bing.lan.comm.base.mvp.IBaseContract.IBaseView;
import com.bing.lan.comm.view.LoadPageView;

public interface IBaseFragmentContract {

    interface IBaseFragmentView<T extends IBaseFragmentPresenter> extends IBaseView<T> {

        void stopUpdate();

        void reStartUpdate();

        void setViewState2LoadPage(LoadPageView.LoadDataResult loadDataResult);

        void resetErrorCount();

        FragmentActivity getActivity();

        boolean isHaveData();

        void setHaveData(boolean haveData);

        void updateTitle(String title);
    }

    interface IBaseFragmentPresenter<T extends IBaseFragmentView, M extends IBaseFragmentModule>
            extends IBasePresenter<T, M> {

        void stopUpdate();

        void setParams(Bundle bundle);

        void reStartUpdate();

        String getTitle();
    }

    interface IBaseFragmentModule extends IBaseModule {

    }
}
