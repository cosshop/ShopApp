package com.app.shop.shopapp.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.app.shop.shopapp.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class UiUtil {
    private static final boolean DEBUG = true;
    private static final boolean ANIMATE = true;
    public static final int DEFAULT_BG = R.mipmap.ic_launcher;
    public static final int DEFAULT_BIG_BG = R.mipmap.ic_launcher;
    public static final int DEFAULT_SMALL_BG = R.mipmap.ic_launcher;
    private static final int DEFAULT_AVATAR = R.mipmap.ic_launcher;
    private static DisplayImageOptions.Builder defaultDisplayBuidler;
    private static AnimateFirstDisplayListener animateFirstListener = new AnimateFirstDisplayListener();

    static {
        defaultDisplayBuidler = new DisplayImageOptions.Builder()
                .showImageOnLoading(DEFAULT_BG)
                .showImageForEmptyUri(DEFAULT_BG)
                .showImageOnFail(DEFAULT_BG)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .considerExifParams(true)
                .displayer(new SimpleBitmapDisplayer());
    }

    public static void setAvatar(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            setImage(imageView, url, DEFAULT_AVATAR);
        } else {
            imageView.setImageResource(DEFAULT_AVATAR);
        }
    }

    public static void setNoDefualtImage(ImageView imageView, String url){setImage(imageView, url, 0);}

    public static void setImage(ImageView imageView, String url) {
        setImage(imageView, url, DEFAULT_BG);
    }

    public static void setImageSmall(ImageView imageView, String url) {
        setImage(imageView, url, DEFAULT_SMALL_BG);
    }

    public static void setImageBig(ImageView imageView, String url) {
        setImage(imageView, url, DEFAULT_BIG_BG);
    }

    public static void setImage(ImageView imageView, String url, int defaultBg) {
        setImage(imageView, url, defaultBg, ImageView.ScaleType.CENTER_CROP, animateFirstListener);
    }

    public static void setImage(ImageView imageView, String url, int defaultBg, ImageView.ScaleType scaleType) {
        setImage(imageView, url, defaultBg, scaleType, animateFirstListener);
    }

    /**
     * @param imageView
     * @param url
     * @param defaultBg
     * @param scaleType
     * @param listener
     */
    public static void setImage(ImageView imageView, String url, int defaultBg, ImageView.ScaleType scaleType, SimpleImageLoadingListener listener) {
        if (imageView.getTag() != null && imageView.getTag().equals(url)) {
            return;
        }
            if (TextUtils.isEmpty(url)) {
                imageView.setImageResource(defaultBg);

                return;
            }
            defaultDisplayBuidler.showImageOnLoading(defaultBg);
            imageView.setScaleType(scaleType);
            ImageLoader.getInstance().displayImage(url, imageView, defaultDisplayBuidler.build(), listener);
    }

    public static void saveImage2Local(String url, SimpleImageLoadingListener listener) {
        ImageLoader.getInstance().loadImage(url, listener);
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null && ANIMATE) {
                ImageView imageView = (ImageView) view;
                imageView.setTag(imageUri);
                FadeInBitmapDisplayer.animate(imageView, 500);
            }
        }
    }
}