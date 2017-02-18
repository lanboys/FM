package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IHotContract {

    interface IHotView extends IBaseFragmentContract.IBaseFragmentView<IHotPresenter> {

        void updateRecyclerView(List<HotInfoBean> data);
          void updateBanner(List<String> imageUrls);}

    interface IHotPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IHotView, IHotModule> {
    }

    interface IHotModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
