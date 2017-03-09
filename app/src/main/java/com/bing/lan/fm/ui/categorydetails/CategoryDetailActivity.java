package com.bing.lan.fm.ui.categorydetails;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.category.bean.CategoryListBean;
import com.bing.lan.fm.ui.categorydetails.adapter.FragmentAdapter;
import com.bing.lan.fm.ui.categorydetails.bean.CategoryDeitailsBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends BaseActivity<ICategoryDetailContract.ICategoryDetailPresenter>
        implements ICategoryDetailContract.ICategoryDetailView {
    public static final String CATEGORYDETAILS_ITEM = "categorydetails_item";
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CategoryListBean.ListBean mListBeanItem;
    private CategoryDeitailsBean mTitleDatas;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_category;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //拿到相应的从上一级fragment传过来的数据
        Intent intentFromFragment = this.getIntent();
        mListBeanItem = (CategoryListBean.ListBean) intentFromFragment.getSerializableExtra(CATEGORYDETAILS_ITEM);
        //获取网络的数据.
    }

    private void initViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        //设置需要的数据,遍历集合拿到所有的bean
        List<CategoryDeitailsBean.KeywordsBean> keywords = mTitleDatas.getKeywords();
        if (keywords != null) {
            for (int i = 0; i < keywords.size(); i++) {
                CategoryDeitailsBean.KeywordsBean keywordsBean = keywords.get(i);
                titles.add(keywordsBean.getKeywordName());
            }
        }
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            // TODO:   如果是第一个,那就添加为第一个Fragment,这个样式和其他的都是不一样的
            






            fragments.add(new ListFragment());
        }
        FragmentAdapter mFragmentAdapteradapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapteradapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapteradapter);
    }


    @Override
    protected void readyStartPresenter() {
        //进行网络数据的加载
        mPresenter.onStart(mListBeanItem.getId(), mListBeanItem.getContentType());

    }

    @Override
    public void setCategoryTitles(CategoryDeitailsBean data) {
        this.mTitleDatas = data;
        initViewPager();

    }
}
