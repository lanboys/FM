package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.fm.ui.search.bean.SearchHintWordResult;
import com.bing.lan.fm.ui.search.bean.SearchHotWordResult;
import com.bing.lan.fm.ui.search.bean.SearchResult;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.search.SearchPresenter.LOAD_SEARCH_HINT_WORD;
import static com.bing.lan.fm.ui.search.SearchPresenter.LOAD_SEARCH_HOT_WORD;
import static com.bing.lan.fm.ui.search.SearchPresenter.LOAD_SEARCH_RESULT;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class SearchModule extends BaseActivityModule
        implements ISearchContract.ISearchModule {

    public static String getSearchHotWordUrl() {
        return "http://search.ximalaya.com/hotWord?device=iPhone&size=45&version=5.4.81";
    }

    public static String getSearchResultUrl(String word) {
        return "http://search.ximalaya.com/front/v1?core=all&device=iPhone&is_paid=true&kw=" +
                word + "&live=true&page=1&paidFilter=false&rows=3&spellchecker=true&version=5.4.81";
    }

    //

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        switch (action) {
            case LOAD_SEARCH_HINT_WORD:
                loadSearchHintWord(action, listener);
                break;
            case LOAD_SEARCH_HOT_WORD:
                loadSearchHotWord(action, listener);
                break;
            case LOAD_SEARCH_RESULT:
                loadSearchResult(action, listener, (String) parameter[0]);
                break;
        }
    }

    private void loadSearchResult(int action, IBaseContract.OnDataChangerListener listener, String s) {
        Observable<SearchResult> searchResult = mApiService.getSearchResult(getSearchResultUrl(s));
        subscribe(searchResult, action, listener, "搜索结果");
    }

    private void loadSearchHotWord(int action, IBaseContract.OnDataChangerListener listener) {
        Observable<List<SearchHotWordResult.HotWordListBean>> observable = mApiService.getSearchHotWord(getSearchHotWordUrl())
                .filter(new Func1<SearchHotWordResult, Boolean>() {
                    @Override
                    public Boolean call(SearchHotWordResult searchHotWordResult) {
                        return (searchHotWordResult.getHotWordList().size() > 0);
                    }
                })
                .map(new Func1<SearchHotWordResult, List<SearchHotWordResult.HotWordListBean>>() {
                    @Override
                    public List<SearchHotWordResult.HotWordListBean> call(SearchHotWordResult searchHotWordResult) {
                        return searchHotWordResult.getHotWordList();
                    }
                });
        subscribe(observable, action, listener, "搜索热词");
    }

    private void loadSearchHintWord(int action, IBaseContract.OnDataChangerListener listener) {
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

        subscribe(observable, action, listener, "搜索hint");
    }
}
