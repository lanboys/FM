package com.bing.lan.comm.base.mvp.fragment.refresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.view.LoadPageView;

import java.util.List;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

/**
 * Created by 520 on 2017/1/11.
 */

public abstract class AbsRefreshPresenter<DATA, LISTVIEWBEAN>
        extends BaseFragmentPresenter<IAbsRefreshContract.IAbsRefreshView,
        IAbsRefreshContract.IAbsRefreshModule>
        implements IAbsRefreshContract.IAbsRefreshPresenter {


    /**
     * 区分加载更多还是更新数据
     */
    private boolean mIsLoadMore = false;
    private boolean mIsListViewHaveData = false;
    /**
     * 下次加载数据的索引
     */
    private int mNextLoadMoreDataUrlIndex = 0;

    @Override
    public void onStart(Object... params) {
        if (!mIsListViewHaveData) {
            mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_LOADING);
            // //重新点击进入页面,重置错误计数为0
            mView.resetErrorCount();
            updateData();
        }
    }

    public void errorReloadData() {
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_LOADING);
        updateData();
    }

    public void loadImage(Object path, ImageView imageView) {
        mModule.loadImage(path, imageView);
    }

    public void loadMoreData() {
        if (isFinishLoadTask()) {
            mIsLoadMore = true;
            mModule.loadData(0, mNextLoadMoreDataUrlIndex, this);
        }
        mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_LOADING);
        mView.setListViewSelectLastVisible();
    }

    @Override
    public void updateData() {
        if (isFinishLoadTask()) {
            mIsLoadMore = false;
            mModule.loadData(0, 0, this);//更新数据,从0开始
        }
    }

    private boolean isFinishLoadTask() {
        if (mModule.isLoading()) {
            mView.showToast("客官,你稍等片刻,小二正在加载数据");
            return false;
        }
        return true;
    }

    @Override
    public void onListViewScrollStateChanged(AbsListView view, int scrollState) {

        if (mView.isOpenFooterLoadMoreView()) {

            if (scrollState == SCROLL_STATE_IDLE) {
                int lastVisiblePosition = view.getLastVisiblePosition();
                int lastItemPosition = view.getAdapter().getCount() - 1;//包括头和尾

                if (lastVisiblePosition == lastItemPosition) {
                    mNextLoadMoreDataUrlIndex = lastItemPosition;
                    if (mView.isOpenHeaderBannerView()) {
                        mNextLoadMoreDataUrlIndex -= 1;
                    }
                    loadMoreData();
                }
            }
        }
    }

    @Override
    public void stopUpdate() {
        mView.closeRefreshUI();
        mModule.releaseSubscribe();
    }

    @Override
    public void onSuccess(int action, final Object objectData) {
        AppUtil.postTaskSafe(new Runnable() {
            @Override
            public void run() {
                //关闭刷新进度条
                mView.closeRefreshUI();

                List<LISTVIEWBEAN> data = getListViewBean((DATA) objectData);

                if (mView.isOpenHeaderBannerView()) {
                    List<?> bannerData = getHeaderBannerBean((DATA) objectData);
                    mView.updateHeaderBanner(bannerData);
                }

                if (data != null && data.size() > 0) {
                    if (mIsLoadMore) {
                        mView.loadMoreListViewData(data);
                        //加载成功仍然显示正在加载..
                        mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_LOADING);
                        // TODO: 2017/1/14 当加载的数据只有一条的时候怎么显示??

                    } else {
                        // ArrayList<Object> urls = new ArrayList<>();
                        // urls.add("http://192.168.100.102:8080/GooglePlayServer/image?name=app/com.youyuan.yyhl/icon.jpg");
                        // urls.add("http://192.168.100.102:8080/GooglePlayServer/image?name=app/com.kugou.android/icon.jpg");
                        // urls.add("http://192.168.100.102:8080/GooglePlayServer/image?name=app/com.m520it.www/icon.jpg");
                        // urls.add("http://192.168.100.102:8080/GooglePlayServer/image?name=app/com.achievo.vipshop/icon.jpg");
                        //
                        // mView.updateHeaderBanner(urls);

                        mIsListViewHaveData = true;
                        mView.updateListViewData(data);
                        //更新成功显示成功页面
                        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
                    }

                    mView.showToast("小二为您更新了" + data.size() + "条数据");
                } else {
                    mView.showToast("没有更多数据了哦,请稍后再试");

                    if (mIsLoadMore) {
                        mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_EMPTY);
                        //空页面显示5秒后自动隐藏
                        AppUtil.postTaskSafeDelay(new Runnable() {
                            @Override
                            public void run() {
                                mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_HIDE_ALL);
                            }
                        }, 5000);
                    } else {
                        // 有数据将不显示空页面
                        if (!mIsListViewHaveData) {
                            mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_ERROR);
                            // mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_EMPTY);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onError(int action, Throwable e) {
        AppUtil.postTaskSafe(new Runnable() {
            @Override
            public void run() {

                mView.closeRefreshUI();

                if (mIsLoadMore) {
                    mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_ERROR);
                    //错误页面显示5秒后自动隐藏,如果5s内点击了加载重试,将隐藏不了
                    AppUtil.postTaskSafeDelay(new Runnable() {
                        @Override
                        public void run() {
                            mView.updateFooterView(AbsRefreshFragment.LoadDataResult.LOAD_HIDE_ALL);
                        }
                    }, 5000);
                } else {

                    // 有数据将不显示错误页面
                    if (!mIsListViewHaveData) {
                        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_ERROR);
                    }
                }
                mView.showToast("不好意思,网络出现了问题哦,加载不了数据");
            }
        });
    }

    /**
     * 将M层传过来的数据转换成listView 的数据
     *
     * @param object
     * @return
     */
    protected List<LISTVIEWBEAN> getListViewBean(DATA object) {
        return null;
    }

    protected List<?> getHeaderBannerBean(DATA object) {
        // if (mView.isOpenHeaderBannerView()) {
        //     throw new RuntimeException("please override this method, so you can update banner");
        // }
        return null;
    }

    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Object item = parent.getAdapter().getItem(position);
        //
        // FragmentActivity activity = mView.getActivity();
        // Intent intent = new Intent(activity, DetailActivity.class);
        // activity.startActivity(intent);

    }
}
