package com.bing.lan.fm.ui.gank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImageLoaderManager;
import com.bing.lan.fm.R;
import com.bing.lan.fm.ui.gank.bean.GankBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView {

    @BindView(R.id.recyclerView_gank)
    RecyclerView mRecyclerView;
    // @BindView(listView)
    // AsymmetricGridView mListView;
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

    // private void initListview(List<GankBean.ResultsBean> data) {
    //     // Choose your own preferred column width
    //
    //     mListView.setRequestedColumnWidth(AppUtil.dip2px(60));
    //     final List<AsymmetricItem> items = new ArrayList<>();
    //
    //     // initialize your items array
    //     BaseListAdapter adapter = new BaseListAdapter<GankBean.ResultsBean>(getContext()) {
    //         @Override
    //         protected int getItemLayoutId(int itemViewType) {
    //             return R.layout.item_gank_meizi;
    //         }
    //
    //         @Override
    //         protected BaseViewHolder createViewHolder(int itemViewType, View itemView) {
    //             return new BaseViewHolder(itemView) {
    //                 @BindView(R.id.iv_girl)
    //                 ImageView mImageView;
    //
    //
    //                 @Override
    //                 public void fillData(GankBean.ResultsBean data, int position) {
    //
    //                     ImageLoaderManager.loadImage(mImageView, data.getUrl());
    //                 }
    //             };
    //         }
    //     };
    //
    //     // ListAdapter  adapter1 = new ListAdapter(getActivity(), listView, items);
    //
    //     adapter.setDataAndRefresh(data);
    //     AsymmetricGridViewAdapter asymmetricAdapter =
    //             new AsymmetricGridViewAdapter<>(getActivity(), mListView, adapter);
    //     mListView.setAdapter(asymmetricAdapter);
    // }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        mRecyclerView.setAdapter(new GankRecyclerViewAdapter(AppUtil.getAppContext(),
                R.layout.item_gank_meizi, data));
        // initListview(  data);

    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AppUtil.getAppContext(), 3) {

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);


        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    static class GankRecyclerViewAdapter extends CommonAdapter<GankBean.ResultsBean> {

        public GankRecyclerViewAdapter(Context context, int layoutId, List<GankBean.ResultsBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, GankBean.ResultsBean resultsBean, int position) {
            ImageView imageView = holder.getView(R.id.iv_girl);
            ImageLoaderManager.loadImage(imageView, resultsBean.getUrl());
        }
    }
}
