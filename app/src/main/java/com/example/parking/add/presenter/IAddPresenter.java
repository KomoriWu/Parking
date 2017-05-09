package com.example.parking.add.presenter;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public interface IAddPresenter {
    void searchParking(Context context, LatLonPoint latLonPoint);
}
