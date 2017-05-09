package com.example.parking.add.model;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.parking.utils.Utils;

import java.util.ArrayList;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public class AddModelImpl implements IAddModel, PoiSearch.OnPoiSearchListener {

    private static final String TAG = "AddModelImpl";
    private PoiSearch mPoiSearch;
    private PoiSearch.Query mQuery;
    private OnParkSearchListener mParkListener;

    @Override
    public void startSearch(Context context, OnParkSearchListener listener, LatLonPoint latLonPoint) {
        this.mParkListener = listener;
        mQuery = new PoiSearch.Query(Utils.TEXT_POI_KEY, Utils.TEXT_POI_TYPE);
        mQuery.setPageSize(10);// 设置每页最多返回多少条poiitem
        mQuery.setPageNum(1);//设置查询页码
        mPoiSearch = new PoiSearch(context, mQuery);
        mPoiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, Utils.POI_SEARCH_BOUND));
        mPoiSearch.setOnPoiSearchListener(this);
        mPoiSearch.searchPOIAsyn();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        ArrayList<PoiItem> queryItems = poiResult.getPois();
        for (int j = 0; j < queryItems.size(); j++) {
            mParkListener.parkSearchSucceed(queryItems.get(j));
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        Log.d("PoiItem", poiItem.toString());
        mParkListener.parkSearchSucceed(poiItem);
    }


}
