package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.bean.HotResult;
import com.bing.lan.fm.ui.hot.bean.HotResult1;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;
import com.bing.lan.fm.ui.hot.bean.ListItemFocusImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private static final int LOAD_COUNT = 30;
    private static final int LOAD_PAGE = 4;
    private static final int MAX_PAGE = -1;

    private int loadCompleted = 0;
    private List<HotInfoBean> mHotInfos = new ArrayList<>();
    private boolean isFinishHot = false;
    private boolean isFinishHot1 = false;

    @Override
    public void onStart(Object... params) {
        isFinishHot = false;
        isFinishHot1 = false;
        mHotInfos.clear();

        Random random = new Random();
        int nextInt = random.nextInt(15);

        mModule.requestData(LOAD_HOT_MAIN, this);
        mModule.requestData(LOAD_GANK, this, LOAD_COUNT, nextInt + 1);
        mModule.requestData(LOAD_HOT_MAIN1, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {
            case LOAD_HOT_MAIN:
                handleHotData((HotResult) data);
                isFinishHot = true;
                break;
            case LOAD_HOT_MAIN1:
                handleHotData1((HotResult1) data);
                isFinishHot1 = true;
                break;
            case LOAD_GANK:
                mView.updateGirlViewPager((List<GankBean.ResultsBean>) data);
                break;
        }
    }

    private void handleHotData1(HotResult1 hotResult) {

        //横向recycleView
        mHotInfos.add(0, hotResult.getDiscoveryColumns());
        //猜你喜欢
        mHotInfos.add(1, hotResult.getGuess());
        //精品
        mHotInfos.add(2, hotResult.getPaidArea());
        //听广州
        mHotInfos.add(hotResult.getCityColumn());
        //听人文等
        List<HotInfoBean<ListItemEditorBean>> list = hotResult.getHotRecommends().getList();
        mHotInfos.addAll(list);
        //付费会员
        mHotInfos.add(hotResult.getMember());
    }

    private void handleHotData(HotResult hotResult) {
        //小编推荐
        mHotInfos.add( hotResult.getEditorRecommendAlbums());
        //精品听单
        mHotInfos.add( hotResult.getSpecialColumn());

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

        if (isFinishHot && isFinishHot1) {
            mView.updateRecyclerView(mHotInfos);
        }

        loadCompleted++;
        log.d("onCompleted(): " + loadCompleted);

        if (loadCompleted == 2) {
            //全部加载成功才设置为有数据状态,否则再次可见时,自动重新加载数据
            mView.setHaveData(true);
        }
    }
}
