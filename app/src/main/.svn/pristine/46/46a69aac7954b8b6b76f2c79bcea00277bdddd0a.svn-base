package com.bing.lan.fm.ui.album;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.fm.ui.album.bean.AlbumResultBean;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.album.AlbumPresenter.LOAD_ALBUM;
import static com.bing.lan.fm.ui.album.AlbumPresenter.LOAD_MORE;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class AlbumModule extends BaseActivityModule
        implements IAlbumContract.IAlbumModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_ALBUM:
                loadAlbum(action, listener, (Long) parameter[0]);
                break;
            case LOAD_MORE:
                break;
        }
    }

    public void loadAlbum(int action, IBaseContract.OnDataChangerListener listener, Long albumId) {
        Observable<AlbumResultBean.DataBean> observable = mApiService.getAlbumResult(albumId)
                .filter(new Func1<AlbumResultBean, Boolean>() {
                    @Override
                    public Boolean call(AlbumResultBean albumResultBean) {
                        return albumResultBean.getRet() == 0;
                    }
                })
                .map(new Func1<AlbumResultBean, AlbumResultBean.DataBean>() {
                    @Override
                    public AlbumResultBean.DataBean call(AlbumResultBean albumResultBean) {
                        return albumResultBean.getData();
                    }
                });

        subscribe(observable, action, listener, "专辑页面");
    }
}
