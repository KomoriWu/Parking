package com.example.parking.add.model;

import com.amap.api.services.core.PoiItem;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/9
 * @date 2017/5/9
 */

public interface OnParkSearchListener {
    void parkSearchSucceed(PoiItem poiItem);

    void parkSearchCompleted();
}
