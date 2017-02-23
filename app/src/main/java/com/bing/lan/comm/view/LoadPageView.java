package com.bing.lan.comm.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.fm.R;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * @author 蓝兵
 * @time 2017/1/10  18:42
 */
public abstract class LoadPageView extends FrameLayout implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private static final int STATE_EMPTY = 0;
    private static final int STATE_ERROR = 1;
    private static final int STATE_LOADING = 2;
    private static final int STATE_SUCCESS = 3;
    /**
     * 重试次数
     */
    private static final int RELOAD_TIMES = 2;
    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);
    private boolean mIsOpenRefresh;
    private View mEmptyPager;
    private View mErrorPager;
    private View mLoadingPager;
    private View mSuccessPager;
    private int mCurrentState = STATE_LOADING;

    private OnErrorButtonListener mErrorButtonListener;
    private int mErrorCount;
    private BGARefreshLayout mRefreshLayout;

    public LoadPageView(Context context) {
        this(context, true);
    }

    public LoadPageView(Context context, boolean openRefresh) {
        this(context, null, openRefresh);
    }

    public LoadPageView(Context context, AttributeSet attrs, boolean openRefresh) {
        super(context, attrs);
        this.mIsOpenRefresh = openRefresh;
        initCommonView(context);
    }

    public void setErrorButtonListener(OnErrorButtonListener errorButtonListener) {
        mErrorButtonListener = errorButtonListener;
    }

    protected int getPagerLoading() {
        return R.layout.pager_loading;
    }

    protected int getPagerEmpty() {
        return R.layout.pager_empty;
    }

    protected int getPagerError() {
        return R.layout.pager_error;
    }

    private void initCommonView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);

        mLoadingPager = View.inflate(context, getPagerLoading(), null);
        mEmptyPager = View.inflate(context, getPagerEmpty(), null);
        mErrorPager = View.inflate(context, getPagerError(), null);

        //        mLoadingPager = inflater.inflate(R.layout.pager_loading, this, false);
        //        mEmptyPager = inflater.inflate(R.layout.pager_loading, this, false);
        //        mErrorPager = inflater.inflate(R.layout.pager_loading, this, false);

        this.addView(mEmptyPager, 0);
        this.addView(mErrorPager, 0);
        this.addView(mLoadingPager, 0);

        if (!mIsOpenRefresh) {
            //没有下拉刷新
            mSuccessPager = initSuccessView(inflater, this);
            if (mSuccessPager != null) {
                this.addView(mSuccessPager, 0);
            }
        } else {
            mSuccessPager = View.inflate(context, R.layout.pager_refresh, null);
            mRefreshLayout = (BGARefreshLayout) mSuccessPager.findViewById(R.id.load_page_refresh_container);
            ScrollView scrollView = (ScrollView) mSuccessPager.findViewById(R.id.scroll_container);
            initRefreshLayout(mRefreshLayout);
            View view = initSuccessView(inflater, scrollView);
            if (view != null) {
                scrollView.addView(view, 0);
            }
            if (mSuccessPager != null) {
                this.addView(mSuccessPager, 0);
            }
        }

        mErrorPager.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // loadingDataAndRefreshState();

                mCurrentState = STATE_LOADING;
                refreshViewByState();

                if (mErrorButtonListener != null) {
                    mErrorButtonListener.OnErrorButtonClick(v);
                }
            }
        });

        refreshViewByState();
    }

    // protected abstract void initRefreshLayout(BGARefreshLayout refreshLayout) ;
    protected void initRefreshLayout(BGARefreshLayout refreshLayout) {
        // 为BGARefreshLayout 设置代理
        refreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格
        refreshLayout.setRefreshViewHolder(getRefreshViewHolder());
    }

    @NonNull
    protected BGARefreshViewHolder getRefreshViewHolder() {

        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(AppUtil.getAppContext(), true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.defult_refresh_img_style);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.default_refresh_color_style);
        return moocStyleRefreshViewHolder;
    }

    private void refreshViewByState() {

        mEmptyPager.setVisibility(mCurrentState == STATE_EMPTY ? VISIBLE : GONE);
        mErrorPager.setVisibility(mCurrentState == STATE_ERROR ? VISIBLE : GONE);
        mLoadingPager.setVisibility(mCurrentState == STATE_LOADING ? VISIBLE : GONE);
        if (mSuccessPager != null) {
            mSuccessPager.setVisibility(mCurrentState == STATE_SUCCESS ? VISIBLE : GONE);
        }
    }

    protected abstract View initSuccessView(LayoutInflater inflater, ViewGroup parent);

    /**
     * 外界接口
     *
     * @param viewState
     */
    public void setViewState(LoadDataResult viewState) {
        mCurrentState = viewState.getState();

        //在此判断,防止在错误重试页面,用户不断点击重试按钮
        //记录显示错误页面的次数,次数大于RELOAD_TIMES将显示空白界面
        if (mCurrentState == STATE_ERROR) {
            mErrorCount++;
            if (mErrorCount >  RELOAD_TIMES) {
                mCurrentState = STATE_EMPTY;
            }
            log.d("setViewState():出现错误页面的次数 " + mErrorCount);
        }

        //加载成功后清除计数
        if (mCurrentState == STATE_SUCCESS) {
            resetErrorCount();
        }

        //更新状态
        refreshViewByState();
    }

    public void resetErrorCount() {
        mErrorCount = 0;
    }

    @Override
    public abstract void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout);

    @Override
    public abstract boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout);

    public enum LoadDataResult {

        LOAD_SUCCESS(STATE_SUCCESS), //成功页面
        LOAD_ERROR(STATE_ERROR),//错误页面
        LOAD_EMPTY(STATE_EMPTY), //没有数据的页面
        LOAD_LOADING(STATE_LOADING);//正在加载页面

        private final int mState;

        LoadDataResult(int state) {
            this.mState = state;
        }

        public int getState() {
            return mState;
        }

    }

    public interface OnErrorButtonListener {

        void OnErrorButtonClick(View v);
    }
}
