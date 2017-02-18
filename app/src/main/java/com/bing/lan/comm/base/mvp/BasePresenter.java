package com.bing.lan.comm.base.mvp;

import android.widget.ImageView;

import com.bing.lan.comm.utils.LogUtil;

/**
 * @author 蓝兵
 * @time 2017/1/12  18:31
 */
public abstract class BasePresenter<
        T extends IBaseContract.IBaseView,
        M extends IBaseContract.IBaseModule>
        implements IBaseContract.IBasePresenter<T, M> {

    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    // 会不会内存泄露
    protected M mModule;
    protected T mView;
    // protected boolean isHaveData;

    @Override
    public void setModule(M module) {
        mModule = module;
    }

    @Override
    public void onAttachView(T view) {
        mView = view;
    }

    @Override
    public void onDetachView() {
        mModule.releaseTask();
        mView = null;
    }

    @Override
    public void loadImage(Object path, ImageView imageView) {
        mModule.loadImage(path, imageView);
    }

    @Override
    public void loadData(int action, Object... parameter) {

    }

    @Override
    public void onCompleted(int action) {

    }
}
