package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * @author 蓝兵
 * @time 2017/2/23  21:58
 */
public class FrescoLoadStrategy implements IBaseLoaderStrategy {

    public static void init(Context context) {
          Phoenix.init(context);
    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url) {
        com.facebook.fresco.helper.ImageLoader.loadImage((SimpleDraweeView) imageView, url);
    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, int reqWidth, int reqHeight) {
        com.facebook.fresco.helper.ImageLoader.loadImage((SimpleDraweeView) imageView, url, reqWidth, reqHeight);
    }

    //reqWidth/reqHeight 有什么用
    @Override
    public void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult) {
        com.facebook.fresco.helper.ImageLoader.loadImage(context, url, reqWidth, reqHeight, loadImageResult);
    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener) {
        com.facebook.fresco.helper.ImageLoader.loadImage((SimpleDraweeView) imageView, url, controllerListener);
    }

    @Override
    public void loadBigImage(Context ctx, ImageView imageView, String url) {
        // TODO: 2017/2/23
        com.facebook.fresco.helper.ImageLoader.loadImage((SimpleDraweeView) imageView, url);
    }

    @Override
    public void loadSmallImage(Context ctx, ImageView imageView, String url) {
        com.facebook.fresco.helper.ImageLoader.loadImageSmall((SimpleDraweeView) imageView, url);
    }
}
