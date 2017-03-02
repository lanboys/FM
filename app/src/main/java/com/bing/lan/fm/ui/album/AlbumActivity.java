package com.bing.lan.fm.ui.album;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.fm.R;
import com.bing.lan.fm.listener.RecyclerViewScrollListener;
import com.bing.lan.fm.ui.album.bean.TracksInfoBean;
import com.bing.lan.fm.ui.album.delagate.TracksInfoDelagate;
import com.bing.lan.fm.ui.hot.bean.ListItemEditorBean;
import com.bing.lan.fm.ui.music.MusicActivity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bing.lan.fm.ui.hot.delagate.EditorRecomItemDelagate.ALBUM_DETAIL;

public class AlbumActivity extends BaseActivity<IAlbumContract.IAlbumPresenter>
        implements IAlbumContract.IAlbumView, MultiItemTypeAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.rlv_album_music)
    RecyclerView mRecyclerView;
    private long mAlbumId;
    private List<TracksInfoBean> mRecyclerViewData;
    private MultiItemTypeAdapter<TracksInfoBean> mMultiItemTypeAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_album;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initViewAndData(Intent intent) {
        setToolBar(mToolbar, "专辑详情", true);
        initFab();
        initData(intent);

        initRecyclerView();
    }

    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewData = new ArrayList<>();

        mMultiItemTypeAdapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), mRecyclerViewData);
        TracksInfoDelagate editorRecomItemDelagate = new TracksInfoDelagate();

        mMultiItemTypeAdapter.addItemViewDelegate(editorRecomItemDelagate);

        mMultiItemTypeAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mMultiItemTypeAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener() {
            @Override
            public int getLastVisiblePosition(RecyclerView.LayoutManager layoutManager) {
                return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }

            @Override
            public void loadMore() {
                log.d("loadMore(): 专辑页面加载更多");
                mPresenter.requestData(AlbumPresenter.LOAD_MORE);
            }
        });

        mRecyclerView.setVisibility(View.GONE);
    }

    private void initData(Intent intent) {
        ListItemEditorBean editorBean = (ListItemEditorBean) intent.getSerializableExtra(ALBUM_DETAIL);
        mAlbumId = editorBean.getAlbumId();
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart(mAlbumId);
    }

    private void initFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void updateRecyclerView(List<TracksInfoBean> data) {
        mRecyclerViewData.clear();
        mRecyclerViewData.addAll(data);
        mRecyclerView.setVisibility(View.VISIBLE);
        showToast("为您更新了" + data.size() + "条数据");
    }

    @Override
    public void loadRecyclerView(List<TracksInfoBean> data) {
        mRecyclerViewData.addAll(data);
        showToast("为您增加了" + data.size() + "条数据");
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

        Intent intent = new Intent(view.getContext(), MusicActivity.class);
        intent.putExtra(MusicActivity.TRACK_PLAYLIST, (ArrayList<TracksInfoBean>) mRecyclerViewData);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
