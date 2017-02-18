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
import com.bing.lan.fm.ui.gank.adapter.MeiZhiAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class GankFragment extends BaseFragment<IGankContract.IGankPresenter>
        implements IGankContract.IGankView ,RecyclerArrayAdapter.OnLoadMoreListener {

    @BindView(R.id.recyclerView_gank)
    EasyRecyclerView rcyclerView;
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

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AppUtil.getAppContext(), 3) {

            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rcyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void updateGank(List<GankBean.ResultsBean> data) {
        // mRecyclerView.setAdapter(new GankRecyclerViewAdapter(AppUtil.getAppContext(),
        //         R.layout.item_gank_meizi, data));
        // initListview(  data);

        MeiZhiAdapter meiZhiAdapter = new MeiZhiAdapter(getContext());
        meiZhiAdapter.setMore(R.layout.load_more_layout,this);
        meiZhiAdapter.setNoMore(R.layout.no_more_layout);
        meiZhiAdapter.setError(R.layout.error_layout);

        rcyclerView.setAdapterWithProgress(meiZhiAdapter);
    }

    @Override
    public void onLoadMore() {

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
