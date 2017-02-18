package com.bing.lan.fm.ui.hot;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.hot.bean.HotResult;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.hot.HotPresenter.LOAD_HOT_MAIN;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class HotModule extends BaseFragmentModule
        implements IHotContract.IHotModule {

    @Override
    public void loadData(final int action, final IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_HOT_MAIN:
                loadHotMain(action, listener);
                break;
        }
    }

    /**
     * 有一个方法 Observable.doOnSubscribe() 。它和 Subscriber.onStart() 同样
     * 是在 subscribe() 调用后而且在事件发送前执行，但区别在于它可以指定线程。
     * 默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；而如果在
     * doOnSubscribe() 之后有 subscribeOn() 的话，它将执行在离它最近的
     * subscribeOn() 所指定的线程。
     */

    private void loadHotMain(final int action, final IBaseContract.OnDataChangerListener listener) {

        Observable<HotResult> observable = mApiService.getHotResult()
                .filter(new Func1<HotResult, Boolean>() {
                    @Override
                    public Boolean call(HotResult hotResult) {
                        if (!(hotResult.getRet() == 0)) {
                            throw new RuntimeException("请求数据失败,错误信息:" + hotResult.getMsg());
                        }
                        return true;
                    }
                })
             /*   .map(new Func1<HotResult, List<HotInfoBean>>() {
                    @Override
                    public List<HotInfoBean> call(HotResult hotResult) {
                        List<HotInfoBean> hotInfos = new ArrayList<>();
                        hotInfos.add(hotResult.getFocusImages());
                        hotInfos.add(hotResult.getEditorRecommendAlbums());
                        hotInfos.add(hotResult.getSpecialColumn());
                        return hotInfos;
                    }
                })*/;
        // .doOnSubscribe(new Action0() {
        //     @Override
        //     public void call() {
        //         // 需要在主线程执行
        //         log.d("call(): 在事件发送之前执行");
        //     }
        // })
        // .subscribeOn(AndroidSchedulers.mainThread())
        // .doOnNext(new Action1<List<HotResult.HotInfoBean>>() {
        //     @Override
        //     public void call(List<HotResult.HotInfoBean> hotInfoBeen) {
        //在,最终订阅之前做一些操作,跟filter,map,filterMap区别在于,不用对数据做处理
        // (官方说的不改变数据流),可以另外开启一个线程,进行缓存,重试,调试等操作,
        //         Schedulers.io().createWorker().schedule(new Action0() {
        //             @Override
        //             public void call() {
        //                 try {
        //                     Thread.sleep(2000);
        //                 } catch (Exception e) {
        //                     e.printStackTrace();
        //                 }
        //             }
        //         });
        //     }
        // });

        mSubscriptions.add(subscribe(
                observable,
                action,
                listener,
                "热门页面"));
    }
}
