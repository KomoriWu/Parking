package com.example.parking.near.presenter;

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
    public void loadParkData(String filterType, String sortType) {
        mNearModel.loadParkData(filterType, sortType, new NearModelImpl.OnLoadListener() {
            @Override
            public void showProgress() {
                mNearView.showProgress();
            }

            @Override
            public void success(List<Parking> parkingList) {
                mNearView.showParkData(parkingList);
            }
        });
    }
}
