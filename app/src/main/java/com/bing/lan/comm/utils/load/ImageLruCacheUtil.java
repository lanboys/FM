package com.bing.lan.comm.utils.load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.bing.lan.comm.utils.LogUtil;

//注意要在后台线程进行加载图片
public class ImageLruCacheUtil {

    protected static final LogUtil log = LogUtil.getLogUtil(ImageLruCacheUtil.class, LogUtil.LOG_VERBOSE);
    private static ImageLruCacheUtil sLoadUtil;
    private final LruCache<String, Bitmap> mLruCache;

    private ImageLruCacheUtil() {
        //准备一个LruCache
        //KEY   path(图片的路径是不同的，就作为key来用)
        //value bitmap
        //        int maxSize = 最大可用内存的八分之一
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        //前提一：缓存的最大上限
        //前提二：要告诉lruCache，存在里面的每个对象的大小
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //确定存在LruCache中的每个对象的内存大小
                return bitmap.getByteCount();
            }
        };
    }

    public static ImageLruCacheUtil getInstance() {
        if (sLoadUtil == null) {
            synchronized (ImageLruCacheUtil.class) {
                if (sLoadUtil == null) {
                    sLoadUtil = new ImageLruCacheUtil();
                }
            }
        }
        return sLoadUtil;
    }

    private static float getBitmapScale(String path, int targetWidth, int targetHeight) {

        if (targetWidth == 0 || targetHeight == 0) {
            return 0;// 0 表示按原来比例大小加载(默认加载方式)
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        //得知道原图的尺寸  目标尺寸
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        log.i("getBitmapScale(): outWidth:" + outWidth);
        log.i("getBitmapScale(): outHeight:" + outHeight);

        //计算inSampleSize
        float scaleW = (outWidth * 1.0f) / targetWidth;
        float scaleH = (outHeight * 1.0f) / targetHeight;
        //取大的
        float max = Math.max(scaleW, scaleH);

        log.i("getBitmapScale():scaleW:" + scaleW + " scaleH:"
                + scaleH + " max:" + max + " inSampleSize:" + options.inSampleSize);
        return max;
    }

    public void addBitmapToLruCache(String key, Bitmap bitmap) {
        if (mLruCache.get(key) == null) {
            mLruCache.put(key, bitmap);
        }
    }

    public Bitmap loadBitmap(String path) {
        return loadBitmapFromLru(path, 0);
    }

    //根据大小加载本地图片
    public Bitmap loadBitmap(String path, int targetWidth, int targetHeight) {
        //获取尺寸比例
        float max = getBitmapScale(path, targetWidth, targetHeight);
        return loadBitmapFromLru(path, (int) max);
    }

    //根据比例加载内存图片
    private Bitmap loadBitmapFromLru(String path, int scale) {
        //先从内存缓存中去读取，内存缓存通过LruCache来做了
        Bitmap bitmap = mLruCache.get(path);
        //1 读取不到数据，为Null
        if (bitmap == null) {
            //1.1 从本地加载
            bitmap = LoadBitmapFromDisk(path, scale);
            log.i("loadBitmapFromLru(): 从本地加载图片");
            //1.2 放到内存缓存中
            mLruCache.put(path, bitmap);
            //1.3 返回出去
            return bitmap;
        }
        //2 读取到数据，直接返回出去
        log.i("loadBitmapFromLru():从LruCache内存缓存中加载图片");
        return bitmap;
    }

    //根据比例加载本地大图片(会根据给定比例对原图片进行压缩加载)
    private Bitmap LoadBitmapFromDisk(String path, int scale) {
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

        //注意要在后台线程进行加载图片
        return BitmapFactory.decodeFile(path, options);
    }
}
