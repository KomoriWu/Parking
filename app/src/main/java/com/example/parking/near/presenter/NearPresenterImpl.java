package com.example.parking.near.presenter;

import android.os.AsyncTask;

import com.example.parking.bean.Parking;
import com.example.parking.near.model.NearModel;
import com.example.parking.near.model.NearModelImpl;
import com.example.parking.near.view.NearView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */

public class NearPresenterImpl implements NearPresenter {
    private NearModel mNearModel;
    private NearView mNearView;

    public NearPresenterImpl(NearView mNearView) {
        this.mNearView = mNearView;
        mNearModel = new NearModelImpl();
    }

    @Override
    public void loadParkData(final String filterType, final String sortType) {
        new AsyncTask<Void, Void, List<Parking>>() {
            @Override
            protected void onPreExecute() {
                mNearView.showProgress();
            }

            @Override
            protected List<Parking> doInBackground(Void... voids) {
                return mNearModel.loadParkData(filterType, sortType);
            }

            @Override
            protected void onPostExecute(List<Parking> parkingList) {
                parkingList = new ArrayList<>();
                Parking parking = new Parking();
                parking.setAddress("地址");
                parking.setName("停车场");
                parkingList.add(parking);
                mNearView.showParkData(parkingList);
            }

        }.execute();
    }
}
