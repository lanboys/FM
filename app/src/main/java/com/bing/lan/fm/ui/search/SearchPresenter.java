package com.bing.lan.fm.ui.search;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.fm.ui.home.bean.SearchWordResult;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class SearchPresenter
        extends BaseActivityPresenter<ISearchContract.ISearchView, ISearchContract.ISearchModule>
        implements ISearchContract.ISearchPresenter {
    public static final int LOAD_SEARCH_WORD = 1;

    @Override
    public void onStart(Object... params) {
        mModule.requestData(LOAD_SEARCH_WORD, this);

    }


    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_SEARCH_WORD:
                List<SearchWordResult.SearchWordBean> searchWordBeans = (List<SearchWordResult.SearchWordBean>) data;
                String guideWord = searchWordBeans.get(0).getGuideWord();

                mView.updateSearchWord(guideWord);
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
