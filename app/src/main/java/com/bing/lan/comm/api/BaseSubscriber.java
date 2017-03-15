package com.bing.lan.comm.api;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.utils.LogUtil;

import rx.Subscriber;

/**
 * @author 蓝兵
 * @time 2017/2/17  0:04
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    // protected final LogUtil log = LogUtil.getLogUtil(getClass(), LogUtil.LOG_VERBOSE);
    private LogUtil log;
    private IBaseContract.OnDataChangerListener listener;
    private String mDescription;
    private int action;

    public BaseSubscriber(Builder builder) {
        this.action = builder.action;
        this.listener = builder.listener;
        this.mDescription = builder.mDescription;
        this.log = builder.log;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    // public static <T> Builder<T> newBuilder() {
    //     return new Builder<T>();
    // }

    @Override
    public void onNext(T bean) {
        if (log != null) {
            log.i("onNext():加载 " + mDescription + " 成功: " + bean.toString());
        }
        if (listener != null) {
            listener.onSuccess(action, bean);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (log != null) {
            log.e("onError(): 加载 " + mDescription + " 失败: " + e);
        }
        if (listener != null) {
            listener.onError(action, e);
        }
    }

    @Override
    public void onCompleted() {
        if (log != null) {
            log.i("onCompleted(): 加载 " + mDescription + " 完成");
        }
        if (listener != null) {
            listener.onCompleted(action);
        }
    }

    public static final class Builder {

        IBaseContract.OnDataChangerListener listener;
        String mDescription;
        int action;
        LogUtil log;

        private Builder() {

        }

        public Builder action(int action) {
            this.action = action;
            return this;
        }

        public Builder dataChangeListener(IBaseContract.OnDataChangerListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder description(String description) {
            this.mDescription = description;
            return this;
        }

        public Builder log(LogUtil log) {
            this.log = log;
            return this;
        }

        public <M> Subscriber<M> build() {
            return new BaseSubscriber<M>(this);
        }
    }
}