package com.bing.lan.comm.base.mvp;

import android.widget.ImageView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.api.BaseSubscriber;
import com.bing.lan.comm.di.DaggerDiComponent;
import com.bing.lan.comm.di.DiModule;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.utils.RxJavaUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

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
    protected List<Subscription> mSubscriptions=new ArrayList<>();

    public BaseModule() {
        DaggerDiComponent.builder()
                .diModule(new DiModule(getClass()))
                .build()
                .inject(this);
    }

    @Override
    public void releaseTask() {
        if (mSubscriptions != null && mSubscriptions.size() > 0) {
            for (Subscription subscription : mSubscriptions) {
                RxJavaUtils.releaseSubscribe(subscription);
            }
            mSubscriptions.clear();
        }
    }

    @Override
    public void loadImage(Object path, ImageView imageView) {
        // Picasso.with(AppUtil.getAppContext()).load((String) path).into(imageView);
        // Glide可以加载 path 为object的路径,比图本地资源文件  R.mipmap.ic_launcher
        Glide.with(AppUtil.getAppContext())
                .load(path)
                .crossFade()
                .into(imageView);

        // ImageLoaderManager.loadRefreshImage(AppUtil.getAppContext(), imageView, (String) path);
    }

    protected <T> Subscription subscribe(Observable<T> observable,
            int action,
            IBaseContract.OnDataChangerListener listener,
            String onNextString) {

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubscriber.newBuilder()
                        .action(action)
                        .log(log)
                        .dataChangelistener(listener)
                        .description(onNextString)
                        .build());
    }
}
