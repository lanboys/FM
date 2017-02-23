package com.bing.lan.fm.ui.pic;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class PictureModule extends BaseActivityModule
        implements IPictureContract.IPictureModule {

    @Override
    public void releaseTask() {

    }

    // @Override
    // public void loadImage(Object path, ImageView imageView) {
        // ImagePicassoUtil.loadImage(imageView, (String) path);
        // com.bing.lan.comm.utils.load.ImageLoader.getInstance().loadImage(imageView, (String) path);
    // }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

    }
}
