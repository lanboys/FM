package com.bing.lan.fm.ui.music;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.fm.ui.music.bean.TrackInfoBean;

public interface IMusicContract {

    interface IMusicView
            extends IBaseActivityContract.IBaseActivityView<IMusicPresenter> {
          void updateTrackInfo(TrackInfoBean trackInfoBean);

    }

    interface IMusicPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IMusicView, IMusicModule> {

    }

    interface IMusicModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
