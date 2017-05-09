package com.example.parking.near.model;

import com.example.parking.bean.Parking;

import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public class NearModelImpl implements NearModel {

    @Override
    public List<Parking> loadParkData(String filterType, String sortType) {
        List<Parking> parkingArrayList = Parking.getParkingList(filterType, sortType);
        return parkingArrayList;
    }
}
