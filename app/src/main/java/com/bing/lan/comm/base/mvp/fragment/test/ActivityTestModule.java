package com.bing.lan.comm.base.mvp.fragment.test;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class ActivityTestModule extends BaseActivityModule
        implements IActivityTestContract.IActivityTestModule {



    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

    }
}
