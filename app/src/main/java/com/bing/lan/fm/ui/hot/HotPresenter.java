package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.HotResult;
import com.bing.lan.fm.ui.hot.bean.HotResult1;
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
    public static final int LOAD_HOT_MAIN1 = 2;

    private static final int LOAD_COUNT = 20;
    private static final int LOAD_PAGE = 1;
    private static final int MAX_PAGE = -1;

    private int loadCompleted = 0;
    private List<HotInfoBean> mHotInfos = new ArrayList<>();

    @Override
    public void onStart(Object... params) {
        mModule.requestData(LOAD_HOT_MAIN, this);
        mModule.requestData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);
        mModule.requestData(LOAD_HOT_MAIN1, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {
            case LOAD_HOT_MAIN:
                handleHotData((HotResult) data);
                break;
            case LOAD_HOT_MAIN1:
                handleHotData1((HotResult1) data);
                break;
            case LOAD_GANK:
                mView.updateGirlViewPager((List<GankBean.ResultsBean>) data);
                break;
        }
    }

    private void handleHotData1(HotResult1 hotResult) {

        //猜你喜欢
        mHotInfos.add(0,hotResult.getGuess() );
        mHotInfos.add(hotResult.getDiscoveryColumns() );
        mView.updateRecyclerView(mHotInfos);
    }

    private void handleHotData(HotResult hotResult) {
        //小编推荐
        mHotInfos.add(hotResult.getEditorRecommendAlbums());
        //精品听单
        mHotInfos.add(hotResult.getSpecialColumn());
        mView.updateRecyclerView(mHotInfos);

        //轮播图
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
        super.onError(action, e);
        //下拉刷新,之前有数据,就不显示错误页面
        if (!mView.isHaveData()) {
            mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_ERROR);
        }
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.closeRefreshing();
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);

        loadCompleted++;
        log.d("onCompleted(): " + loadCompleted);

        if (loadCompleted == 2) {
            //全部加载成功才设置为有数据状态,否则再次可见时,自动重新加载数据
            mView.setHaveData(true);
        }
    }
}
