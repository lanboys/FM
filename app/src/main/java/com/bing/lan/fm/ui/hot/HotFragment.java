package com.bing.lan.fm.ui.hot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anbetter.log.MLog;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.cons.Constants;
import com.bing.lan.fm.listener.RecyclerViewScrollListener;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.bing.lan.fm.ui.hot.delagate.DiscoverItemDelagate;
import com.bing.lan.fm.ui.hot.delagate.EditorRecomItemDelagate;
import com.bing.lan.fm.ui.hot.delagate.GirlViewPagerAdapter;
import com.bing.lan.fm.ui.hot.delagate.GuessItemDelagate;
import com.bing.lan.fm.ui.hot.delagate.SpecialItemDelagate;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.facebook.fresco.helper.listener.IResult;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class HotFragment extends BaseFragment<IHotContract.IHotPresenter>
        implements IHotContract.IHotView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    Banner mBanner;
    ViewPager mGirlViewpager;
    @BindView(R.id.recyclerView)
    RecyclerView mHotRecyclerView;
    @BindView(R.id.refresh_container)
    SwipeRefreshLayout mHotRefreshContainer;

    private int[] imgs = {R.drawable.i1, R.drawable.i2,
            R.drawable.i3, R.drawable.i4, R.drawable.i5,
            R.drawable.i6, R.drawable.i7, R.drawable.i1,
            R.drawable.i2, R.drawable.i3, R.drawable.i4,
            R.drawable.i5, R.drawable.i6, R.drawable.i7,
            R.drawable.i1, R.drawable.i2, R.drawable.i3,
            R.drawable.i4, R.drawable.i5, R.drawable.i6,
            R.drawable.i7};

    private List<View> mViews;
    private View mGirlViewpagerView;
    private View mBannerView;
    // private int BANNER_HEIGHT = AppUtil.dip2px(175);
    // private int VIEWPAGE_HEIGHT = (int) (AppUtil.getScreenW() * 0.65f + AppUtil.dip2px(20));
    // private int VIEWPAGE_HEIGHT = AppUtil.dip2px(285);
    private GirlViewPagerAdapter mAdapter;
    private List<HotInfoBean> mRecyclerViewData;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private MultiItemTypeAdapter<HotInfoBean> mMultiItemTypeAdapter;
    private List<GankBean.ResultsBean> mImgList;
    private int bannerHeight;
    private int viewpageHeight;

    public static HotFragment newInstance(String title) {
        HotFragment fragment = new HotFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {
        mHotRefreshContainer.setOnRefreshListener(this);
        initHeight();
        initBanner();
        initGirlGallery();
        initRecyclerView();
        hideAll();
    }

    private void initHeight() {
        bannerHeight = AppUtil.dip2px(175);
        // bannerHeight = (int) (AppUtil.getScreenH() * 0.35f);
        viewpageHeight = (int) (AppUtil.getScreenW() * 0.7f + AppUtil.dip2px(20));
    }

    protected void initBanner() {
        mBannerView = mLayoutInflater.inflate(R.layout.item_banner_layout, null);
        ViewGroup.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, bannerHeight);
        mBannerView.setLayoutParams(layoutParams);

        mBanner = (Banner) mBannerView.findViewById(R.id.item_banner);

        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, final ImageView imageView) {
                //加载图片
                // ImagePicassoUtil.loadImage(imageView, (String) path);
                // com.bing.lan.comm.utils.load.ImageLoader.getInstance().loadImage(imageView, (String) path);
                com.bing.lan.comm.utils.load.ImageLoader
                        .getInstance()
                        .loadImage(getContext(), (String) path, AppUtil.getScreenW(), bannerHeight, new IResult<Bitmap>() {
                            @Override
                            public void onResult(Bitmap bitmap) {
                                MLog.i("Thread.currentThread().getId() = " + Thread.currentThread().getId());
                                imageView.setImageBitmap(bitmap);
                                // imageView.setScaleType(ImageView.ScaleType.CENTER);
                            }
                        });
            }
        });
    }

    private void initGirlGallery() {

        int screenWidth = AppUtil.getScreenW();

        mGirlViewpagerView = mLayoutInflater.inflate(R.layout.hot_viewpage_layout, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, viewpageHeight);

        mGirlViewpagerView.setLayoutParams(layoutParams);
        mGirlViewpager = (ViewPager) mGirlViewpagerView.findViewById(R.id.girl_viewpager);

        // mGirlViewpager.setPageMargin(-screenWidth / 3 - 90);
        mGirlViewpager.setPageMargin((int) (-screenWidth * 0.4f));
        mGirlViewpager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                if (Math.abs(position) == 1) {
                    position = 1;
                }
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });

        mAdapter = new GirlViewPagerAdapter();
        mGirlViewpager.setAdapter(mAdapter);
    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mHotRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewData = new ArrayList<>();

        mMultiItemTypeAdapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), mRecyclerViewData);
        EditorRecomItemDelagate editorRecomItemDelagate = new EditorRecomItemDelagate();

        mMultiItemTypeAdapter.addItemViewDelegate(editorRecomItemDelagate);

        mMultiItemTypeAdapter.addItemViewDelegate(new SpecialItemDelagate());
        mMultiItemTypeAdapter.addItemViewDelegate(new GuessItemDelagate());
        mMultiItemTypeAdapter.addItemViewDelegate(new DiscoverItemDelagate());

        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mMultiItemTypeAdapter);

        mHeaderAndFooterWrapper.addHeaderView(mBannerView);
        mHeaderAndFooterWrapper.addHeaderView(mGirlViewpagerView);

        mHotRecyclerView.setAdapter(mHeaderAndFooterWrapper);
        mHotRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            @Override
            public void loadMore() {
                // TODO: 2017/2/25
                // log.d("loadMore(): 热门页面加载更多,还未做");
                //没有加载更多
            }
        });
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    @Override
    public void updateBanner(List<String> imageUrls) {
        mBanner.setImages(imageUrls);
        mBanner.start();
        notifyDataSetChanged();
        mBanner.setVisibility(View.VISIBLE);
        mHotRecyclerView.scrollToPosition(0);
    }

    @Override
    public void updateGirlViewPager(List<GankBean.ResultsBean> data) {
        mViews = new ArrayList<>();

        mImgList = data;
        for (int i = 0; i < data.size(); i++) {

            GankBean.ResultsBean resultsBean = data.get(i);

            View view = mLayoutInflater.inflate(R.layout.hot_item_viewpage, null);
            ImageView im = (ImageView) view.findViewById(R.id.vp_img_item);
            // DialogTitle dt = (DialogTitle) view.findViewById(R.id.dt_img_title);
            // dt.setText(resultsBean.getDesc());

            // ImagePicassoUtil.loadImage(im, resultsBean.getUrl());
            com.bing.lan.comm.utils.load.ImageLoader.getInstance().loadBigImage(im, resultsBean.getUrl());
            im.setScaleType(ImageView.ScaleType.FIT_CENTER);

            im.setOnClickListener(this);
            im.setTag(i);

            mViews.add(view);
        }

        mAdapter.setData(mViews);

        Random random = new Random();
        int nextInt = random.nextInt(mViews.size() - 15);
        if (nextInt < 1) {
            nextInt += 5;
        }
        mGirlViewpager.setCurrentItem(nextInt);

        mGirlViewpager.setOffscreenPageLimit(mViews.size());
        notifyDataSetChanged();

        mGirlViewpager.setVisibility(View.VISIBLE);
        mHotRecyclerView.scrollToPosition(0);

    }

    @Override
    public void updateRecyclerView(List<HotInfoBean> data) {
        mRecyclerViewData.clear();
        mRecyclerViewData.addAll(data);
        notifyDataSetChanged();
        mHotRecyclerView.setVisibility(View.VISIBLE);
        mHotRecyclerView.scrollToPosition(0);

        showToast("为您更新了" + data.size() + "条数据");
    }

    @Override
    public void onRefresh() {
        mPresenter.reStartUpdate();
    }

    private void hideAll() {
        mBanner.setVisibility(View.GONE);
        mGirlViewpager.setVisibility(View.GONE);
        mHotRecyclerView.setVisibility(View.GONE);
    }

    private void notifyDataSetChanged() {
        mMultiItemTypeAdapter.notifyDataSetChanged();
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    public void closeRefreshing() {
        if (mHotRefreshContainer != null && mHotRefreshContainer.isRefreshing()) {
            mHotRefreshContainer.setRefreshing(false);
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(AppUtil.getAppContext(), PictureActivity.class);
        intent.putExtra(PictureActivity.PIC_INDEX, (int) v.getTag());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PictureActivity.PIC_LIST, (Serializable) mImgList);//注意序列化

        AppUtil.getAppContext().startActivity(intent);
    }
}

