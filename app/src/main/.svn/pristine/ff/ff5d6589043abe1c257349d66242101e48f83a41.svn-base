package com.bing.lan.fm.ui.top;

import android.content.Intent;
import android.os.Bundle;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.fm.cons.Constants;

/**
 *
 */
public class TopFragment extends BaseFragment<ITopContract.ITopPresenter>
        implements ITopContract.ITopView {


    public static TopFragment newInstance(String title) {
        TopFragment fragment = new TopFragment();
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
