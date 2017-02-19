package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IHotContract {

    interface IHotView extends IBaseFragmentContract.IBaseFragmentView<IHotPresenter> {

        void updateGirlViewPager(List<GankBean.ResultsBean> data);

        void updateRecyclerView(List<HotInfoBean> data);

        void updateBanner(List<String> imageUrls);

        void closeRefeshing();
    }

    interface IHotPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IHotView, IHotModule> {

    }

    interface IHotModule extends IBaseFragmentContract.IBaseFragmentModule {

    }
}
