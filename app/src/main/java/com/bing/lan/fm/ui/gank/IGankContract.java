package com.bing.lan.fm.ui.gank;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.fm.ui.gank.bean.GankBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IGankContract {

    interface IGankView extends IBaseFragmentContract.IBaseFragmentView<IGankPresenter> {

        void updateGank(List<GankBean.ResultsBean> data);
          void loadMoreGank(List<GankBean.ResultsBean> data);
        void closeRefeshing();
    }

    interface IGankPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IGankView, IGankModule> {

        void loadMoreGankData();

        void updateGankData();
    }

    interface IGankModule extends IBaseFragmentContract.IBaseFragmentModule {

    }
}
