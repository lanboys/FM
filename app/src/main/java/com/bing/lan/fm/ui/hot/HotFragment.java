package com.bing.lan.fm.ui.hot;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImageLoaderManager;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.delagate.EditorRecomItemDelagate;
import com.bing.lan.fm.ui.hot.delagate.GirlViewPagerAdapter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class HotFragment extends BaseFragment<IHotContract.IHotPresenter>
        implements IHotContract.IHotView, BGARefreshLayout.BGARefreshLayoutDelegate {

    Banner mBanner;
    ViewPager mGirlViewpager;
    @BindView(R.id.hot_recyclerView)
    RecyclerView mHotRecyclerView;
    @BindView(R.id.hot_refresh_container)
    BGARefreshLayout mHotRefreshContainer;

    private int[] imgs = {R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7};
    private List<View> mViews;
    private View mGirlViewpagerView;
    private View mBannerView;
    private int BANNER_HEIGHT = AppUtil.dip2px(150);
    private int VIEWPAGE_HEIGHT = AppUtil.dip2px(275);
    private GirlViewPagerAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent) {
        initRefreshLayout(mHotRefreshContainer);
        initBanner();
        initGirlGallery();
    }

    @Override
    public void updateRecyclerView(List<HotInfoBean> data) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mHotRecyclerView.setLayoutManager(linearLayoutManager);

        MultiItemTypeAdapter<HotInfoBean> adapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), data);
        adapter.addItemViewDelegate(new EditorRecomItemDelagate());

        // adapter.addItemViewDelegate(new FocusImageItemDelagate());
        // adapter.addItemViewDelegate(new EditorRecomItemDelagate());

        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);

        mHeaderAndFooterWrapper.addHeaderView(mBannerView);
        mHeaderAndFooterWrapper.addHeaderView(mGirlViewpagerView);
        mHotRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    private void initGirlGallery() {

        int screenWidth = AppUtil.getScreenW();
        // Log.d("bingtag", screenWidth + "---");
        mGirlViewpagerView = mLayoutInflater.inflate(R.layout.hot_viewpage_layout, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, VIEWPAGE_HEIGHT);

        mGirlViewpagerView.setLayoutParams(layoutParams);
        mGirlViewpager = (ViewPager) mGirlViewpagerView.findViewById(R.id.girl_viewpager);

        mGirlViewpager.setPageMargin(-screenWidth / 3 - 35);

        mGirlViewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                if (Math.abs(position) == 1) {
                    position = 1;
                }
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                // Log.d("bingtag", "--------" + position + "------" + +normalizedposition + "----" + (normalizedposition / 2 + 0.5f));
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });

        mAdapter = new GirlViewPagerAdapter();
        mGirlViewpager.setAdapter(mAdapter);
        updateGirlViewPager();
    }

    private void updateGirlViewPager() {
        createViews();
        mAdapter.setData(mViews);
        mGirlViewpager.setCurrentItem(mViews.size() / 2);
        mGirlViewpager.setOffscreenPageLimit(mViews.size());
    }

    private void createViews() {
        mViews = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(AppUtil.getAppContext());
        for (int id : imgs) {
            View view = inflater.inflate(R.layout.hot_item_viewpage, null);
            ImageView im = (ImageView) view.findViewById(R.id.vp_img_item);
            im.setImageResource(id);
            mViews.add(view);
        }
    }

    protected void initBanner() {
        mBannerView = mLayoutInflater.inflate(R.layout.item_banner_layout, null);
        ViewGroup.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, BANNER_HEIGHT);
        mBannerView.setLayoutParams(layoutParams);
        mBanner = (Banner) mBannerView.findViewById(R.id.item_banner);
        // showBanner(false);
        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //加载图片
                ImageLoaderManager.loadImage(imageView, (String) path);
            }
        });
    }

    public void showBanner(boolean isShow) {
        mBanner.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    public void updateBanner(List<String> imageUrls) {
        showBanner(true);
        mBanner.setImages(imageUrls);
        mBanner.start();
    }
}
