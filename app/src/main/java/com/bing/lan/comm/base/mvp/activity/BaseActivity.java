package com.bing.lan.comm.base.mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityPresenter;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityView;
import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.di.ActivityModule;
import com.bing.lan.comm.di.DaggerActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImmersionUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.SPUtil;
import com.bing.lan.fm.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;
import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:36
 */
public abstract class BaseActivity<T extends IBaseActivityPresenter>
        extends AppCompatActivity
        implements IBaseActivityView<T> {

    // protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);
    protected Unbinder mViewBind;

    @Inject
    protected LogUtil log;
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImmersion();
        //透明状态栏
        initTranslucentStatus();
        //初始化布局
        initWindowUI();
        //启动di
        startInject(getActivityComponent());
        //初始化View 和 数据
        initViewAndData(getIntent());
        //获取权限
        requestPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mViewBind != null) {
            mViewBind.unbind();
            mViewBind = null;
        }
        //解绑
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }

        AppUtil.MemoryLeakCheck(this);
    }

    protected abstract int getLayoutResId();

    protected abstract void startInject(ActivityComponent activityComponent);

    protected abstract void initViewAndData(Intent intent);

    /**
     * 权限请求成功时调用
     */
    protected abstract void readyStartPresenter();

    protected void initWindowUI() {
        //初始化布局
        setContentView(getLayoutResId());
        //绑定控件
        mViewBind = ButterKnife.bind(this);
    }

    public void requestPermissions() {
        // TODO: 2017/1/12 获取权限的操作
        readyStartPresenter();
    }

    /**
     * 日间和夜景模式切换
     */
    protected void switchNightMode() {
        boolean isNight = !SPUtil.getBoolean(AppConfig.DAY_NIGHT_MODE);
        setDefaultNightMode(isNight ? MODE_NIGHT_YES : MODE_NIGHT_NO);
        SPUtil.putBoolean(AppConfig.DAY_NIGHT_MODE, isNight);
        recreate();
    }

    /**
     * 判断当前的模式
     *
     * @return true表示夜景模式
     */
    protected boolean isNightMode() {
        return SPUtil.getBoolean(AppConfig.DAY_NIGHT_MODE);
    }

    /**
     * 请求状态栏透明
     */
    protected boolean isTranslucentStatus() {
        return true;
    }

    /**
     * 请求沉浸式
     */
    protected boolean isImmersion() {
        // isImmersion = true;
        return false;
    }

    private void initTranslucentStatus() {

        if (!isTranslucentStatus())
            return;
        ImmersionUtil.initTranslucentStatus(this);
    }

    private void initImmersion() {
        if (!isImmersion())
            return;
        //
        ImmersionUtil.initImmersionSmallApi19(this);
    }

    private void initImmersion(boolean hasFocus) {
        if (!isImmersion())
            return;
        if (hasFocus) {
            ImmersionUtil.initImmersion(this);
        }
    }

    /**
     * 获取焦点或失去焦点时调用
     *
     * @param hasFocus 获取焦点返回true,否则返回false
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initImmersion(hasFocus);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this, getIntent()))
                .build();
    }

    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish) {
        AppUtil.startActivity(this, clazz, isFinish);
        overridePendingTransition(R.anim.scale_alpht_in, R.anim.scale_alpht_out);
    }

    /**
     * 默认false
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false);
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(AppUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String msg) {
        //        new
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuId() == 0) {
            return super.onCreateOptionsMenu(menu);
        }
        getMenuInflater().inflate(getMenuId(), menu);
        return true;
    }

    /**
     * 加载图片
     */
    // protected void loadImage(Object path, ImageView imageView) {
    //     mPresenter.loadImage(path, imageView);
    // }

    protected int getMenuId() {
        return 0;
    }

    protected void setToolBar(Toolbar toolBar) {
        setToolBar(toolBar, null, true);
    }

    protected void setToolBar(Toolbar toolBar, String title) {
        setToolBar(toolBar, title, true);
    }

    protected void setToolBar(Toolbar toolBar, String title, boolean finishActivity) {
        if (title != null) {
            toolBar.setTitle(title);
        }
        setSupportActionBar(toolBar);
        // toolBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标
        toolBar.setTitleTextColor(Color.WHITE);
        if (finishActivity) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //将默认的 返回箭头 显示出来
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            //给箭头添加监听器
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }
}
