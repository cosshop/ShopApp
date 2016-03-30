package com.app.shop.shopapp;

import android.app.Application;

import com.app.shop.shopapp.config.AppDir;
import com.app.shop.shopapp.utils.JUtils;
import com.jiongbull.jlog.JLog;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * <请描述这个类是干什么的>
 *
 * @author lwli
 * @data: 2016/3/11 15:17
 * @version: V1.0
 */
public class App extends Application {
    public  static App instance;
    public static App getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        instance=this;
        JLog.init(this)
                .setDebug(BuildConfig.DEBUG);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPriority(Thread.NORM_PRIORITY - 2)// 加载图片的线程数
                .denyCacheImageMultipleSizesInMemory() // 解码图像的大尺寸将在内存中缓存先前解码图像的小尺寸。
                .memoryCacheExtraOptions(400, 480) // max width, max height，即保存的每个缓存文件的最大长宽   //设置图片的大小
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCacheSize(5* 1024 * 1024*1024)
                .diskCache(new UnlimitedDiskCache(AppDir.getDir(AppDir.AppDirEnum.UPLOAD_IMAGE_TEMP)))
                .diskCacheFileCount(1500) //缓存的文件数量
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .build();
        ImageLoader.getInstance().init(config);//全局初始化此配置
    }
}
