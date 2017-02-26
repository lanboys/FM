package com.bing.lan.fm.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bing.lan.comm.app.BaseApplication;
import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.girl.GirlFragment;
import com.bing.lan.fm.ui.home.HomeFragment;
import com.bing.lan.fm.ui.mine.MineFragment;
import com.bing.lan.fm.ui.search.SearchActivity;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;
import com.squareup.otto.Subscribe;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView<IMainContract.IMainPresenter>,
        NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SearchAdapter.OnItemClickListener, SearchView.OnVoiceClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.searchView)
    SearchView mSearchView;

    private String[] mTabTitles;
    private int[] mTabImages;
    private FragmentManager mFragmentManager;

    private HomeFragment mHomeFragment;
    private GirlFragment mGirlFragment;
    private MineFragment mMineFragment;
    private SearchHistoryTable mHistoryDatabase;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件总线
        BaseApplication.sBus.unregister(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isImmersion() {
        return false;
    }

    @Override
    protected boolean isTranslucentStatus() {
        return true;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        // 获得Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        initTabData();

        setToolBar(mToolbar, "", false);

        initFab();

        initDrawerLayout();

        initNavigation();

        initSearchView();
    }

    @Override
    protected void readyStartPresenter() {
        //启动p层逻辑
        // mPresenter.onStart();

        //注册事件总线
        BaseApplication.sBus.register(this);
        jumpHomeFragment();
    }

    //otto测试
    @Subscribe
    public void onReceive(Integer is) {

        showToast("otto测试切换成功");
    }

    private void initTabData() {

        mTabTitles = AppUtil.getStrArr(R.array.main_tab_title);

        TypedArray ar = AppUtil.getAppRes().obtainTypedArray(R.array.main_tab_image);
        int len = ar.length();
        mTabImages = new int[len];
        for (int i = 0; i < len; i++)
            mTabImages[i] = ar.getResourceId(i, 0);
        ar.recycle();
    }

    private void initNavigation() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initSearchView() {

        mHistoryDatabase = new SearchHistoryTable(this);

        mSearchView.setHint("fm");
        mSearchView.setOnQueryTextListener(this);

        SearchAdapter searchAdapter = new SearchAdapter(this, new ArrayList<SearchItem>());
        searchAdapter.addOnItemClickListener(this);
        mSearchView.setAdapter(searchAdapter);

        mSearchView.setVoiceText("Set permission on Android 6.0+ !");
        mSearchView.setOnVoiceClickListener(this);

        mSearchView.setArrowOnly(false);
    }

    protected void goSearchActivity(String text) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra(SearchActivity.EXTRA_KEY_TEXT, text);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mHistoryDatabase.addItem(new SearchItem(query));
        goSearchActivity(query);
        mSearchView.close(false);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.textView_item_text);
        String query = textView.getText().toString();
        mHistoryDatabase.addItem(new SearchItem(query));
        goSearchActivity(query);
        mSearchView.close(false);
    }

    @Override
    public void onVoiceClick() {
        // permission

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            jumpHomeFragment();
        } else if (id == R.id.nav_girl) {
            jumpGirlFragment();
        } else if (id == R.id.nav_subscriber) {
            jumpSubscriberFragment();
        } else if (id == R.id.nav_mine) {
            jumpMineFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        closeDrawer();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_zxing_qrcode:
                //打开扫码界面
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivity(intent);
                // startActivityForResult(intent, REQUEST_CODE);
                // return true;
                break;
            case R.id.action_search:
                mSearchView.open(true, item);
                break;
            case R.id.action_day_night:
                switchNightMode();
                break;

            case R.id.action_test_otto:
                BaseApplication.sBus.post(Integer.parseInt("15"));
                showToast("测试otto");
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void jumpHomeFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance(mTabTitles[0]);
        }
        replaceFragment(mHomeFragment);
        updateTitle(mHomeFragment.getTitle());
    }

    private void jumpGirlFragment() {
        if (mGirlFragment == null) {
            mGirlFragment = GirlFragment.newInstance(mTabTitles[1]);
        }
        replaceFragment(mGirlFragment);
        updateTitle(mGirlFragment.getTitle());
    }

    private void jumpSubscriberFragment() {
        // if (mHomeFragment == null) {
        //     SubscriberFragment.newInstance(mTabTitles[2]);
        // }
        // replaceFragment(mHomeFragment);
        // updateTitle(mGirlFragment.getTitle());

    }

    private void jumpMineFragment() {
        if (mMineFragment == null) {
            mMineFragment = MineFragment.newInstance(mTabTitles[3]);
        }
        replaceFragment(mMineFragment);
        updateTitle(mMineFragment.getTitle());
    }

    /*关闭侧边导航*/
    private void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    /*判断导航是否打开*/
    private boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void replaceFragment(BaseFragment instance) {
        try {
            String hashCode = Integer.toString(instance.hashCode());

            BaseFragment addedFragment = (BaseFragment) getForehand();

            // start transaction
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            if (addedFragment != null) {
                addedFragment.stopUpdate();
                addedFragment.onPause();
                transaction.hide(addedFragment);
            }

            Fragment cachedFragment = mFragmentManager.findFragmentByTag(hashCode);
            if (cachedFragment == null) {
                cachedFragment = instance;
                //fragment 的hashCode是内存地址,作为tag存进去
                transaction.add(R.id.fragment_container, cachedFragment, hashCode);
            } else {
                cachedFragment.onResume();
                transaction.show(cachedFragment);
                ((IBaseFragmentContract.IBaseFragmentView) cachedFragment).reStartUpdate();
            }
            transaction.addToBackStack(hashCode);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*获得前台显示的Fragment实例*/
    private Fragment getForehand() {
        //返回堆栈的总数目。
        int count = mFragmentManager.getBackStackEntryCount();
        Fragment addedFragment = null;
        FragmentManager.BackStackEntry entryAt;
        if (count > 0) {
            //根据序号返回后台堆栈中的BackStackEntry对象，最底的序号为0。
            entryAt = mFragmentManager.getBackStackEntryAt(count - 1);
            if (entryAt != null) {
                String hashCode = entryAt.getName();//fragment tag
                addedFragment = mFragmentManager.findFragmentByTag(hashCode);
            }
        }
        return addedFragment;
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
            return;
        }

        // back to the home fragment or finish the activity
        Fragment forehand = getForehand();
        if (forehand == null || forehand.getClass() == HomeFragment.class) {
            createExitDialog().show();
        } else {
            jumpHomeFragment();
        }
    }

    private SweetAlertDialog createExitDialog() {

        return new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setCustomImage(R.drawable.exit_pic)
                .setTitleText(AppUtil.getString(R.string.exit_tips))
                .setCancelText(AppUtil.getString(R.string.ok_btn))
                .setConfirmText(AppUtil.getString(R.string.cancel_btn))
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        finish();
                        // sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    }
                });
    }

    @Override
    public void updateTitle(String title) {
        mToolbar.setTitle(title);
    }
}
