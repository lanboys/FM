package com.bing.lan.fm.ui.music;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.fm.ui.music.bean.PlayResult;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class MusicPresenter
        extends BaseActivityPresenter<IMusicContract.IMusicView, IMusicContract.IMusicModule>
        implements IMusicContract.IMusicPresenter {

    public static final int LOAD_TRACK = 0;

    @Override
    public void onStart(Object... params) {
        requestData(LOAD_TRACK, params);
    }

    @Override
    public void requestData(int action, Object... parameter) {
        switch (action) {
            case LOAD_TRACK:
                mModule.requestData(action, this, parameter);
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_TRACK:

                PlayResult playResult = (PlayResult) data;
                mView.updateTrackInfo(playResult.getTrackInfo());

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
    }
}
