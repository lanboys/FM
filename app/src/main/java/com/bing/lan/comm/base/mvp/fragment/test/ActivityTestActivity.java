package com.bing.lan.comm.base.mvp.fragment.test;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

public class ActivityTestActivity extends BaseActivity<IActivityTestContract.IActivityTestPresenter>
        implements IActivityTestContract.IActivityTestView {

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        // activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {

    }

    @Override
    protected void readyStartPresenter() {

    }
}
