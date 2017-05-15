package com.example.parking.query.model;

import android.content.Context;
import android.util.Log;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.parking.query.presenter.OnQueryListener;
import com.example.parking.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here.
 * Q
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public class QueryModelImpl implements IQueryModel, Inputtips.InputtipsListener, PoiSearch.OnPoiSearchListener {

    public static final int CODE_SUCCEED = 1000;

    private OnQueryListener mOnQueryListener;

    public QueryModelImpl(OnQueryListener onQueryListener) {
        mOnQueryListener = onQueryListener;
    }

    @Override
    public void startQuery(Context context, String keyWord) {
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "",
                SPUtils.getSharedStringData(context, SPUtils.CITY_CODE));
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码
        PoiSearch poiSearch = new PoiSearch(context, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public void tipQuery(Context context, String keyWord) {
        InputtipsQuery inputquery =
                new InputtipsQuery(keyWord, SPUtils.getSharedStringData(context, SPUtils.CITY_CODE));
        inputquery.setCityLimit(true);//限制在当前城市
        Inputtips inputTips = new Inputtips(context, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        ArrayList<SearchResult> searchResults = new ArrayList<>();
        if (i == CODE_SUCCEED) {
            for (int j = 0; j < list.size(); j++) {
                SearchResult result = new SearchResult();
                result.setAddress(list.get(j).getName());
                result.setLatLonPoint(list.get(j).getPoint());
                Log.d("onGetInputtips", result.toString());
                if (result.getLatLonPoint() != null) {
                    searchResults.add(result);
                }
            }
            mOnQueryListener.querySucceed(searchResults);
        } else {
            mOnQueryListener.queryFailed();
        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == CODE_SUCCEED) {
            ArrayList<SearchResult> searchResults = new ArrayList<>();
            ArrayList<PoiItem> poiItems = poiResult.getPois();
            for (int j = 0; j < poiItems.size(); j++) {
                SearchResult result = new SearchResult();
                result.setAddress(poiItems.get(j).getTitle());
                result.setLatLonPoint(poiItems.get(j).getLatLonPoint());
                Log.d("onPoiSearched", result.toString());
                if (result.getLatLonPoint() != null) {
                    searchResults.add(result);
                }
            }
            mOnQueryListener.querySucceed(searchResults);
        } else {
            mOnQueryListener.queryFailed();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        if (i == CODE_SUCCEED) {
            ArrayList<SearchResult> searchResults = new ArrayList<>();
            SearchResult result = new SearchResult();
            result.setAddress(poiItem.getTitle());
            result.setLatLonPoint(poiItem.getLatLonPoint());
            if (result.getLatLonPoint() != null) {
                searchResults.add(result);
                mOnQueryListener.querySucceed(searchResults);
            } else {
                mOnQueryListener.queryFailed();
            }
        } else {
            mOnQueryListener.queryFailed();
        }
    }
}
