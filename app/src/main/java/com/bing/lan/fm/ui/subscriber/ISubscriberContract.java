package com.bing.lan.fm.ui.subscriber;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface ISubscriberContract {

    interface ISubscriberView extends IBaseFragmentContract.IBaseFragmentView<ISubscriberPresenter> {
    }

    interface ISubscriberPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<ISubscriberView, ISubscriberModule> {
    }

    interface ISubscriberModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
