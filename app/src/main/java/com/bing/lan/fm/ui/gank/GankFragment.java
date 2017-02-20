package com.bing.lan.fm.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImagePicassoUtil;
import com.bing.lan.comm.view.ImageAdapterLayout;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView,
        MultiItemTypeAdapter.OnItemClickListener {

    private static final int GRIL_COLUMN = 3;
    @BindView(R.id.recyclerView_gank)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_container)
    BGARefreshLayout mRefreshLayout;
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
    protected void initViewAndData(Intent intent) {
        initRefreshLayout(mRefreshLayout);
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
    }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.clear();
        updateRecylerViewData(data);
    }

    @Override
    public void loadMoreGank(List<GankBean.ResultsBean> data) {
        updateRecylerViewData(data);
    }

    private void updateRecylerViewData(List<GankBean.ResultsBean> data) {
        mRecyclerViewData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.updateGankData();
    }

    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.loadMoreGankData();
        return true;
    }

    public void closeRefeshing() {
        if (mRefreshLayout != null) {
            mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        Intent intent = new Intent(AppUtil.getAppContext(), PictureActivity.class);

        intent.putExtra(PictureActivity.PIC_INDEX, position + 1);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(PictureActivity.PIC_LIST, (Serializable) mRecyclerViewData);//注意序列化

        AppUtil.getAppContext().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    static class GankRecyclerViewAdapter extends CommonAdapter<GankBean.ResultsBean> {

        public GankRecyclerViewAdapter(Context context, int layoutId, List<GankBean.ResultsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, GankBean.ResultsBean resultsBean, int position) {
            ImageView imageView = holder.getView(R.id.iv_girl);

            final ImageAdapterLayout imageView1 = holder.getView(R.id.image_container);

            ImagePicassoUtil.loadImage(imageView, resultsBean.getUrl());
            final int screenWidth = AppUtil.getScreenW();

            // Glide.with(imageView.getContext())
            //         .load(resultsBean.getUrl())
            //         .asBitmap()
            //         .placeholder(R.drawable.image_default_202)
            //         .diskCacheStrategy(DiskCacheStrategy.ALL)
            //         .into(new SimpleTarget<Bitmap>(screenWidth / GRIL_COLUMN, screenWidth / GRIL_COLUMN) {
            //                   @Override
            //                   public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            //                       int width = resource.getWidth();
            //                       int height = resource.getHeight();
            //
            //                       imageView1.setRelative(width / height);
            //                       ((ImageView) holder.getView(R.id.iv_girl)).setImageBitmap(resource);
            //
            //                       // //计算高宽比
            //                       // int finalHeight = (screenWidth / GRIL_COLUMN) * height / width;
            //                       //
            //                       // ViewGroup.LayoutParams layoutParams = holder.getView(R.id.iv_girl).getLayoutParams();
            //                       // layoutParams.height = finalHeight;
            //                       // ((ImageView) holder.getView(R.id.iv_girl)).setImageBitmap(resource);
            //                   }
            //               }
            //         );
        }
    }
}
