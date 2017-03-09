package com.bing.lan.fm.ui.top;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.fm.ui.top.bean.TopBean;

import rx.Observable;
import rx.functions.Func1;

import static com.bing.lan.fm.ui.top.TopPresenter.LOAD_TOP_MAIN;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class TopModule extends BaseFragmentModule
        implements ITopContract.ITopModule {



    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_TOP_MAIN :
                loadTopMain(action,listener);
                break;
        }
    }

    private void loadTopMain(int action, IBaseContract.OnDataChangerListener listener) {
        Observable<TopBean> observable = mApiService.getTopResult()
                .filter(new Func1<TopBean, Boolean>() {
                    @Override
                    public Boolean call(TopBean topResult) {
                        if (!(topResult.getRet() == 0)) {
                            throw new RuntimeException("请求数据失败,错误信息:" + topResult.getMsg());
                        }
                        return            true;
                      //  return            true;
                    }
                });
        subscribe(
                observable,
                action,
                listener,
                "榜单页面");
    }
}
