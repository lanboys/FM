package com.bing.lan.fm.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bing.lan.comm.app.BaseApplication;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.view.ResideMenu.ResideMenu;
import com.bing.lan.comm.view.ResideMenu.ResideMenuItem;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.girl.GirlFragment;
import com.bing.lan.fm.ui.home.HomeFragment;
import com.squareup.otto.Subscribe;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView<IMainContract.IMainPresenter> {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    private Class[] mFragmentClazz;
    private String[] mTabTitles;
    private int[] mTabImages;
    private LayoutInflater mInflater;
    private ResideMenu mResideMenu;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    protected boolean isImmersion() {
        return false;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {



        initTabData();
        initFragmentTabHost();
        //借用沉浸式来实现透明状态栏
        if (Build.VERSION.SDK_INT <= 16) {
            initResideMenu();
        }
    }

    @Override
    protected void readyStartPresenter() {
        //启动p层逻辑
        // mPresenter.onStart();

        //注册事件总线
        BaseApplication.sBus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件总线
        BaseApplication.sBus.unregister(this);
    }

    @Subscribe
    public void onReceive(Integer is) {
        switchNightMode();
        showToast("切换成功");
        log.d("onReceive(): " + is);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(mTabTitles[0]);
            View loadPage = fragment.getContentView();
            View viewById = loadPage.findViewById(R.id.view_pager);
            if (mResideMenu != null && viewById != null) {
                mResideMenu.addIgnoredView(viewById);
            }
        }
    }

    public void initResideMenu() {
        // attach to current activity;
        mResideMenu = new ResideMenu(this);
        mResideMenu.setBackground(R.drawable.menu_background);
        mResideMenu.attachToActivity(this);
        mResideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        // resideMenu.addIgnoredView(mFrameLayout);
        // resideMenu.addIgnoredView(mTabLayout);

        // create menu items;
        String titles[] = {"Home", "Profile", "Calendar", "Settings"};
        int icon[] = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

        for (int i = 0; i < titles.length; i++) {
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("我被点击了");
                }
            });
            mResideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }

        // TODO: 2017/2/15   用 rxbus / eventbus 实现
        mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT); // or ResideMenu.DIRECTION_RIGHT
        mResideMenu.closeMenu();
    }

    // @Override
    // public boolean dispatchTouchEvent(MotionEvent ev) {
    //     return mResideMenu.dispatchTouchEvent(ev);
    // }



    private void initTabData() {
        mFragmentClazz = new Class[]{
                HomeFragment.class,
                GirlFragment.class,
                HomeFragment.class,
                HomeFragment.class,
        };

        mTabTitles = AppUtil.getStrArr(R.array.main_tab_title);

        TypedArray ar = AppUtil.getAppRes().obtainTypedArray(R.array.main_tab_image);
        int len = ar.length();
        mTabImages = new int[len];
        for (int i = 0; i < len; i++)
            mTabImages[i] = ar.getResourceId(i, 0);
        ar.recycle();

        mInflater = LayoutInflater.from(this);
    }

    private void initFragmentTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragmentClazz.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, mFragmentClazz[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
    }

    private View getTabView(int index) {
        View view = mInflater.inflate(R.layout.tab_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);
        int padding = AppUtil.dip2px(50);
        int padding2 = AppUtil.dip2px(4);
        if (index == 1) {
            view.setPadding(padding2, padding2, padding, padding2);
        }
        if (index == 2) {
            view.setPadding(padding, padding2, padding2, padding2);
        }
        image.setImageResource(mTabImages[index]);
        title.setText(mTabTitles[index]);
        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
