package com.example.parking.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orm.SugarApp;

import c.b.BP;

/**
 * Created by KomoriWu
 * on 2017-04-18.
 */

public class MyApplication extends SugarApp {
    private static ImageLoader mImageLoader;
    private static Application sInstance;
    public static final String APP_PAY_ID = "d2545b0db7cf58434db4802ee16fa95e";
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        BP.init(APP_PAY_ID);
    }

    public static ImageLoader getImageLoader(Context context) {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = ImageLoader.getInstance();
                    mImageLoader.init(ImageLoaderConfiguration.createDefault(context.
                            getApplicationContext()));
                }
            }
        }
        return mImageLoader;
    }

    public static Application getAppContext() {
        return sInstance;
    }

}
