package com.bing.lan.comm.base.mvp.fragment.test;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;

/**
 *
 */
public class FragTestFragment extends BaseFragment<IFragTestContract.IFragTestPresenter>
        implements IFragTestContract.IFragTestView {

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        //        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

    }


    @Override
    protected void initViewAndData(Intent intent) {

    }
}
