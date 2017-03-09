package com.bing.lan.fm.ui.subscriber;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.musicplay.Music;
import com.bing.lan.comm.utils.musicplay.MusicPlayDao;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.R;
import com.bing.lan.fm.cons.Constants;
import com.bing.lan.fm.ui.subscriber.bean.SubscriberItemBean;
import com.bing.lan.fm.ui.subscriber.delagate.FullDelDemoAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 *
 */
public class SubscriberFragment extends BaseFragment<ISubscriberContract.ISubscriberPresenter>
        implements ISubscriberContract.ISubscriberView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.recyclerView_subscber)
    RecyclerView mRecyclerViewSubscber;
    @BindView(R.id.refresh_subscber)
    SwipeRefreshLayout mRefreshSubscber;

    private List<SubscriberItemBean> mRecyclerViewData;
    private MultiItemTypeAdapter<Music> mMultiItemTypeAdapter;
    private FullDelDemoAdapter mFullAdapter;
    private List<Music> mTwoRecyclerViewData;

    public static SubscriberFragment newInstance(String title) {
        SubscriberFragment fragment = new SubscriberFragment();
        Bundle args = new Bundle();
        args.putString(Constants.FRAGMENT_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * @return 返回布局
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_subscber;
    }
    //依赖注入
    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    /**
     * 设置初始
     */
    @Override
    protected void readyStartPresenter() {
        mRefreshSubscber.setOnRefreshListener(this);
        mPresenter.onStart();
        setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
    }

    @Override
    protected void initViewAndData(Intent intent, Bundle arguments) {
        //初始化数据
        //initData();
        initTwoData();
        //初始化控件
        initRecyclerView();

    }

    /**
     * 从数据库中获取到数据
     */
    private void initTwoData() {
        RealmResults<Music> musics =  MusicPlayDao.queryAllMusicInfo();
        log.d("mTwoRecyclerViewData===  "+musics);
        if(mTwoRecyclerViewData==null) {
            mTwoRecyclerViewData =  new ArrayList<Music>();
        }
        for ( Music s : musics) {
            mTwoRecyclerViewData.add(s);
        }
        if(mFullAdapter != null) {
            mFullAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 假数据
     */
    private void initData() {
        //创建空集合存放数据
    }


    private void initRecyclerView() {
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerViewSubscber.setLayoutManager(linearLayoutManager);

        mFullAdapter = new FullDelDemoAdapter(AppUtil.getAppContext(),mTwoRecyclerViewData);
        //监听
        mFullAdapter.setOnDelListener(new FullDelDemoAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                if (pos >= 0 && pos < mTwoRecyclerViewData.size()) {
                    Toast.makeText(AppUtil.getAppContext(), "删除:第" + pos+"条数据成功", Toast.LENGTH_SHORT).show();
                    mTwoRecyclerViewData.remove(pos);
                    mFullAdapter.notifyItemRemoved(pos);//推荐用这个
                    // MusicPlayDao.deleteMusicInfo(mTwoRecyclerViewData.get(pos).albumId);

                    mFullAdapter.notifyDataSetChanged();
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //mAdapter.notifyDataSetChanged();
                }
            }
        });
        //关联数据
        mRecyclerViewSubscber.setAdapter(mFullAdapter);
        mFullAdapter.notifyDataSetChanged();



        /*mMultiItemTypeAdapter = new MultiItemTypeAdapter<>(AppUtil.getAppContext(), mTwoRecyclerViewData);
        final SubscriberItemDelagate adapter = new SubscriberItemDelagate();
        adapter.setOnDelListener(new SubscriberItemDelagate.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                mRecyclerViewData.remove(pos);

                mMultiItemTypeAdapter.notifyDataSetChanged();
            }
        });

        mMultiItemTypeAdapter.addItemViewDelegate(adapter);
        mRecyclerViewSubscber.setAdapter(mMultiItemTypeAdapter);
        mMultiItemTypeAdapter.notifyDataSetChanged();*/
    }

    /**
     *
     */
    @Override
    public void onRefresh() {
        //先清除所有数据
        mTwoRecyclerViewData.clear();
        //再重新添加一下数据
        initTwoData();
        //再关闭
        closeRefreshing();
    }

    /**
     * 关闭刷新
     */
    public void closeRefreshing() {
        if (mRefreshSubscber != null && mRefreshSubscber.isRefreshing()) {
            mRefreshSubscber.setRefreshing(false);
        }
    }
}
