package com.bing.lan.fm.ui.category;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.category.bean.CategoryListBean;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.category.CategoryPresenter.LOAD_CATEGORY;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class CategoryModule extends BaseFragmentModule
        implements ICategoryContract.ICategoryModule {

                /*---------这里是真正进行数据的地方-----------*/
    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_CATEGORY  :
                loadCategory(action, listener);
                break;
        }
    }
    private void loadCategory(int action, IBaseContract.OnDataChangerListener listener) {
        //通过Rxjava进行网络的请求

        Observable<CategoryListBean> observable = mApiService.getCategory()
                .filter(new Func1<CategoryListBean, Boolean>() {
            @Override
            public Boolean call(CategoryListBean categoryListBean) {
                return categoryListBean.getRet()==0;
            }
        });
        subscribe(observable, action, listener, "分类页面");

    }
}
