package com.bing.lan.fm.ui.category;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.ui.category.bean.CategoryListBean;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class CategoryPresenter extends
        BaseFragmentPresenter<ICategoryContract.ICategoryView, ICategoryContract.ICategoryModule>
        implements ICategoryContract.ICategoryPresenter {
    public static final int LOAD_CATEGORY = 0X05;
    @Override
    public void onStart(Object... params) {
          mModule.requestData(LOAD_CATEGORY,this);
            // mModule.loadData(LOAD_GANK, this, LOAD_COUNT, LOAD_PAGE);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_CATEGORY:
                mView.setRecyclerViewDatas(((CategoryListBean)data).getList());
             break;

        }
    }

    @Override
    public void onError(int action, Throwable e) {
        super.onError(action, e);
    }

    @Override
    public void onCompleted(int action) {
        super.onCompleted(action);
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);

        mView.setHaveData(true);


    }
}
