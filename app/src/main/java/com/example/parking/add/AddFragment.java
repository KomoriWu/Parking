package com.example.parking.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.parking.R;
import com.example.parking.add.presenter.AddPresenterImpl;
import com.example.parking.add.presenter.IAddPresenter;
import com.example.parking.add.view.IAddView;
import com.example.parking.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class AddFragment extends BaseFragment implements MapManager.onMapListener, IAddView {

    private static final String TAG = "AddFragment";
    private MapManager mapManager;
    private LatLng mLatLng;
    private IAddPresenter mAddPresenter;
    private boolean isFirst = true;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, null);
        ButterKnife.bind(this, view);

        AMap aMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).
                getMap();
        mapManager = new MapManager(getActivity(), aMap);
        mapManager.setOnMapListener(this);
        mAddPresenter = new AddPresenterImpl(AddFragment.this);
        return view;
    }


    @Override
    public void mapLocationChanged(AMapLocation location) {
        mLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        LatLonPoint latLonPoint = new LatLonPoint(location.getLatitude(), location.getLongitude());
        Log.d(TAG, location.toString());
        if (isFirst) {
            mAddPresenter.searchParking(getActivity(), latLonPoint);
            isFirst = false;
        }
    }

    @Override
    public void mapMarkerClick(Marker marker) {
        marker.showInfoWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapManager.onDestroy();
    }


    @Override
    public void searchSucceed(PoiItem poiItem) {
        mapManager.addMarker(poiItem);
    }


}
