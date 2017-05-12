package com.example.parking.query.model;

import com.amap.api.services.core.LatLonPoint;

import java.io.Serializable;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public class SearchResult implements Serializable {
    public String address;
    private LatLonPoint latLonPoint;

    public SearchResult(String address, LatLonPoint latLonPoint) {
        this.address = address;
        this.latLonPoint = latLonPoint;
    }

    public SearchResult() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLonPoint getLatLonPoint() {
        return latLonPoint;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.latLonPoint = latLonPoint;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "address='" + address + '\'' +
                ", latLonPoint=" + latLonPoint +
                '}';
    }
}
