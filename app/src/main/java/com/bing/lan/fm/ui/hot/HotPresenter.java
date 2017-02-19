package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.HotResult;
import com.bing.lan.fm.ui.hot.bean.ListItemFocusImageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class HotPresenter extends
        BaseFragmentPresenter<IHotContract.IHotView, IHotContract.IHotModule>
        implements IHotContract.IHotPresenter {

    public static final int LOAD_GANK = 0;
    public static final int LOAD_HOT_MAIN = 1;


    private static final int LOAD_COUNT = 35;
    private static final int LOAD_PAGE = 1;
    private static final int MAX_PAGE = -1;

    @Override
    public void onStart(Object... params) {
        mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);
        mModule.loadData(LOAD_HOT_MAIN, this);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);

        switch (action) {

            case LOAD_HOT_MAIN:
                handleHotData((HotResult) data);
                break;
        }
    }

    private void handleHotData(HotResult hotResult) {
        List<HotInfoBean> hotInfos = new ArrayList<>();
        hotInfos.add(hotResult.getEditorRecommendAlbums());
        hotInfos.add(hotResult.getSpecialColumn());
        mView.updateRecyclerView(hotInfos);

        HotInfoBean<ListItemFocusImageBean> focusImages = hotResult.getFocusImages();
        List<ListItemFocusImageBean> list = focusImages.getList();
        List<String> imageUrls = new ArrayList<>();
        for (ListItemFocusImageBean listItemFocusImageBean : list) {
            imageUrls.add(listItemFocusImageBean.getPic());
        }
        mView.updateBanner(imageUrls);
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
