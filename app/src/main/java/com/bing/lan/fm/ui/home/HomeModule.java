package com.bing.lan.fm.ui.home;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.home.bean.SearchWordResult;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class HomeModule extends BaseFragmentModule
        implements IHomeContract.IHomeModule {


    @Override
    public void loadData(final int action, final IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<List<SearchWordResult.SearchWordBean>> observable = mApiService.getHomeSearchWord()
                .filter(new Func1<SearchWordResult, Boolean>() {
                    @Override
                    public Boolean call(SearchWordResult searchWordResult) {
                        if (searchWordResult.getRet() == 0 && searchWordResult.getList().size() > 0) {
                            return true;
                        }
                        return null;
                    }
                })
                .map(new Func1<SearchWordResult, List<SearchWordResult.SearchWordBean>>() {
                    @Override
                    public List<SearchWordResult.SearchWordBean> call(SearchWordResult searchWordResult) {
                        return searchWordResult.getList();
                    }
                });

        Subscription subscribe = subscribe(observable,
                action,
                listener,
                "搜索词汇");

    }
}
