package com.bing.lan.fm.ui.recommend;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.recommend.bean.RecBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class RecommendPresenter extends
        BaseFragmentPresenter<IRecommendContract.IRecommendView, IRecommendContract.IRecommendModule>
        implements IRecommendContract.IRecommendPresenter {

    public static final int LOAD_RECOMD_MAIN = 1;
    private List<HotInfoBean> mHotInfos = new ArrayList<>(50);
    private boolean isFinishHot = false;
    private boolean isFinishHot1 = false;

    @Override
    public void onStart(Object... params) {
        isFinishHot = false;
        isFinishHot1 = false;
        mHotInfos.clear();

        Random random = new Random();
        int nextInt = random.nextInt(15);

        mModule.requestData(LOAD_RECOMD_MAIN, this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_RECOMD_MAIN:
                mView.dataRec((RecBean) data);
                break;
        }
    }

    /* //返回处理的结果
     private void handleRecData(RecBean data) {

     }
 */
    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
        mView.closeRefreshing();
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);

        mView.closeRefreshing();
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        mView.setHaveData(true);
    }
}
