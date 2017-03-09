package com.bing.lan.fm.ui.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.cons.Constants;
import com.bing.lan.fm.ui.album.AlbumActivity;
import com.bing.lan.fm.ui.recommend.adapter.ItemAdapter;
import com.bing.lan.fm.ui.recommend.bean.AlbumBean;
import com.bing.lan.fm.ui.recommend.bean.DataBean;
import com.bing.lan.fm.ui.recommend.bean.RecBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 *
 */
public class RecommendFragment extends BaseFragment<IRecommendContract.IRecommendPresenter>
        implements IRecommendContract.IRecommendView, MultiItemTypeAdapter.OnItemClickListener {

    public static final String ALBUM_DETAIL = "album_detail";

    @BindView(R.id.recyclerView)
    RecyclerView mRecomRecyclerView;
    private ArrayList<Object> mRecyclerViewData;
    private ItemAdapter mItemAdapter;
    private MultiItemTypeAdapter mAdapter;

    public static RecommendFragment newInstance(String title) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_recommend;
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
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecomRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewData = new ArrayList<>();

        mAdapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), mRecyclerViewData);
        mAdapter.addItemViewDelegate(new ItemAdapter());
        mRecomRecyclerView.setAdapter(mAdapter);
        //设置item的点击
        mAdapter.setOnItemClickListener(this);
    }

    /**
     * 回调
     */
    @Override
    public void dataRec(RecBean data) {
        List<DataBean> bean = data.getData();
        mRecyclerViewData.clear();
        mRecyclerViewData.addAll(bean);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        AlbumBean tag = (AlbumBean) view.getTag();
        log.d("onItemClick(): " + tag);
        Intent intent = new Intent(view.getContext(), AlbumActivity.class);
        intent.putExtra(ALBUM_DETAIL, tag);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
