package com.bing.lan.comm.utils.load;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapCacheUtils {

    public static boolean checkCacheBitmap(String md5) {
        File file = new File(AppUtil.getCacheDir(), md5);
        return file.exists();
    }

    public static boolean cacheBitmap(InputStream is, String md5) {
        OutputStream out = null;
        try {
            File cacheFile = AppUtil.getCacheFile(md5);
            out = new FileOutputStream(cacheFile);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtils.close(out);
        }
        return true;
    }

    public static Bitmap getCacheBitmap(String md5) {
        return BitmapFactory.decodeFile(AppUtil.getCacheFile(md5).getAbsolutePath());
    }
}
