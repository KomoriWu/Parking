package com.example.parking.near.presenter;

import android.os.AsyncTask;

import com.example.parking.bean.Parking;
import com.example.parking.near.model.NearModel;
import com.example.parking.near.model.NearModelImpl;
import com.example.parking.near.view.NearView;

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
            protected List<Parking> doInBackground(Void... voids) {
                return mNearModel.loadParkData(filterType, sortType);
            }

            @Override
            protected void onCancelled(List<Parking> parkingList) {
                mNearView.showParkData(parkingList);
            }
        }.execute();
    }
}
