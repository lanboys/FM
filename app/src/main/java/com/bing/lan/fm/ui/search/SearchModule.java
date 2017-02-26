package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.fm.ui.home.bean.SearchWordResult;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class SearchModule extends BaseActivityModule
        implements ISearchContract.ISearchModule {

    @Override
    public void releaseTask() {

    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
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

        mSubscriptions.put(action + "", subscribe(observable,
                action,
                listener,
                "搜索词汇"));
    }
}
