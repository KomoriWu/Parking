package com.example.parking.add;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.query.model.SearchResult;
import com.example.parking.utils.SPUtils;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public class MapManager implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener {

    private static final String TAG = "MapManager";
    private Context mContext;
    private AMap mMap;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private MarkerOptions markerOption;
    //是否开启了缩放级别，
    private boolean hasSetZoom;
    private boolean isFirst = true;

    public MapManager(Context context, AMap aMap) {
        this.mContext = context;
        this.mMap = aMap;
        init();
    }

    private void init() {
        mUiSettings = mMap.getUiSettings();//实例化UiSettings类
        mUiSettings.setCompassEnabled(true);//显示指南针
        mUiSettings.setScaleControlsEnabled(true);//显示比例尺控件
        //定位
        mMap.setLocationSource(this);// 设置定位监听
        mUiSettings.setMyLocationButtonEnabled(true); // 显示默认的定位按钮
        mMap.setMyLocationEnabled(true);// 可触发定位并显示定位层
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        //监听地图上标记的点击功能
        mMap.setOnMarkerClickListener(this);
    }

    //缩放缩放级别
    public void zoom() {
        if (mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        }
    }

    /**
     * 增加一个在地图上添加标记的功能，
     *
     * @param
     */
//    public void addMarker(ReceiveOrderModel model){
//        //先清空地图上的标记再添加标记
//        clearAllMarker();
//        if (model!=null){
//            LatLng latLng = new LatLng(model.getData().getLatitude(),model.getData().getLongitude());
//            //移动地图到指定位置，故障位置
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                    latLng,//新的中心点坐标
//                    13, //新的缩放级别
//                    0, //俯仰角0°~45°（垂直与地图时为0）
//                    0  ////偏航角 0~360° (正北方为0)
//            )));
//            //在故障位置，添加标记
//            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
//                    .decodeResource(mContext.getResources(),
//                            R.drawable.home_weixiu_red)))
//                    .position(latLng).title("救援地点：").snippet(model.getData().getAddress())
//                    .draggable(true);
//            Marker marker = mMap.addMarker(markerOption);
//            marker.showInfoWindow();
//        }
//    }
    public void addMarker(PoiItem poiItem) {
        if (poiItem != null) {
            LatLng latLng = new LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory
                            .decodeResource(mContext.getResources(), R.drawable.home_weixiu_red)))
                    .position(latLng).title(poiItem.getTitle())
                    .snippet(poiItem.getParkingType())
                    .draggable(false);
            Marker marker = mMap.addMarker(markerOption);
        }
    }

    public void clearAndAddMarker(SearchResult searchResult) {
        if (searchResult != null) {
            clearAllMarker();
            //显示定位小蓝点
            isFirst = true;
            LatLng latLng = new LatLng(searchResult.getLatLonPoint().getLatitude(), searchResult.getLatLonPoint().getLongitude());
//            移动地图到指定位置，故障位置
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    latLng,//新的中心点坐标
                    13, //新的缩放级别
                    0, //俯仰角0°~45°（垂直与地图时为0）
                    0  ////偏航角 0~360° (正北方为0)
            )));
            markerOption = new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory
                            .decodeResource(mContext.getResources(), R.drawable.home_weixiu_red)))
                    .position(latLng).title(searchResult.getAddress())
                    .draggable(false);
            Marker marker = mMap.addMarker(markerOption);
            marker.showInfoWindow();
        }
    }

    public void clearAllMarker() {
        if (mMap != null) {
            mMap.clear();
        } else {
            Log.e(TAG, "mMap is null ...");
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (mMap != null) {
            if (onMapListener != null) {
                onMapListener.mapMarkerClick(marker);
            }
        }
        return true;
    }

    //激活定位
    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(mContext);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mLocationOption.setInterval(20000);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mlocationClient.startLocation();
        }
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    /**
     * 定位成功后的回调函数
     *
     * @param amapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {

            SPUtils.setSharedStringData(MyApplication.getAppContext(), SPUtils.CITY_CODE, amapLocation.getCity());
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                if (isFirst) {
                    isFirst = false;
                    mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                }
                Log.d(TAG, "定位成功");
                if (onMapListener != null) {
                    onMapListener.mapLocationChanged(amapLocation);
                }
//                if (!hasSetZoom) {
//                    zoom();
//                    hasSetZoom = true;
//                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e(TAG, errText);
            }
        }
    }

    /**
     * 销毁对象
     */

    public void onDestroy() {
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    public void zoomToCurrent() {
        if (!hasSetZoom) {
            zoom();
            hasSetZoom = true;
        }
    }

    public interface onMapListener {
        void mapLocationChanged(AMapLocation location);

        void mapMarkerClick(Marker marker);
    }

    private onMapListener onMapListener;

    public void setOnMapListener(onMapListener i) {
        this.onMapListener = i;
    }
}
