package com.bing.lan.comm.base.mvp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by lean on 16/10/28.
 */

public class SampleFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private View mView;
    private PullToRefreshView mPullToRefreshView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TextView view = new TextView(getContext());
        // ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // view.setLayoutParams(layoutParams);
        // view.setBackgroundColor(0X000000000);
        //
        // view.setText("我只是一个简单的TestFragment");
        // view.setTextSize(45);
        // view.setGravity(Gravity.CENTER);

        // ImageView view = new ImageView(getContext());
        //
        // ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // view.setLayoutParams(layoutParams);
        // view.setImageResource(R.drawable.girl);
        //
        // // container.setPadding(AppUtil.dip2px(20),0,AppUtil.dip2px(20),0);
        // view.setScaleType(ImageView.ScaleType.FIT_XY);

        mView =  inflater.inflate(R.layout.fragment_simple, container, false);
        // view.setViewState(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        initRecyclerView( mView);
        //
        // initRefreshLayout();
        initPullToRefreshView(mView);

        return mView;
    }

    private void initPullToRefreshView(View view) {
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    private void initRefreshLayout() {
        // mRefreshLayout = (BGARefreshLayout) mView.findViewById(R.id.rl_modulename_refresh);
        // // 为BGARefreshLayout 设置代理
        // mRefreshLayout.setDelegate(this);
        // // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        // BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(AppUtil.getAppContext(), true);
        // moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.defult_refresh_img_style);
        // moocStyleRefreshViewHolder.setUltimateColor(R.color.default_refresh_color_style);
        //
        // moocStyleRefreshViewHolder.setLoadingMoreText("正在加载中...");
        // // 设置下拉刷新和上拉加载更多的风格
        // mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);

    }

    private void initRecyclerView( View view) {

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AppUtil.getAppContext(), "客观没有更多的数据了哦", Toast.LENGTH_SHORT).show();
                // mRefreshLayout.endRefreshing();
            }
        }, 2000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return true;
    }
}
