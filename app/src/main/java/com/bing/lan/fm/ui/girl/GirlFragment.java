package com.bing.lan.fm.ui.girl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.base.mvp.fragment.SampleFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.cons.Constants;
import com.bing.lan.fm.ui.gank.GankFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class GirlFragment extends BaseFragment<IGirlContract.IGirlPresenter>
        implements IGirlContract.IGirlView {

    @BindView(R.id.tab_girl)
    TabLayout mTabGirl;
    @BindView(R.id.view_pager_girl)
    ViewPager mViewPagerGirl;
    private String[] mGirlTabTitle;
    private List<Fragment> mFragments;

    public static GirlFragment newInstance(String title) {
        GirlFragment fragment = new GirlFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        fragment.setTitle(title);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected boolean isOpenLoadPager() {
        return false;
    }

    @Override
    protected void readyStartPresenter() {
    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {
        initData();
        initView();
    }

    private void initData() {
        mGirlTabTitle = AppUtil.getAppRes().getStringArray(R.array.girl_tab_title);
        mFragments = new ArrayList<>();

        mFragments.add(new GankFragment());
        mFragments.add(new SampleFragment());
        mFragments.add(new SampleFragment());
        mFragments.add(new SampleFragment());
    }

    private void initView() {
        mViewPagerGirl.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mGirlTabTitle[position];
            }
        });

        mTabGirl.setupWithViewPager(mViewPagerGirl);
    }
}
