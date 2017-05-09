package com.example.parking.near.model;

import com.example.parking.bean.Parking;

import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public interface NearModel {
    void loadParkData(String filterType, String sortType, NearModelImpl.OnLoadListener
            onLoadListener);
}
