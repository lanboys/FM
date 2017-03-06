package com.bing.lan.fm.ui.recommend;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IRecommendContract {

    interface IRecommendView extends IBaseFragmentContract.IBaseFragmentView<IRecommendPresenter> {
    }

    interface IRecommendPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IRecommendView, IRecommendModule> {
    }

    interface IRecommendModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
