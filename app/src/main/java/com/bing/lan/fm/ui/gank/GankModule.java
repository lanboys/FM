package com.bing.lan.fm.ui.gank;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.gank.bean.GankBean;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class GankModule extends BaseFragmentModule
        implements IGankContract.IGankModule {

    String Gank_BASE_URL = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";
    // http://gank.io/api/data/%E7%A6%8F%E5%88%A9/4/2

    private String getGankGirlUrl(int count, int page) {
        return Gank_BASE_URL + count + "/" + page;
    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

        Observable<List<GankBean.ResultsBean>> observable = mApiService.getGankGirl(getGankGirlUrl((Integer) parameter[0], (Integer) parameter[1]))
                .filter(new Func1<GankBean, Boolean>() {
                    @Override
                    public Boolean call(GankBean gankBean) {
                        return !gankBean.isError();
                    }
                })
                .map(new Func1<GankBean, List<GankBean.ResultsBean>>() {
                    @Override
                    public List<GankBean.ResultsBean> call(GankBean gankBean) {
                        return gankBean.getResults();
                    }
                });

        mSubscriptions.add(subscribe(
                observable,
                action,
                listener,
                "妹子"
        ));
    }
}
