package com.example.parking.near.model;

import android.os.AsyncTask;

import com.example.parking.bean.Parking;

import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public class NearModelImpl implements NearModel {

    @Override
    public void loadParkData(final int page, final int size, final String filterType,
                             final String sortType, final OnLoadListener onLoadListener) {

        new AsyncTask<Void, Void, List<Parking>>() {
            @Override
            protected void onPreExecute() {
                onLoadListener.showProgress();
            }

            @Override
            protected List<Parking> doInBackground(Void... voids) {
                List<Parking> parkingArrayList= Parking.getParkingList(page, size, filterType,
                        sortType);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return parkingArrayList;
            }

            @Override
            protected void onPostExecute(List<Parking> parkingList) {
//                parkingList = new ArrayList<>();
//                Parking parking = new Parking();
//                parking.setAddress("地址");
//                parking.setName("停车场");
//                parkingList.add(parking);
                onLoadListener.success(parkingList);
            }

        }.execute();

    }

    public interface OnLoadListener {
        void showProgress();

        void success(List<Parking> parkingList);
    }
}
