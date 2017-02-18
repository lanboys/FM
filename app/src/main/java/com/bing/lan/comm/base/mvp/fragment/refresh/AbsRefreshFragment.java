package com.bing.lan.comm.base.mvp.fragment.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.comm.base.BaseViewHolder;
import com.bing.lan.comm.base.adapter.MyBaseAdapter;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.fm.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bing.lan.comm.base.mvp.fragment.refresh.AbsRefreshFragment.LoadDataResult.LOAD_EMPTY;
import static com.bing.lan.comm.base.mvp.fragment.refresh.AbsRefreshFragment.LoadDataResult.LOAD_ERROR;
import static com.bing.lan.comm.base.mvp.fragment.refresh.AbsRefreshFragment.LoadDataResult.LOAD_LOADING;

/**
 * A simple {@link Fragment} subclass.
 */
// T extends IAbsRefreshContract.IAbsRefreshPresenter

public abstract class AbsRefreshFragment<LISTVIEWBEAN>
        extends BaseFragment<IAbsRefreshContract.IAbsRefreshPresenter>
        implements IAbsRefreshContract.IAbsRefreshView,
        SwipeRefreshLayout.OnRefreshListener,
        AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener {

    @BindView(R.id.pull_refresh_view)
    protected SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_refresh)
    protected ListView mRefreshListView;
    private RefreshAdapter mRefreshAdapter;

    private LoadMoreHolder mLoadMoreHolder;
    private Banner mBanner;

    protected int getLayoutResId() {
        return R.layout.fragment_list_view_refresh;
    }

    @Override
    protected void readyStartPresenter() {
        //启动p层逻辑
        mPresenter.onStart();
    }

    @Override
    protected void errorReloadData() {
        mPresenter.errorReloadData();
    }

    @Override
    protected View initSuccessView(LayoutInflater layoutInflater, ViewGroup parent) {
        View view = layoutInflater.inflate(getLayoutResId(), parent, false);
        //        View view = View.inflate(AppUtil.getAppContext(), R.layout.fragment_list_view_refresh, null);
        //         ButterKnife.bind(this, view);
        if (isOpenHeaderBannerView()) {
            mRefreshListView.addHeaderView(addHeaderView());
        }
        if (isOpenFooterLoadMoreView()) {
            mRefreshListView.addFooterView(addFooterView());
        }

        mRefreshAdapter = new RefreshAdapter();
        mRefreshListView.setAdapter(mRefreshAdapter);
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshListView.setOnScrollListener(this);
        mRefreshListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public boolean isOpenHeaderBannerView() {
        return true;
    }

    @Override
    public void setListViewSelectLastVisible() {
        mRefreshListView.setSelection(mRefreshListView.getCount() - 1);
    }

    @Override
    public boolean isOpenFooterLoadMoreView() {
        return true;
    }

    /**
     * 可重写该方法,更改头布局
     *
     * @return
     */
    protected View addHeaderView() {
        View headerView = mLayoutInflater.inflate(R.layout.item_banner_layout, null);
        mBanner = (Banner) headerView.findViewById(R.id.item_banner);

        int screenHeight = 800;
        AbsListView.LayoutParams layoutParams =
                new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 3);  //设置轮播图高度
        mBanner.setLayoutParams(layoutParams);

        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                getBannerImageSetting(imageView);
                //加载图片
                loadImage(path, imageView);
            }
        });
        List<?> imageUrls = getBannerImageUrls();

        mBanner.setImages(imageUrls);
        //设置banner的动画等
        getBannerSetting(mBanner);

        //设置方法全部调用完毕时最后调用
        mBanner.start();

        return headerView;
    }

    /**
     * 使用默认的轮播图,可通过此方法更改默认图片
     *
     * @return
     */
    @NonNull
    protected List<?> getBannerImageUrls() {
        //默认图片地址
        List<Object> imageUrls = new ArrayList<>();
        imageUrls.add("");
        imageUrls.add("");
        imageUrls.add("");
        // imageUrls.add(R.mipmap.ic_launcher);
        // imageUrls.add(R.mipmap.ic_launcher);
        // imageUrls.add(R.mipmap.ic_launcher);
        return imageUrls;
    }

    /**
     * 使用默认的轮播图,可通过此方法给轮播图添加一些设置,如动画
     *
     * @param banner
     * @return
     */
    protected void getBannerSetting(Banner banner) {
        // banner.setBannerStyle();

    }

    /**
     * 使用默认的轮播图,可重写该方法更改轮播图的图片设置
     *
     * @param imageView
     */
    protected void getBannerImageSetting(ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.CENTER);
    }

    /**
     * 使用默认的轮播图,可调用该方法更新轮播图图片
     *
     * @param imageUrls
     */
    public void updateHeaderBanner(List<?> imageUrls) {
        //如果有headerView 重写该方法进行更新数据
        //怎么更新啊啊啊  啊  啊 啊 啊
        if (mBanner != null && imageUrls != null && imageUrls.size() > 0) {
            mBanner.setImages(imageUrls);
            mBanner.start();
        }
    }

    /**
     * 可重写该方法,更改默认的加载更多的视图
     *
     * @return
     */
    protected View addFooterView() {
        View footerView = mLayoutInflater.inflate(R.layout.item_load_more, null);
        mLoadMoreHolder = new LoadMoreHolder(footerView);
        return footerView;
    }

    @Override
    public void updateFooterView(LoadDataResult result) {
        mLoadMoreHolder.refreshViewData(result, 0);
    }

    /**
     * 加载图片
     *
     * @param path
     * @param imageView
     */
    protected void loadImage(Object path, ImageView imageView) {
        mPresenter.loadImage(path, imageView);
    }

    @Override
    public void onRefresh() {
        mPresenter.updateData();
    }

    public void setSwipeRefreshLayoutEnabled(boolean b) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnabled(b);
        }
    }

    // @Override
    public void openRefreshUI() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void closeRefreshUI() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public <LISTVIEWBEAN1> void updateListViewData(List<LISTVIEWBEAN1> data) {
        mRefreshAdapter.setDataAndRefresh((List<LISTVIEWBEAN>) data);
    }

    @Override
    public <LISTVIEWBEAN1> void loadMoreListViewData(List<LISTVIEWBEAN1> data) {
        mRefreshAdapter.insert((List<LISTVIEWBEAN>) data);
    }

    /**
     * 子类可以重写该方法更改类型数量
     *
     * @return
     */
    public int getListViewTypeCount() {
        return 1;
    }

    /**
     * 子类可以重写该方法更改类型
     *
     * @param position
     * @param data
     * @return
     */
    public int getListViewItemType(int position, List<LISTVIEWBEAN> data) {
        return 0;
    }

    public int getListViewItemLayoutId(int itemViewType) {
        // TODO: 2017/2/10
        return 0;
    }

    public BaseViewHolder<LISTVIEWBEAN> getListViewItemHolder(int itemViewType, View convertView) {
        // TODO: 2017/2/10
        return null;
    }

    /**
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        mPresenter.onListViewScrollStateChanged(view, scrollState);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.onListViewItemClick(parent, view, position, id);
    }

    public enum LoadDataResult {

        LOAD_HIDE_ALL,
        LOAD_ERROR,
        LOAD_EMPTY,
        LOAD_LOADING

    }

    class RefreshAdapter extends MyBaseAdapter<LISTVIEWBEAN> {

        @Override
        public int getItemLayoutId(int itemViewType) {
            return getListViewItemLayoutId(itemViewType);
        }

        @Override
        public BaseViewHolder<LISTVIEWBEAN> getViewHolder(int itemViewType, View convertView) {
            return getListViewItemHolder(itemViewType, convertView);
        }

        @Override
        public int getViewTypeCount() {
            return getListViewTypeCount();
        }

        @Override
        public int getItemViewType(int position) {
            return getListViewItemType(position, mData);
        }
    }

    class LoadMoreHolder extends BaseViewHolder<LoadDataResult> {

        protected LoadDataResult isVisible;
        // @BindView(R.id.item_loadmore_tv_loading)
        // TextView itemLoadMoreTvLoading;
        @BindView(R.id.item_loadmore_container_loading)
        LinearLayout itemLoadMoreContainerLoading;
        @BindView(R.id.item_loadmore_container_retry)
        LinearLayout itemLoadMoreContainerRetry;
        @BindView(R.id.item_loadmore_tv_retry)
        TextView itemLoadMoreTvRetry;
        // @BindView(R.id.item_loadmore_tv_empty)
        // TextView itemLoadMoreTvEmpty;
        @BindView(R.id.item_loadmore_container_empty)
        LinearLayout itemLoadMoreContainerEmpty;

        public LoadMoreHolder(View viewHolder) {
            super(viewHolder);

            itemLoadMoreTvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.loadMoreData();
                }
            });
        }

        @Override
        public void refreshViewData(LoadDataResult result, int position) {

            itemLoadMoreContainerEmpty.setVisibility(View.GONE);
            itemLoadMoreContainerRetry.setVisibility(View.GONE);
            itemLoadMoreContainerLoading.setVisibility(View.GONE);

            switch (result) {
                case LOAD_LOADING:
                    itemLoadMoreContainerLoading.setVisibility(View.VISIBLE);
                    isVisible = LOAD_LOADING;
                    break;
                case LOAD_EMPTY:
                    itemLoadMoreContainerEmpty.setVisibility(View.VISIBLE);
                    isVisible = LOAD_EMPTY;
                    break;
                case LOAD_ERROR:
                    isVisible = LOAD_ERROR;
                    itemLoadMoreContainerRetry.setVisibility(View.VISIBLE);
                    break;
                case LOAD_HIDE_ALL:
                    //正在加载页面是不能隐藏的
                    if (isVisible == LOAD_LOADING) {
                        itemLoadMoreContainerLoading.setVisibility(View.VISIBLE);
                    } else {
                        isVisible = null;
                    }
                    break;
            }
        }
    }
}
