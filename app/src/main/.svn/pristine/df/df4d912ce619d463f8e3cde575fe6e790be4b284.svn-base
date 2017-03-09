package com.bing.lan.fm.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.load.ImageLoader;
import com.bing.lan.fm.R;
import com.bing.lan.fm.listener.RecyclerViewScrollListener;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import butterknife.BindView;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView,
        MultiItemTypeAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final int GRIL_COLUMN = 3;
    @BindView(R.id.recyclerView_gank)
    RecyclerView mRecyclerView;
    @BindView(R.id.hot_refresh_container)
    SwipeRefreshLayout mHotRefreshContainer;

    private List<GankBean.ResultsBean> mRecyclerViewData;
    private GankRecyclerViewAdapter mAdapter;

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
    protected void initViewAndData(Intent intent, Bundle arguments) {
        mHotRefreshContainer.setOnRefreshListener(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setVisibility(View.GONE);
        StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(GRIL_COLUMN, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerViewData = new ArrayList<>();
        mAdapter = new GankRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.item_gank_meizi, mRecyclerViewData);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {

                int[] into = new int[3];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                //接近底部开始加载更多,因为高度层次不齐,所以加上5
                return into[0] + 5;
            }

            @Override
            public void loadMore() {
                mPresenter.loadMoreGankData();
                log.d("loadMore(): " + "gank界面加载更多");
            }
        });
    }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.clear();
        updateRecyclerViewData(data);
    }

    @Override
    public void loadMoreGank(List<GankBean.ResultsBean> data) {
        updateRecyclerViewData(data);
    }

    private void updateRecyclerViewData(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void closeRefreshing() {

        if (mHotRefreshContainer != null && mHotRefreshContainer.isRefreshing()) {
            mHotRefreshContainer.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.updateGankData();
        log.d("onBGARefreshLayoutBeginRefreshing(): gank界面下拉刷新");
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        Intent intent = new Intent(AppUtil.getAppContext(), PictureActivity.class);

        intent.putExtra(PictureActivity.PIC_INDEX, position);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PictureActivity.PIC_LIST, (Serializable) mRecyclerViewData);//注意序列化

        AppUtil.getAppContext().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    class GankRecyclerViewAdapter extends CommonAdapter<GankBean.ResultsBean> {

        public GankRecyclerViewAdapter(Context context, int layoutId, List<GankBean.ResultsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, GankBean.ResultsBean resultsBean, int position) {
            String imageUrl = resultsBean.getUrl();

            // loadImageByFresco(holder, imageUrl);
            // loadImageByPicasso(holder,imageUrl);
            // loadImageByGlide(holder, imageUrl);
            // loadImageByLoad(holder, imageUrl, position);

            final SimpleDraweeView draweeView = holder.getView(R.id.main_simple_drawee_view);
            final int imageViewWidth = AppUtil.getScreenW() / GRIL_COLUMN;

            ImageLoader.getInstance()
                    .loadImage(draweeView, imageUrl, new BaseControllerListener<ImageInfo>() {
                        @Override
                        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                            if (imageInfo == null) {
                                return;
                            }
                            ViewGroup.LayoutParams vp = draweeView.getLayoutParams();
                            //计算控件高宽比
                            vp.height = (int) (imageViewWidth * imageInfo.getHeight() / imageInfo.getWidth() + 0.5f);
                            draweeView.requestLayout();
                        }
                    });
        }
    }
}
