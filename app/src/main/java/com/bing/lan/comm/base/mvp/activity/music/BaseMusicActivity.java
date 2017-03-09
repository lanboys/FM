package com.bing.lan.comm.base.mvp.activity.music;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;

public class BaseMusicActivity extends BaseActivity<IBaseMusicContract.IBaseMusicPresenter>
        implements IBaseMusicContract.IBaseMusicView {

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
