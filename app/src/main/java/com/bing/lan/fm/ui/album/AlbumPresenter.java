package com.bing.lan.fm.ui.album;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.fm.ui.album.bean.AlbumResultBean;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class AlbumPresenter
        extends BaseActivityPresenter<IAlbumContract.IAlbumView, IAlbumContract.IAlbumModule>
        implements IAlbumContract.IAlbumPresenter {

    public static final int LOAD_ALBUM = 0;
    public static final int LOAD_MORE = 1;

    @Override
    public void onStart(Object... params) {
        requestData(LOAD_ALBUM, params);
    }

    @Override
    public void requestData(int action, Object... parameter) {
        switch (action) {

            case LOAD_ALBUM:
                mModule.requestData(action, this, parameter);

                break;
            case LOAD_MORE:
                // mModule.requestData(action, this, parameter);

                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        switch (action) {

            case LOAD_ALBUM:
                mView.updateRecyclerView(((AlbumResultBean.DataBean) data).getTracks().getList());

                break;
            case LOAD_MORE:
                // mModule.requestData(action, this, parameter);

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
