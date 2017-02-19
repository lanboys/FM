package com.bing.lan.fm.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.main.MainActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<ISplashContract.ISplashPresenter>
        implements ISplashContract.ISplashView<ISplashContract.ISplashPresenter> {

    @BindView(R.id.splash_container)
    RelativeLayout mSplashContainer;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected boolean isImmersion() {
        return true;
    }

    @Override
    protected void initViewAndData(Intent intent) {

    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    public void startAnimation() {
        mSplashContainer.animate().alpha(1.0f).setDuration(3000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mPresenter.animationFinished();
            }
        });
    }

    public void animationFinished() {
        startActivity(MainActivity.class, true);
    }
}
