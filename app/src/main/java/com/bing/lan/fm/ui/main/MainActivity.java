package com.bing.lan.fm.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bing.lan.comm.app.BaseApplication;
import com.bing.lan.comm.base.mvp.activity.BaseMusicActivity;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.comm.utils.musicplay.MusicPlayDao;
import com.bing.lan.comm.utils.musicplay.MusicPlayer;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.girl.GirlFragment;
import com.bing.lan.fm.ui.home.HomeFragment;
import com.bing.lan.fm.ui.mine.MineFragment;
import com.bing.lan.fm.ui.music.MusicActivity;
import com.bing.lan.fm.ui.search.SearchActivity;
import com.bing.lan.fm.ui.splash1.SplashFragment;
import com.bing.lan.fm.ui.subscriber.SubscriberFragment;
import com.bing.lan.inke.yingke.IndexActivity;
import com.lapism.searchview.SearchAdapter;
import com.lapism.searchview.SearchHistoryTable;
import com.lapism.searchview.SearchItem;
import com.lapism.searchview.SearchView;
import com.squareup.otto.Subscribe;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

import static com.bing.lan.fm.R.id.cir_play1;
import static com.bing.lan.fm.R.id.cir_play2;
import static com.bing.lan.fm.ui.music.MusicActivity.MSG_HIDE_PLAY_BTN;

