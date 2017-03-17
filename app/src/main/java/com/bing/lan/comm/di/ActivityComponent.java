package com.bing.lan.comm.di;

import com.bing.lan.fm.ui.album.AlbumActivity;
import com.bing.lan.fm.ui.anchordetail.AnchorDetailActivity;
import com.bing.lan.fm.ui.categorydetails.CategoryDetailActivity;
import com.bing.lan.fm.ui.main.MainActivity;
import com.bing.lan.fm.ui.music.MusicActivity;
import com.bing.lan.fm.ui.pic.PictureActivity;
import com.bing.lan.fm.ui.search.SearchActivity;
import com.bing.lan.fm.ui.splash.SplashActivity;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/1/10  11:02
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);

    void inject(PictureActivity pictureActivity);

    void inject(SearchActivity searchActivity); 

    void inject(AlbumActivity albumActivity);

    void inject(MusicActivity musicActivity);

    void inject(AnchorDetailActivity anchorDetailActivity);

    void inject(CategoryDetailActivity categoryDetailActivity);
}

