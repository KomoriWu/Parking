package com.example.parking.add;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.example.parking.R;
import com.example.parking.add.presenter.AddPresenterImpl;
import com.example.parking.add.presenter.IAddPresenter;
import com.example.parking.add.service.TTSController;
import com.example.parking.add.view.IAddView;
import com.example.parking.base.BaseFragment;
import com.example.parking.utils.Utils;

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

        if (marker.isInfoWindowShown()) {
            NaviLatLng startLatlng = new NaviLatLng(mLatLng.latitude, mLatLng.longitude);
            LatLng markerLatlng = marker.getPosition();
            NaviLatLng endLatlng = new NaviLatLng(markerLatlng.latitude, markerLatlng.longitude);
            showNavagationView(startLatlng, endLatlng);
        } else {
            marker.showInfoWindow();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapManager.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirst = true;
    }

    @Override
    public void searchSucceed(PoiItem poiItem) {
        mapManager.addMarker(poiItem);
    }

    @Override
    public void searchCompleted() {
        mapManager.zoomToCurrent();
    }

    private void showNavagationView(final NaviLatLng startLatlng, final NaviLatLng endLatlng) {
        new AlertDialog.Builder(getActivity()).setMessage("确定要开启导航?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Utils.showNaviActivity(getActivity(), startLatlng, endLatlng);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
