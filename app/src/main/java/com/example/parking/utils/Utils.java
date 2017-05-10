package com.example.parking.utils;

import android.content.Context;
import android.content.Intent;
import android.Manifest;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.amap.api.navi.model.NaviLatLng;
import com.example.parking.R;
import com.example.parking.add.navi.NavigationActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public class Utils {
    public static final String DISTANCE_MIN = "distance_min";
    public static final String DISTANCE_MAX = "distance_max";
    public static final String PARKING = "parking";
    public static final String TEXT_POI_KEY = "停车场";
    public static final String TEXT_POI_TYPE = "交通设施服务";
    public static final int POI_SEARCH_BOUND = 10000;
    public static final int POI_SEARCH_COUNT = 32;
    //讯飞语音
    public static final String XUN_FEI_APP_ID = "57d0dad1";
    public static final int POI_SEARCH_COUNT = 50;

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).setAction(R.string.know,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    //显示导航Activity
    public static void showNaviActivity(Context context, NaviLatLng startLatlng, NaviLatLng endLatlng) {
        Intent intent = new Intent(context, NavigationActivity.class);
        intent.putExtra(NavigationActivity.EXTRA_START_POINT, startLatlng);
        intent.putExtra(NavigationActivity.EXTRA_END_POINT, endLatlng);
        context.startActivity(intent);
    }


    public static String getPermission(int position) {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        return permissions[position];
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId) {
        return getImageOptions(defaultIconId, 0);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageOnFail(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisc()
                .build();
    }
}
