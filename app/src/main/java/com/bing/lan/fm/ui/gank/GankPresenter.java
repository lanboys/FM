package com.bing.lan.fm.ui.gank;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
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
    private static final int LOAD_COUNT = 15;
    private static final int LOAD_PAGE = 1;
    private static final int MAX_PAGE = -1;

    @Override
    public void onStart(Object... params) {

            mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_GANK:
                mView.updateGank((List<GankBean.ResultsBean>) data);

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
