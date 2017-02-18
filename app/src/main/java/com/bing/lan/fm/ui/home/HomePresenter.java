package com.bing.lan.fm.ui.home;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.fm.ui.home.bean.SearchWordResult;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class HomePresenter extends
        BaseFragmentPresenter<IHomeContract.IHomeView, IHomeContract.IHomeModule>
        implements IHomeContract.IHomePresenter {

    public static final int LOAD_SEARCH_WORD = 1;

    @Override
    public void onStart(Object... params) {

        mModule.loadData(LOAD_SEARCH_WORD, this);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void onSuccess(int action, Object data) {

        switch (action) {

            case LOAD_SEARCH_WORD:
              List<SearchWordResult.SearchWordBean>  searchWordBeans = (List<SearchWordResult.SearchWordBean>) data;
                String guideWord = searchWordBeans.get(0).getGuideWord();

                mView.updateSearchWord(guideWord);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
