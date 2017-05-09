package com.example.parking.add.model;

import android.content.Context;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public interface IAddModel {
    void startSearch(Context context, OnParkSearchListener listener, LatLonPoint latLonPoint);
}
