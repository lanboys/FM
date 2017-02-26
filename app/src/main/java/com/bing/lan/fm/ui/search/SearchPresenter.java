package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.fm.ui.search.bean.SearchHintWordResult;
import com.bing.lan.fm.ui.search.bean.SearchHotWordResult;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class SearchPresenter
        extends BaseActivityPresenter<ISearchContract.ISearchView, ISearchContract.ISearchModule>
        implements ISearchContract.ISearchPresenter {

    public static final int LOAD_SEARCH_HINT_WORD = 0;
    public static final int LOAD_SEARCH_HOT_WORD = 1;
    public static final int LOAD_SEARCH_RESULT = 2;

    @Override
    public void onStart(Object... params) {
        loadData(LOAD_SEARCH_HINT_WORD);
        loadData(LOAD_SEARCH_HOT_WORD);
    }

    @Override
    public void loadData(int action, Object... parameter) {
        switch (action) {
            case LOAD_SEARCH_HINT_WORD:
                mModule.requestData(action, this);
                break;
            case LOAD_SEARCH_HOT_WORD:
                mModule.requestData(action, this);
                break;
            case LOAD_SEARCH_RESULT:
                mModule.requestData(action, this,parameter);
                break;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_SEARCH_HINT_WORD:
                List<SearchHintWordResult.SearchWordBean> searchWordBeans = (List<SearchHintWordResult.SearchWordBean>) data;
                String guideWord = searchWordBeans.get(0).getGuideWord();
                mView.updateSearchWord(guideWord);
                break;

            case LOAD_SEARCH_HOT_WORD:
                mView.updateFlowContainer2((List<SearchHotWordResult.HotWordListBean>) data);
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
