package com.bing.lan.fm.ui.home;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.search.bean.SearchHintWordResult;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class HomeModule extends BaseFragmentModule
        implements IHomeContract.IHomeModule {

    @Override
    public void loadData(final int action, final IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<List<SearchHintWordResult.SearchWordBean>> observable = mApiService.getSearchHintWord()
                .filter(new Func1<SearchHintWordResult, Boolean>() {
                    @Override
                    public Boolean call(SearchHintWordResult searchWordResult) {
                        if (searchWordResult.getRet() == 0 && searchWordResult.getList().size() > 0) {
                            return true;
                        }
                        return null;
                    }
                })
                .map(new Func1<SearchHintWordResult, List<SearchHintWordResult.SearchWordBean>>() {
                    @Override
                    public List<SearchHintWordResult.SearchWordBean> call(SearchHintWordResult searchWordResult) {
                        return searchWordResult.getList();
                    }
                });

        subscribe(observable,
                action,
                listener,
                "搜索词汇");
    }
}
