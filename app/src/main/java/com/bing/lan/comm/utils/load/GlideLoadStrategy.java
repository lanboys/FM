package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * @author 蓝兵
 * @time 2017/2/23  22:43
 */
public class GlideLoadStrategy implements IBaseLoaderStrategy {

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url) {

    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, int reqWidth, int reqHeight) {

    }

    @Override
    public void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult) {

    }

    @Override
    public void loadImage(Context ctx, ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener) {

    }

    @Override
    public void loadSmallImage(Context ctx, ImageView imageView, String url) {

    }

    @Override
    public void loadBigImage(Context ctx, ImageView imageView, String url) {

    }
}
