package com.bing.lan.comm.di;

import com.bing.lan.fm.ui.gank.GankFragment;
import com.bing.lan.fm.ui.girl.GirlFragment;
import com.bing.lan.fm.ui.home.HomeFragment;
import com.bing.lan.fm.ui.hot.HotFragment;
import com.bing.lan.fm.ui.mine.MineFragment;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(MineFragment mineFragment);

    void inject(HomeFragment homeFragment);

    void inject(HotFragment hotFragment);

    void inject(GankFragment gankFragment);

    void inject(GirlFragment girlFragment);
}