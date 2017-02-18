package com.bing.lan.comm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bing.lan.comm.config.AppConfig;
import com.bing.lan.fm.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;




/**
 * @author 赵坤
 * @email artzok@163.com
 */
public class ImageLoaderManager {
    private static final int EMPTY_GONE = -1;
    private static final int EMPTY_DISABLE = -2;
    private static final int EMPTY_NONE = -3;

    public static void loadImage(  ImageView imageView, String url) {
        loadImage(AppUtil.getAppContext(), imageView, url, EMPTY_GONE, R.drawable.image_default_202, R.drawable.image_default_202);
    }
    //
    // public static void loadCardItemImage(Context context, ImageView imageView, String url) {
    //     loadImage(context, imageView, url, R.drawable.ic_logo, R.drawable.default_splash, R.drawable.ic_logo);
    // }
    //
    // public static void loadRefreshImage(Context context, ImageView imageView, String url) {
    //     loadImage(context, imageView, url, R.mipmap.ic_launcher, R.drawable.ic_logo , R.drawable.ic_logo);
    // }
    //
    // public static void loadBannerItemImage(Context context, ImageView imageView, String url) {
    //     loadImage(context, imageView, url, EMPTY_NONE, R.drawable.default_splash, R.drawable.default_splash);
    // }
    //
    // public static void loadAvatarImage(Context context, ImageView imageView, String url) {
    //     loadImage(context, imageView, url, R.drawable.account_avatar, R.drawable.account_avatar, R.drawable.account_avatar);
    // }
    //
    // public static void loadHeaderImage(Context context, ImageView imageView, String url) {
    //     loadImage(context, imageView, url, EMPTY_NONE, R.drawable.default_splash, R.drawable.ic_logo);
    // }
    //
    // public static Bitmap getImage(Context context, String url) throws IOException {
    //     return Picasso.with(context).load(url).get();
    // }

    private static void loadImage(Context context,
                                  ImageView imageView,
                                  String url,
                                  int empty,
                                  int loading,
                                  int error) {
        // 重置图片状态
        imageView.setVisibility(View.VISIBLE);
        // 根据
        if (TextUtils.isEmpty(url)) {
            //图片url为空时的状态
            switch (empty) {
                case EMPTY_GONE:
                    imageView.setVisibility(View.GONE);
                    break;
                case EMPTY_DISABLE:
                    imageView.setVisibility(View.INVISIBLE);
                    break;
                case EMPTY_NONE:
                    imageView.setBackgroundColor(AppUtil.getColor(R.color.colorPrimary));
                    break;
                default:
                    imageView.setImageResource(empty);
            }
            return;
        }

        if (SPUtil.getBoolean(AppConfig.SETTING_NO_IMAGE)) {
            imageView.setImageResource(loading);
            imageView.setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
            return;
        } else {
            imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        }

        Picasso.with(context).load(url)
                .placeholder(loading)
                .error(error)
                .into(imageView);
    }

    public static void saveShareImage(String url, final SaveImageCallBack callBack) {
        final File file = new File(AppUtil.getAppContext().getExternalCacheDir(),
                MD5Util.MD5(url) + ".jpeg");
        if (file.exists()) {
            callBack.callBack(file);
            return;
        }
        Picasso.with(AppUtil.getAppContext()).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                OutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(os);
                }
                AppUtil.postTaskSafe(new Runnable() {
                    @Override
                    public void run() {
                        callBack.callBack(file);
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    public interface SaveImageCallBack {
        void callBack(File f);
    }
}
