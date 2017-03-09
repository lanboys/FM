package com.bing.lan.fm.ui.subscriber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.cons.Constants;
import com.bing.lan.fm.listener.RecyclerViewScrollListener;
import com.bing.lan.fm.ui.hot.bean.HotInfoBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class SubscriberFragment extends BaseFragment<ISubscriberContract.ISubscriberPresenter>
        implements ISubscriberContract.ISubscriberView {

    @BindView(R.id.recyclerView)
    RecyclerView mHotRecyclerView;
    @BindView(R.id.refresh_container)
    SwipeRefreshLayout mHotRefreshContainer;

    public static SubscriberFragment newInstance(String title) {
        SubscriberFragment fragment = new SubscriberFragment();
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

    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {
        initRecyclerView();
    }
    private List<HotInfoBean> mRecyclerViewData;
    private MultiItemTypeAdapter<HotInfoBean> mMultiItemTypeAdapter;
    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mHotRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewData = new ArrayList<>();

        mMultiItemTypeAdapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), mRecyclerViewData);
        // EditorRecomItemDelagate editorRecomItemDelagate = new EditorRecomItemDelagate();
        //
        // mMultiItemTypeAdapter.addItemViewDelegate(editorRecomItemDelagate);

        // mMultiItemTypeAdapter.addItemViewDelegate(new SpecialItemDelagate());
        // mMultiItemTypeAdapter.addItemViewDelegate(new GuessItemDelagate());
        // mMultiItemTypeAdapter.addItemViewDelegate(new DiscoverItemDelagate());


        mHotRecyclerView.setAdapter(mMultiItemTypeAdapter);
        mHotRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            @Override
            public void loadMore() {
                // TODO: 2017/2/25
                // log.d("loadMore(): 热门页面加载更多,还未做");
            }
        });
    }

}
