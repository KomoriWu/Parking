package com.example.parking.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.parking.R;

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
    public static final int POI_SEARCH_COUNT = 50;

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).setAction(R.string.know,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }
}
