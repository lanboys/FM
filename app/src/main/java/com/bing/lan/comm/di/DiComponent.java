package com.bing.lan.comm.di;

import com.bing.lan.comm.base.mvp.BaseModule;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/2/10  9:07
 */

@Component(modules = DiModule.class)
public interface DiComponent {

    void inject(BaseModule baseModule);
}
