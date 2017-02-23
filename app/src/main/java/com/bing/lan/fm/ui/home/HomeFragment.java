package com.bing.lan.fm.ui.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bing.lan.comm.app.BaseApplication;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.base.mvp.fragment.SampleFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.HotFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends BaseFragment<IHomeContract.IHomePresenter>
        implements IHomeContract.IHomeView {


    @BindView(R.id.user_image)
    CircleImageView mUserImage;
    @BindView(R.id.tv_search_word)
    TextView mSearchWordTv;
    @BindView(R.id.search)
    ImageButton mSearch;
    @BindView(R.id.zxing_qrcode)
    ImageButton mQrcode;
    @BindView(R.id.ib_download)
    ImageView mIbDownload;
    @BindView(R.id.tv_downloading)
    TextView mTvDownloading;
    @BindView(R.id.download_layout)
    RelativeLayout mDownloadLayout;
    @BindView(R.id.ib_play_history)
    ImageView mIbPlayHistory;
    @BindView(R.id.history_layout)
    RelativeLayout mHistoryLayout;

    @BindView(R.id.rl_top_bar)
    RelativeLayout mRlTopBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_home)
    TabLayout mTabHome;
    @BindView(R.id.view_pager_home)
    ViewPager mViewPagerHome;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    protected boolean isOpenLoadPager() {
        return false;
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent) {
        //初始化推荐/热门/等fragment
        initFragment();
    }

    private void initFragment() {
        List<Fragment> fragments = new ArrayList<>();
        SampleFragment introduceFragment = new SampleFragment();
        introduceFragment.setAllowEnterTransitionOverlap(true);

        fragments.add(introduceFragment);
        fragments.add(new HotFragment());
        fragments.add(new SampleFragment());
        fragments.add(new SampleFragment());
        fragments.add(new SampleFragment());

        initTab(fragments);
    }

    private void initTab(final List<Fragment> fragments) {
        final List<String> titles = Arrays.asList(AppUtil.getStrArr(R.array.home_tab_title));
        // PagerModelManager manager = new PagerModelManager();
        // manager.addCommonFragment(fragments, titles);
        // ModelPagerAdapter adapter = new ModelPagerAdapter(getChildFragmentManager(), manager);
        //
        // mViewPager.setAdapter(adapter);
        // mViewPager.fixScrollSpeed();
        // // just set viewPager
        // springIndicator.setViewPager(mViewPager);

        mViewPagerHome.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });

        mTabHome.setupWithViewPager(mViewPagerHome);
    }

    public void updateSearchWord(String searchWord) {
        mSearchWordTv.setText(searchWord);
    }

    @OnClick({R.id.user_image, R.id.zxing_qrcode, R.id.tv_search_word, R.id.search, R.id.ib_download, R.id.tv_downloading, R.id.download_layout, R.id.ib_play_history, R.id.history_layout, R.id.rl_top_bar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_image:
                showToast("我被点击了,此时应该打开抽屉");
                BaseApplication.sBus.post(Integer.parseInt("15"));
                break;
            case R.id.zxing_qrcode:
                //打开扫码界面
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
                // startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.search:
                break;
            case R.id.tv_search_word:
                break;
            case R.id.ib_download:
                break;
            case R.id.tv_downloading:
                break;
            case R.id.download_layout:
                break;
            case R.id.ib_play_history:
                break;
            case R.id.history_layout:
                break;
            case R.id.rl_top_bar:

                break;
        }
    }

}

