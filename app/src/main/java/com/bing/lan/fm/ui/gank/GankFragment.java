package com.bing.lan.fm.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImageLoaderManager;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView, RecyclerArrayAdapter.OnLoadMoreListener {

    private static final int GRIL_COLUMN = 3;
    @BindView(R.id.recyclerView_gank)
    RecyclerView mRecyclerView;

    // @BindView(R.id.swipe_refresh_gank)
    // SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_gank;
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
    protected void initViewAndData(Intent intent) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(GRIL_COLUMN, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        mRecyclerView.setAdapter(new GankRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.item_gank_meizi, data));
    }

    @Override
    public void onLoadMore() {

    }

    static class GankRecyclerViewAdapter extends CommonAdapter<GankBean.ResultsBean> {

        public GankRecyclerViewAdapter(Context context, int layoutId, List<GankBean.ResultsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, GankBean.ResultsBean resultsBean, int position) {
            ImageView imageView = holder.getView(R.id.iv_girl);
            ImageLoaderManager.loadImage(imageView, resultsBean.getUrl());
            final int screenWidth = AppUtil.getScreenW();
            Glide.with(imageView.getContext())
                    .load(resultsBean.getUrl())
                    .asBitmap()
                    .placeholder(R.drawable.image_default_202)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<Bitmap>(screenWidth / GRIL_COLUMN, screenWidth / GRIL_COLUMN) {
                              @Override
                              public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                  int width = resource.getWidth();
                                  int height = resource.getHeight();
                                  //计算高宽比
                                  int finalHeight = (screenWidth / GRIL_COLUMN) * height / width;

                                  ViewGroup.LayoutParams layoutParams = holder.getView(R.id.iv_girl).getLayoutParams();
                                  layoutParams.height = finalHeight;
                                  ((ImageView) holder.getView(R.id.iv_girl)).setImageBitmap(resource);
                              }
                          }
                    );
        }
    }
}
