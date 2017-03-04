package com.bing.lan.comm.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;

public class LoadMoreRecyclerView extends RecyclerView {

    private LoadMoreListener mListener;
    private int preScrollState;

    public LoadMoreRecyclerView(Context context) {
        super(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setLoadMoreListener(LoadMoreListener listener) {
        mListener = listener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE://停止滑动
                if (Fresco.getImagePipeline().isPaused())
                    Fresco.getImagePipeline().resume();
                //检测是否需要加载更多
                loadMoreCheck();
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                if (preScrollState == RecyclerView.SCROLL_STATE_SETTLING) {
                    //触摸滑动不需要加载
                    Fresco.getImagePipeline().pause();
                } else {
                    //触摸滑动需要加载
                    if (Fresco.getImagePipeline().isPaused())
                        Fresco.getImagePipeline().resume();
                }
                break;
            case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                Fresco.getImagePipeline().pause();
                break;
        }
        preScrollState = state;
    }

    private void loadMoreCheck() {

        if (mListener != null) {
            int lastVisibleItemPosition = 0;
            LayoutManager layoutManager = getLayoutManager();

            if (layoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] lastPosition = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPosition);
                lastVisibleItemPosition = getMax(lastPosition);
            }

            if (lastVisibleItemPosition == layoutManager.getItemCount() - 1) {
                mListener.loadMore(this,lastVisibleItemPosition);
            }
        }
    }

    private int getMax(int[] lastPosition) {
        int max = lastPosition[0];
        for (int value : lastPosition) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public interface LoadMoreListener {

        void loadMore(LoadMoreRecyclerView loadMoreRecyclerView, int lastVisibleItemPosition);
    }
}
