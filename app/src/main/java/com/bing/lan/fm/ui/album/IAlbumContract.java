package com.bing.lan.fm.ui.album;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;

import java.util.List;

public interface IAlbumContract {

    interface IAlbumView
            extends IBaseActivityContract.IBaseActivityView<IAlbumPresenter> {

        void updateRecyclerView(List<TracksInfoBean> data);

        void loadRecyclerView(List<TracksInfoBean> data);
    }

    interface IAlbumPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IAlbumView, IAlbumModule> {

    }

    interface IAlbumModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
