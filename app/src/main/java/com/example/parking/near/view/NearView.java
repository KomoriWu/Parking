package com.example.parking.near.view;

import com.example.parking.bean.Parking;

import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public interface NearView {
   void showProgress();
    void hideProgress();
    void showParkData(List<Parking> parkingList);
}
