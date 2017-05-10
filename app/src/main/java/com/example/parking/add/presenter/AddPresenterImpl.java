package com.example.parking.add.presenter;

import android.content.Context;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.parking.add.AddFragment;
import com.example.parking.add.model.AddModelImpl;
import com.example.parking.add.model.IAddModel;
import com.example.parking.add.model.OnParkSearchListener;
import com.example.parking.add.view.IAddView;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public class AddPresenterImpl implements IAddPresenter, OnParkSearchListener {
    private IAddView mAddView;
    private IAddModel mAddModel;


    public AddPresenterImpl(IAddView addView) {
        this.mAddView = addView;
        this.mAddModel = new AddModelImpl();
    }

    @Override
    public void searchParking(Context context, LatLonPoint latLonPoint) {
        mAddModel.startSearch(context, this, latLonPoint);
    }

    @Override
    public void parkSearchSucceed(PoiItem poiItem) {
        mAddView.searchSucceed(poiItem);
    }

    @Override
    public void parkSearchCompleted() {
        mAddView.searchCompleted();
    }
}