public class MainActivity extends BaseMusicActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView<IMainContract.IMainPresenter>,
        NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SearchAdapter.OnItemClickListener, SearchView.OnVoiceClickListener, View.OnClickListener {

    private static final int MSG_REMOVE_SPLASH_FRAGMENT = 333;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cir_play)
    FrameLayout mFab;
    @BindView(R.id.searchView)
    SearchView mSearchView;

    @BindView(R.id.cir_play1)
    CircleImageView mCirPlay1;
    @BindView(R.id.cir_play2)
    CircleImageView mCirPlay2;

    private String[] mTabTitles;
    private int[] mTabImages;
    private FragmentManager mFragmentManager;

    private HomeFragment mHomeFragment;
    private GirlFragment mGirlFragment;
    private MineFragment mMineFragment;
    private SearchHistoryTable mHistoryDatabase;
    // private MusicPlayer.ServiceToken mServiceToken;
    private SubscriberFragment mSubscriberFragment;
    private List<Music> mArrayList;
    private int mFirstPlayPos = 0;
    private long mAlbumId;
    private int rotation;
    private SplashFragment mSplashFragment;
    private ViewStub mViewStub;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销事件总线
        BaseApplication.sBus.unregister(this);

        // //    关闭音乐服务
        // MusicPlayer.unbindFromService(mServiceToken);
    }

    protected void initWindowUI() {
        //初始化布局
        setContentView(getLayoutResId());
        //绑定控件
        // mViewBind = ButterKnife.bind(this);
    }

    @Override
    protected void handlerMusicMainMessage(Message msg) {
        super.handlerMusicMainMessage(msg);

        switch (msg.what) {

            case MSG_HIDE_PLAY_BTN:
                boolean playing = MusicPlayer.isPlaying();
                mCirPlay2.setSelected(playing);
                if (playing) {
                    mCirPlay2.setVisibility(View.INVISIBLE);
                } else {
                    mCirPlay2.setVisibility(View.VISIBLE);
                }

                break;
            // case MSG_REMOVE_SPLASH_FRAGMENT:
            //     removeSplashFragment();
            //     break;
        }
    }

    @Override
    protected void updateUIDisplay() {
        if (mHomeFragment != null) {
            mHomeFragment.updateUIDisplay();
        }
        if (mGirlFragment != null) {
            mGirlFragment.updateUIDisplay();
        }
        if (mSubscriberFragment != null) {
            mSubscriberFragment.updateUIDisplay();
        }
        if (mMineFragment != null) {
            mMineFragment.updateUIDisplay();
        }

        rotation += 2;
        mCirPlay1.setRotation(rotation);

        boolean playing = MusicPlayer.isPlaying();
        mCirPlay2.setSelected(playing);
        mCirPlay2.setImageResource(playing ? R.drawable.icon_rec_preview_pause : R.drawable.icon_rec_preview_play);

        if (playing) {
            mMainHandler.sendEmptyMessageDelayed(MSG_HIDE_PLAY_BTN, 5 * 1000);
        } else {
            mCirPlay2.setVisibility(View.VISIBLE);
        }
    }

    public void queryMusicPlayList() {
        RealmResults<Music> musics = MusicPlayDao.queryAllMusicInfo();

        if (musics.size() > 0) {
            Music music = musics.get(0);
            mAlbumId = music.albumId;
        }
    }

    @Override
    protected void handlerOnReceive(Intent intent) {

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
    protected int getMenuId() {
        return R.menu.menu_main;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        // 获得Fragment管理器
        mFragmentManager = getSupportFragmentManager();
        // initTabData();
        //
        // setToolBar(mToolbar, "", false);
        //
        // initCirPlay();
        //
        // initDrawerLayout();
        //
        // initNavigation();
        //
        // initSearchView();
    }

    @Override
    protected void readyStartPresenter() {

        // mServiceToken = MusicPlayer.bindToService(this, new ServiceConnection() {
        //     @Override
        //     public void onServiceConnected(ComponentName name, IBinder service) {
        //
        //     }
        //
        //     @Override
        //     public void onServiceDisconnected(ComponentName name) {
        //
        //     }
        // });

        //启动p层逻辑
        // mPresenter.onStart();

        // //注册事件总线
        // BaseApplication.sBus.register(this);
        // jumpHomeFragment();

        jumpSplashFragment();

        queryMusicPlayList();

        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mViewStub != null) {
                    mViewStub.setVisibility(View.VISIBLE);
                    removeSplashFragment();
                }
            }
        }, 4000);

        initViewStub();
    }

    public void initViewStub() {

        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {

                mViewStub = (ViewStub) findViewById(R.id.app_bar_main_content);
                mViewStub.inflate();
                mViewStub.setVisibility(View.GONE);// INVISIBLE  会导致HomeFragment 不显示最上面内容????

                mViewBind = ButterKnife.bind(MainActivity.this);

                initTabData();

                setToolBar(mToolbar, "", false);

                initCirPlay();

                initDrawerLayout();

                initNavigation();

                initSearchView();

                jumpHomeFragment();
            }
        });

        //注册事件总线
        BaseApplication.sBus.register(this);
        // jumpSplashFragment();

        queryMusicPlayList();
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

    private void initCirPlay() {

        mCirPlay1.setOnClickListener(this);
        mCirPlay2.setOnClickListener(this);
    }

    private void initSearchView() {

        mHistoryDatabase = new SearchHistoryTable(this);

        mSearchView.setHint("搜你想搜的..");
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
        //搜索框的声音搜索

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            jumpHomeFragment();
        } else if (id == R.id.nav_inke) {
            jumpInke();
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

            // case R.id.action_test_otto:
            //     BaseApplication.sBus.post(Integer.parseInt("15"));
            //     showToast("测试otto");
            //     break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void jumpInke() {
        Intent intent1 = new Intent(this, IndexActivity.class);
        startActivity(intent1);
    }

    private void jumpHomeFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = HomeFragment.newInstance(mTabTitles[0]);
        }
        replaceFragment(mHomeFragment);
        updateTitle(mHomeFragment.getTitle());
    }

    private void jumpSplashFragment() {
        if (mSplashFragment == null) {
            mSplashFragment = SplashFragment.newInstance("");

            // start transaction
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            // mSplashFragment = (SplashFragment) mFragmentManager.findFragmentById(R.id.fragment_splash);
            transaction.add(R.id.drawer_layout, mSplashFragment);
            transaction.commit();
        }
    }

    private void removeSplashFragment() {
        // start transaction
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(mSplashFragment);
        transaction.commit();
        mSplashFragment = null;
    }

    private void jumpGirlFragment() {
        if (mGirlFragment == null) {
            mGirlFragment = GirlFragment.newInstance(mTabTitles[1]);
        }
        replaceFragment(mGirlFragment);
        updateTitle(mGirlFragment.getTitle());
    }

    private void jumpSubscriberFragment() {
        if (mSubscriberFragment == null) {
            mSubscriberFragment = SubscriberFragment.newInstance(mTabTitles[2]);
        }
        replaceFragment(mSubscriberFragment);
        updateTitle(mSubscriberFragment.getTitle());
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
            // super.onBackPressed();
            createExitDialog().show();
        } else {
            jumpHomeFragment();
        }
    }

    private AlertDialog createExitDialog() {

        // return new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
        //         .setCustomImage(R.drawable.exit_pic)
        //         .setTitleText(AppUtil.getString(R.string.exit_tips))
        //         .setCancelText(AppUtil.getString(R.string.ok_btn))
        //         .setConfirmText(AppUtil.getString(R.string.cancel_btn))
        //         .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
        //             @Override
        //             public void onClick(SweetAlertDialog sDialog) {
        //                 sDialog.dismissWithAnimation();
        //             }
        //         }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
        //             @Override
        //             public void onClick(SweetAlertDialog sweetAlertDialog) {
        //                 sweetAlertDialog.dismissWithAnimation();
        //                 finish();
        //                 // sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        //             }
        //         });

        TextView view = new TextView(this);
        view.setText(R.string.exit_tips);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                20, AppUtil.getDisplayMetrics());
        view.setPadding(padding, padding, padding, padding);
        // view.setTextColor(AppUtil.getColor(R.color.itemFontColor));
        view.setTextSize(16);
        return new AlertDialog.Builder(this)
                // .setTitle(AppUtil.getString(R.string.exit_tips))
                .setView(view)
                .setNegativeButton(AppUtil.getString(R.string.ok_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismissDialog();
                        finish();
                    }
                })
                .setPositiveButton(AppUtil.getString(R.string.cancel_btn),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dismissDialog();
                            }
                        }).create();
    }

    @Override
    public void updateTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case cir_play1:
            case cir_play2:
                log.d("onClick(): 点击了圆形按钮");
                // playOrPause();

                Music music = MusicPlayer.getCurrentPlayMusic();

                if (MusicPlayer.isPlaying()) {
                    //正在播放
                    MusicActivity.startMusicActivity(v.getContext(), false,
                            0, 0, null);
                } else if (music != null && music.albumId != 0) {
                    // 暂停播放
                    MusicActivity.startMusicActivity(v.getContext(), false,
                            0, 0, null);
                } else {
                    //不在播放,且之前未播放
                    MusicActivity.startMusicActivity(v.getContext(), false,
                            mAlbumId, 0, null);
                }

                break;
        }
    }
}
