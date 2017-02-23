package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;

public interface IBaseLoaderStrategy {

    void loadImage(Context ctx, ImageView imageView, String url);

    void loadImage(Context ctx, ImageView imageView, String url, int reqWidth, int reqHeight);

    void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult);

    void loadImage(Context ctx, ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener);

    void loadSmallImage(Context ctx, ImageView imageView, String url);

    void loadBigImage(Context ctx, ImageView imageView, String url);
}
