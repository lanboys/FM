package com.bing.lan.comm.base.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.DaggerFragmentComponent;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.di.FragmentModule;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.fm.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * @author 蓝兵
 * @time 2017/1/10  18:36
 */
public abstract class BaseFragment<T extends IBaseFragmentContract.IBaseFragmentPresenter>
        extends Fragment
        implements IBaseFragmentContract.IBaseFragmentView<T>,
        LoadPageView.OnErrorButtonListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @Inject
    protected LogUtil log;
    @Inject
    protected T mPresenter;
    protected LayoutInflater mLayoutInflater;

    protected View mContentView;
    protected boolean mHaveData;
    private LoadPageView mLoadPage;
    private Unbinder mViewBind;

    @Override
    public boolean isHaveData() {
        return mHaveData;
    }

    @Override
    public void setHaveData(boolean haveData) {
        mHaveData = haveData;
    }

    public View getContentView() {
        return mContentView;
    }

    public LoadPageView getLoadPage() {
        return mLoadPage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启动di,可能第二次执行生命周期,故需要做个非空判断
        if (mPresenter == null) {
            //必须在子类注入,因为要注入的类型是泛型,只有在实现类才能确定
            startInject(getFragmentComponent());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initWindowUI(inflater, container, savedInstanceState);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        resetErrorCount();
        readyStart();
    }

    private void readyStart() {
        if (!mHaveData) {
            if (mLoadPage != null) {
                setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_LOADING);
            }
            //页面没有数据才启动p层逻辑
            readyStartPresenter();
        } else {
            if (mLoadPage != null) {
                setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止更新
        stopUpdate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mViewBind != null) {
            mViewBind.unbind();
            mViewBind = null;
        }
        //解绑
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }

        AppUtil.MemoryLeakCheck(this);
    }

    /**
     * 停止更新,释放一些正在进行的任务
     */
    public void stopUpdate() {
        if (mPresenter != null)
            mPresenter.stopUpdate();
    }

    @Override
    public void reStartUpdate() {
        if (mPresenter != null)
            mPresenter.reStartUpdate();
    }

    protected abstract void readyStartPresenter();

    protected void initRefreshLayout(BGARefreshLayout refreshLayout) {
        // // 为BGARefreshLayout 设置代理
        // refreshLayout.setDelegate(this);
        // // 设置下拉刷新和上拉加载更多的风格
        // BGARefreshViewHolder refreshViewHolder = getRefreshViewHolder( );
        // refreshLayout.setRefreshViewHolder(refreshViewHolder);


        // 为BGARefreshLayout 设置代理
        refreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(AppUtil.getAppContext(), true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.defult_refresh_img_style);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.default_refresh_color_style);

        moocStyleRefreshViewHolder.setLoadingMoreText("正在加载中...");
        // 设置下拉刷新和上拉加载更多的风格
        refreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
    }

    protected BGARefreshViewHolder getRefreshViewHolder(   ) {

        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder =
                new BGAMoocStyleRefreshViewHolder(AppUtil.getAppContext(), true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.defult_refresh_img_style);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.default_refresh_color_style);

        // 设置下拉刷新和上拉加载更多的风格
        moocStyleRefreshViewHolder.setLoadingMoreText("正在加载中...");
        return moocStyleRefreshViewHolder;
    }



    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
    }

    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return true;
    }

    /**
     * 默认打开加载页面(空页面,错误页面,正在加载页面)
     */
    protected boolean isOpenLoadPager() {
        return true;
    }

    /**
     * 默认关闭下拉刷新(内置了scrollview,容易发生滑动冲突)
     */
    protected boolean isOpenRefresh() {
        //没必要使用的地方尽量关闭,不然嵌套太多层了
        return false;
    }

    private void initWindowUI(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            initContentView(inflater, container);
            mViewBind = ButterKnife.bind(this, mContentView);
            //初始化数据
            initViewAndData(getActivity().getIntent());
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        //绑定控件
        if (mViewBind == null) {
            mViewBind = ButterKnife.bind(this, mContentView);
        }
    }

    private void initContentView(LayoutInflater inflater, ViewGroup container) {
        mLayoutInflater = inflater;
        //判断是否打开加载页面
        if (isOpenLoadPager()) {
            initLoadPager();
            mContentView = mLoadPage;
        } else {
            mContentView = initSuccessView(inflater, container);
        }
    }

    private void initLoadPager() {
        mLoadPage = new LoadPageView(getActivity(), isOpenRefresh()) {
            @Override
            protected void initRefreshLayout(BGARefreshLayout refreshLayout) {
                BaseFragment.this.initRefreshLayout(refreshLayout);
            }

            @Override
            protected View initSuccessView(LayoutInflater inflater, ViewGroup parent) {
                return BaseFragment.this.initSuccessView(inflater, parent);
            }

            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                BaseFragment.this.onBGARefreshLayoutBeginRefreshing(refreshLayout);
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                return BaseFragment.this.onBGARefreshLayoutBeginLoadingMore(refreshLayout);
            }
        };
        //点击错误页面的的加载按钮重新加载
        mLoadPage.setErrorButtonListener(this);
    }

    protected abstract void initViewAndData(Intent intent);

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this, getArguments()))
                .build();
    }

    protected abstract void startInject(FragmentComponent fragmentComponent);

    @Override
    public void setViewState2LoadPage(LoadPageView.LoadDataResult loadDataResult) {
        if (mLoadPage == null) {
            log.w("setViewState2LoadPage(): mLoadPage == null");
            return;
        }
        mLoadPage.setViewState(loadDataResult);
    }

    @Override
    public void resetErrorCount() {
        if (mLoadPage == null) {
            log.w("resetErrorCount(): mLoadPage == null");
            return;
        }
        mLoadPage.resetErrorCount();
    }

    public void showToast(String msg) {
        // TODO: 2017/2/8 记得更新
        Toast.makeText(AppUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected View initSuccessView(LayoutInflater layoutInflater, ViewGroup container) {
        return layoutInflater.inflate(getLayoutResId(), container, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void OnErrorButtonClick(View v) {
        readyStart();
    }

    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish) {
        AppUtil.startActivity(getActivity(), clazz, isFinish);
    }

    /**
     * 默认false
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false);
    }

    /**
     * 加载图片
     *
     */
    protected void loadImage(Object path, ImageView imageView) {
        mPresenter.loadImage(path, imageView);
    }

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void updateTitle(String title) {

    }
}
