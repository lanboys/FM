package com.bing.lan.comm.utils.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.helper.listener.IResult;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * @author 蓝兵
 * @time 2017/2/23  21:55
 */
public class ImageLoader {

    private static volatile ImageLoader instance = null;
    private IBaseLoaderStrategy mStrategy;

    private ImageLoader() {
        mStrategy = new FrescoLoadStrategy();
    }

    public static void init(Context context) {
        FrescoLoadStrategy.init(context);
    }

    public static ImageLoader getInstance() {

        if (instance == null) {
            synchronized (ImageLoader.class) {
                if (instance == null) {
                    instance = new ImageLoader();
                }
            }
        }
        return instance;
    }

    public void loadImage(ImageView imageView, String url) {
        mStrategy.loadImage(imageView.getContext(), imageView, url);
    }

    public void loadImage(ImageView imageView, String url, int reqWidth, int reqHeight) {
        mStrategy.loadImage(imageView.getContext(), imageView, url, reqWidth, reqHeight);
    }

    public void loadImage(ImageView imageView, String url, ControllerListener<ImageInfo> controllerListener) {
        mStrategy.loadImage(imageView.getContext(), imageView, url, controllerListener);
    }

    public void loadBigImage(ImageView imageView, String url) {
        mStrategy.loadBigImage(imageView.getContext(), imageView, url);
    }

    public void loadSmallImage(ImageView imageView, String url) {
        mStrategy.loadSmallImage(imageView.getContext(), imageView, url);
    }

    public void loadImage(Context context, String url, int reqWidth, int reqHeight, IResult<Bitmap> loadImageResult) {
        mStrategy.loadImage(context, url, reqWidth, reqHeight, loadImageResult);
    }
}
