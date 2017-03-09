package com.bing.lan.comm.base;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * @author 蓝兵
 * @time 2017/1/13  20:37
 */
public abstract class BaseViewHolder<M> {

    public View mView;
    public M mBean;

    protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);

    public BaseViewHolder(View view) {
        this.mView = view;//少了this 要报错
        ButterKnife.bind(this, this.mView);
    }

    public BaseViewHolder(@LayoutRes int resource) {
        this.mView = View.inflate(AppUtil.getAppContext(), resource, null);
        ButterKnife.bind(this, this.mView);
    }

    public abstract void refreshViewData(M bean, int position);

    public void refreshViewData(M bean) {
        refreshViewData(bean, 0);
    }
}