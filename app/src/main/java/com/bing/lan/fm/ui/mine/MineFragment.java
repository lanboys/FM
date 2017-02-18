package com.bing.lan.fm.ui.mine;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.fm.R;

/**
 *
 */
public class MineFragment extends BaseFragment<IMineContract.IMinePresenter>
        implements IMineContract.IMineView {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_list_view_refresh;

        // return 0;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent) {

    }

}
