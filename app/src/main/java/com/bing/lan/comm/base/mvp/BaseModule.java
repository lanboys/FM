package com.bing.lan.comm.base.mvp;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.api.BaseSubscriber;
import com.bing.lan.comm.di.DaggerDiComponent;
import com.bing.lan.comm.di.DiModule;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.RxJavaUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/1/12  18:48
 */
public abstract class BaseModule implements IBaseContract.IBaseModule {

    @Inject
    protected ApiService mApiService;

    @Inject
    protected LogUtil log;
    protected Map<String, Subscription> mSubscriptions = new HashMap<>();

    public BaseModule() {
        DaggerDiComponent.builder()
                .diModule(new DiModule(getClass()))
                .build()
                .inject(this);
    }

    @Override
    public void releaseTask() {
        if (mSubscriptions != null && mSubscriptions.size() > 0) {
            for (Subscription subscription : mSubscriptions.values()) {
                RxJavaUtils.releaseSubscribe(subscription);
            }
            mSubscriptions.clear();
        }
    }

    // @Override
    // public void loadImage(Object path, ImageView imageView) {
    //
    //     // Glide可以加载 path 为object的路径,比图本地资源文件  R.mipmap.ic_launcher
    //
    //     // Glide.with(AppUtil.getAppContext())
    //     //         .load(path)
    //     //         .crossFade()
    //     //         .into(imageView);
    //
    //     // ImagePicassoUtil.loadImage(imageView, (String) path);
    //     // ImagePicassoUtil.loadBigImage(imageView, (String) path);
    //
    //     ImageLoader.getInstance().loadBigImage(imageView, (String) path);
    // }

    protected <T> Subscription subscribe(Observable<T> observable,
            int action,
            IBaseContract.OnDataChangerListener listener,
            String onNextString) {

        Subscription subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubscriber.newBuilder()
                        .action(action)
                        .log(log)
                        .dataChangeListener(listener)
                        .description(onNextString)
                        .build());
        mSubscriptions.put(String.valueOf(action), subscribe);
        return subscribe;
    }

    /**
     * 任务完成或者发生错误的时候调用
     *
     * @param action
     */
    @Override
    public void refreshTask(int action) {
        mSubscriptions.remove(String.valueOf(action));
    }

    @Override
    public void requestData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        Subscription subscribe = mSubscriptions.get(String.valueOf(action));
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            //任务正在进行中
            if (listener != null) {
                log.i("requestData(): 有任务正在进行哦 action: " + action);
                listener.onLoading(action);
            }
        } else {
            //没有任务进行
            loadData(action, listener, parameter);
        }
    }

    protected abstract void loadData(int action, IBaseContract.OnDataChangerListener listener, Object[] parameter);
}
