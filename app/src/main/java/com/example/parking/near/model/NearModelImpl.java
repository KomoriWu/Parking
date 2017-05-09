package com.example.parking.near.model;

import com.example.parking.bean.Parking;

import java.util.ArrayList;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public interface NearModelImpl {
    ArrayList<Parking> loadParkData(String distance, String price);
}
