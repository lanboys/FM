package com.bing.lan.fm.ui.categorydetails;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.fm.ui.categorydetails.bean.CategoryDeitailsBean;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.categorydetails.CategoryDetailPresenter.LOAD_TITLE;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class CategoryDetailModule extends BaseActivityModule
        implements ICategoryDetailContract.ICategoryDetailModule {


    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_TITLE:
                loadCategoryDeitailTitle(action, listener, parameter);
                break;
        }
    }

    private void loadCategoryDeitailTitle(int action, IBaseContract.OnDataChangerListener
            listener, Object[] parameter) {
        Observable<CategoryDeitailsBean> observable = mApiService.getCategoryDeitails((int)parameter[0],
                (String) parameter[1])
                .filter(new Func1<CategoryDeitailsBean, Boolean>() {
                    @Override
                    public Boolean call(CategoryDeitailsBean categoryListBean) {
                        return categoryListBean.getRet()==0;
                    }
                });
        subscribe(observable, action, listener, "分类详情的页面");
    }
}
