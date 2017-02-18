package com.bing.lan.comm.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;

/**
 * @author 小码哥Android学院
 * @time 2016/12/27 11:18
 * @desc
 */
public class ImageLoadUtil {

    private static ImageLoadUtil sLoadUtil;
    private final LruCache<String, Bitmap> mLruCache;

    private ImageLoadUtil() {
        //准备一个LruCache
        //KEY   path(图片的路径是不同的，就作为key来用)
        //value bitmap
        //        int maxSize = 最大可用内存的八分之一
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        //前提一：缓存的最大上限
        //前提二：要告诉lruCache，存在里面的每个对象的大小
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //确定存在LruCache中的每个对象的内存大小
                return value.getByteCount();
            }
        };
    }

    public static ImageLoadUtil getInstance() {
        if (sLoadUtil == null) {
            synchronized (ImageLoadUtil.class) {
                if (sLoadUtil == null) {
                    sLoadUtil = new ImageLoadUtil();
                }
            }
        }
        return sLoadUtil;
    }

    public static Bitmap loadBitmapBySize(String path, int targetWidth, int targetHeight) {
        float max = getBitmapScale(path, targetWidth, targetHeight);
        return LoadBitmapByScale(path, (int) max);
    }

    private static Bitmap LoadBitmapByScale(String path, int scale) {
        BitmapFactory.Options options = null;
        if (scale > 0) {
            options = new BitmapFactory.Options();
            options.inSampleSize = scale;
        }

        //真正的加载图片像素数据
        // options.inJustDecodeBounds = false;

        //配置一下色彩的Config
        // options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        // options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeFile(path, options);
    }

    private static float getBitmapScale(String path, int targetWidth, int targetHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //得知道原图的尺寸  目标尺寸
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        Log.e("tag", "outWidth:" + outWidth);
        Log.e("tag", "outHeight:" + outHeight);

        //计算inSampleSize
        float scaleW = (outWidth * 1.0f) / targetWidth;
        float scaleH = (outHeight * 1.0f) / targetHeight;
        //取大的
        float max = Math.max(scaleW, scaleH);
        Log.e("xmg", "scaleW:" + scaleW + " scaleH:" + scaleH + " max:" + max + " inSampleSize:" + options.inSampleSize);
        return max;
    }

    public Bitmap loadBitmap(String path) {
        return loadBitmap(path, 0);
    }

    private Bitmap loadBitmap(String path, int scale) {
        //先从内存缓存中去读取，内存缓存通过LruCache来做了
        Bitmap bitmap = mLruCache.get(path);
        //1 读取不到数据，为Null
        if (bitmap == null) {
            //1.1 从本地加载
            bitmap = LoadBitmapByScale(path, scale);
            Log.e("xmg", "从本地加载图片");
            //1.2 放到内存缓存中
            mLruCache.put(path, bitmap);
            //1.3 返回出去
            return bitmap;
        }
        //2 读取到数据，直接返回出去
        Log.e("xmg", "从LruCache内存缓存中加载图片");
        return bitmap;
    }
}
