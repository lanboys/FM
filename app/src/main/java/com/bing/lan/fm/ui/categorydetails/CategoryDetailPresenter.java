package com.bing.lan.fm.ui.categorydetails;


import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.fm.ui.categorydetails.bean.CategoryDeitailsBean;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class CategoryDetailPresenter
        extends BaseActivityPresenter<ICategoryDetailContract.ICategoryDetailView, ICategoryDetailContract.ICategoryDetailModule>
        implements ICategoryDetailContract.ICategoryDetailPresenter {
    public static final int LOAD_TITLE = 0X001;

    @Override
    public void onStart(Object... params) {
        //传递了id过来
        requestData(LOAD_TITLE, params);

    }


    @Override
    public void requestData(int action, Object... parameter) {
        switch (action) {
            case LOAD_TITLE:
                mModule.requestData(action, this, parameter);

        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_TITLE:
                mView.setCategoryTitles((CategoryDeitailsBean) data);
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
    }
}
