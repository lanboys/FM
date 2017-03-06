package com.bing.lan.fm.ui.recommend;

import android.content.Intent;
import android.os.Bundle;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.fm.cons.Constants;

/**
 *
 */
public class RecommendFragment extends BaseFragment<IRecommendContract.IRecommendPresenter>
        implements IRecommendContract.IRecommendView {

    public static RecommendFragment newInstance(String title) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {

    }
}
