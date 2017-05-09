package com.example.parking.near.presenter;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public interface NearPresenter {
    void loadParkData(int page, int size, String filterType, String sortType);
}
