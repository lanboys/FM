package com.bing.lan.comm.base.mvp.fragment.refresh;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.base.MyBaseProtocol;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.comm.utils.RxJavaUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/1/12  11:52
 */
public abstract class AbsRefreshModule<JSONDATA, DATA> extends BaseFragmentModule
        implements IAbsRefreshContract.IAbsRefreshModule {

    protected Subscription mLatestSubscribe;
    private Runnable mLoadDataTask;
    private MyBaseProtocol<DATA> mProtocol;

    public void loadImage(Object path, ImageView imageView) {
        // Picasso.with(AppUtil.getAppContext()).load((String) path).into(imageView);
        //Glide可以加载 path 为object的路径,比图本地资源文件  R.mipmap.ic_launcher
        // Glide.with(AppUtil.getAppContext())
        //         .load(path)
        //         .crossFade()
        //         .into(imageView);

        // ImageLoaderManager.loadRefreshImage(AppUtil.getAppContext(), imageView, (String) path);
    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

    }

    // public Runnable getLoadDataTask() {
    //     return mLoadDataTask;
    // }

    public boolean isLoading() {
        // TODO: 2017/1/27 用什么做正在加载中的判断
        // boolean b = mLatestSubscribe == null || mLatestSubscribe.isUnsubscribed();
        // return !b;

        return mLoadDataTask != null;
    }

    public void loadData(int action, final int index, final IBaseContract.OnDataChangerListener loadDataListener) {

        //     if (mProtocol == null) {
        //         mProtocol = getBaseProtocol();
        //     }
        //     mLoadDataTask = new Runnable() {
        //         @Override
        //         public void run() {
        //             // SystemClock.sleep(2000);
        //             try {
        //
        //                 String url = getUrl(index);
        //                 // Object o = mProtocol.getParseResults(url);
        //                 DATA parseResults = mProtocol.getParseResults(url);
        //                 log.d("run():parseResults " + parseResults);
        //                 loadDataListener.onSuccess(parseResults);
        //             } catch (IOException e) {
        //                 log.e("OnError: 加载数据失败 ", e);
        //                 loadDataListener.onError(e);
        //             } finally {
        //                 mLoadDataTask = null;
        //             }
        //         }
        //     };
        //     //线程池里面加载
        //     ThreadPoolProxyFactory.createNormalThreadPoolProxy().execute(mLoadDataTask);
        //
        //     loadData(index, loadDataListener, 0);
        // }
        //
        // private void loadData(int index, final OnLoadDataListener<DATA> loadDataListener, int i) {

        Observable<JSONDATA> dataObservable = getDataObservable(ApiManager.getApiService(), index);

        log.d("loadData(): 正在加载数据");
        mLatestSubscribe = dataObservable
                .map(new Func1<JSONDATA, DATA>() {
                    @Override
                    public DATA call(JSONDATA jsondata) {
                        return DataTransform(jsondata);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DATA>() {
                    @Override
                    public void onNext(DATA data) {
                        log.d("onNext():data " + data.toString());
                        loadDataListener.onSuccess(0, data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadDataListener.onError(0, e);
                        // log.e("OnError: 加载数据失败 ", e);
                        log.e("onError(): 加载数据失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    protected Observable<JSONDATA> getDataObservable(ApiService apiService, int index) {
        return null;
    }

    protected DATA DataTransform(JSONDATA jsondata) {
        return (DATA) jsondata;
    }

    public void releaseSubscribe() {
        RxJavaUtils.releaseSubscribe(mLatestSubscribe);
    }

    /**
     * 获取url
     *
     * @param index
     * @return
     */
    protected String getUrl(int index) {
        return null;
    }

    /**
     * 将从url访问到的json数据转换成bean 或 list
     *
     * @param jsonStr
     * @return
     */
    protected DATA json2BeanOrList(String jsonStr) {
        return null;
    }

    @NonNull
    protected MyBaseProtocol<DATA> getBaseProtocol() {

        return new MyBaseProtocol<DATA>() {
            @Override
            protected DATA json2BeanOrList(String jsonStr) {
                return AbsRefreshModule.this.json2BeanOrList(jsonStr);
            }
        };

        // return new AbsRefreshProtocol();
    }

    // class AbsRefreshProtocol extends MyBaseProtocol<DATA> {
    //
    //     @Override
    //     protected DATA json2BeanOrList(String jsonStr) throws Exception {
    //         return AbsRefreshModule.this.json2BeanOrList(jsonStr);
    //     }
    // }

    // protected interface OnDataChangerListener<DATA> {
    //
    //     void onSuccess(DATA data);
    //
    //     void onError(Throwable e);
    // }

    // interface OnDataChangerListener {
    //
    //     void onSuccess(int action, Object data);
    //
    //     void onError(int action, Throwable e);
    // }
}
