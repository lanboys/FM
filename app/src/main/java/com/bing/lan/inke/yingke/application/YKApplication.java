package com.bing.lan.inke.yingke.application;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.orhanobut.logger.Logger;

import java.io.File;


public class YKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        File cache = getCacheFile();

        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(cache)
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();

        Fresco.initialize(this, config);
        Logger.init("hked");
    }

    private File getCacheFile() {
        File sd = Environment.getExternalStorageDirectory();
        File cache = new File(sd, "mm");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }
}
