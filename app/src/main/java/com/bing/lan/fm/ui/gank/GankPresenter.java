package com.bing.lan.fm.ui.gank;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.gank.bean.GankBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class GankPresenter extends
        BaseFragmentPresenter<IGankContract.IGankView, IGankContract.IGankModule>
        implements IGankContract.IGankPresenter {

    private static final int LOAD_GANK = 0;
    private static final int LOAD__MORE_GANK = 1;
    private static final int LOAD_COUNT = 35;
    private int mNextLoadpager = 1;

    @Override
    public void onStart(Object... params) {
        updateGankData();
    }

    public void updateGankData() {
        mModule.requestData(LOAD_GANK, this, LOAD_COUNT, 1);
    }

    public void loadMoreGankData() {
        mModule.requestData(LOAD__MORE_GANK, this, LOAD_COUNT, mNextLoadpager);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_GANK:
                mNextLoadpager++;
                mView.updateGank((List<GankBean.ResultsBean>) data);
                break;
            case LOAD__MORE_GANK:
                mNextLoadpager++;
                mView.loadMoreGank((List<GankBean.ResultsBean>) data);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        if (!mView.isHaveData()) {
            mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_ERROR);
        }
        mView.showToast("加载数据失败");
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.setHaveData(true);
        mView.closeRefeshing();
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
    }
}
