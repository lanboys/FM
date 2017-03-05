package com.bing.lan.comm.base.mvp.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityPresenter;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityView;
import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.di.ActivityModule;
import com.bing.lan.comm.di.DaggerActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.DialogUtil;
import com.bing.lan.comm.utils.ImmersionUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.SPUtil;
import com.bing.lan.comm.utils.ToastUtil;
import com.bing.lan.fm.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

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

    private static final int BASE_PERMISSION_REQUEST_CODE = 0;
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

    /**
     * 默认false
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false, true);
    }

    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish, boolean isAnim) {
        AppUtil.startActivity(this, clazz, isFinish, false);
        if (isAnim) {
            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        }
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
        // Toast.makeText(AppUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String msg) {
        DialogUtil.showAlertDialog(this, msg);
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

    /*请求权限*/
    private void requestPermissions() {

        // 判断系统是不是大于等于M
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 获得权限字符串数组
            final String[] permissions = AppUtil.getStrArr(getPermissionArrId());
            //检查基本权限是否授权成功
            if (checkBasePermissions(permissions)) {
                requestPermissionSucceed();
            } else {
                //检查是否有被拒绝过的权限
                checkDeniedPermissions(permissions);
            }
        } else {
            requestPermissionSucceed();
        }
    }

    /**
     * 检查基本权限
     */
    private boolean checkBasePermissions(String[] permissions) {
        boolean result = true;
        for (String permission : permissions) {
            //PackageManager.PERMISSION_GRANTED 表示授权成功
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, permission)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 检查是否有被拒绝过的权限
     */
    private void checkDeniedPermissions(final String[] permissions) {
        List<String> shouldShowPermission = new ArrayList<>();
        for (String permission : permissions) {

            // false: 1.没有被拒绝过(第一次申请)
            //        2.被拒绝,并且在权限申请对话框中设置了,不再弹窗
            // true: 1.被拒绝过,弹窗向用户解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                shouldShowPermission.add(permission);
            }
        }
        log.i("checkDeniedPermissions():被拒绝过的权限: " + shouldShowPermission.toString());
        if (shouldShowPermission.size() > 0) {
            //被拒绝过需要解释
            showRationaleDialog(permissions);
        } else {
            //未被拒绝
            //永久拒绝(不弹窗了)
            requestPermissionsImpl(permissions);
        }
    }

    private void showRationaleDialog(final String[] permissions) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("授权温馨提示:")
                .setContentText("亲爱的用户,您好,麻烦您授权一下,不然我们没办法启动哦,谢谢您的配合")
                .setCancelText("取消")
                .setConfirmText("确定")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        requestPermissionsImpl(permissions);
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                //请求权限失败
                requestPermissionFailed(Arrays.asList(permissions));
            }
        }).show();
    }

    /**
     * 真正请求权限的操作
     */
    private void requestPermissionsImpl(final String[] permissions) {
        //请求权限
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                //弹系统请求权限对话框
                ActivityCompat.requestPermissions(BaseActivity.this, permissions, BASE_PERMISSION_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == BASE_PERMISSION_REQUEST_CODE) {
            List<String> failed = new ArrayList<>();
            // 检查权限请求结果
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                    failed.add(permissions[i]);
            }

            log.d("onRequestPermissionsResult():请求失败的权限 " + failed.toString());
            // 报告每个请求失败的权限
            if (!failed.isEmpty()) {
                requestPermissionFailed(failed);
                return;
            }
            // 权限请求成功
            requestPermissionSucceed();
        }
    }

    /**
     * 权限请求成功时调用
     */
    protected void requestPermissionSucceed() {
        readyStartPresenter();
    }

    /**
     * 权限请求失败时调用
     */
    protected void requestPermissionFailed(List<String> failedPermissions) {
        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("温馨提示:")
                .setContentText("亲爱的用户,您好,小M未得到您的授权,无法启动哦,再见..")
                .setConfirmText("确定")
                .setCancelText("去设置页面开启权限")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        finish();
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        AppUtil.detailApp(BaseActivity.this, getPackageName());
                        finish();

                    }
                }).show();
    }

    /**
     * 返回权限数组资源id
     */
    protected int getPermissionArrId() {
        return R.array.basic_permissions;
    }
}
