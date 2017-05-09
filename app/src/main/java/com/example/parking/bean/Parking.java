package com.example.parking.bean;

import com.example.parking.utils.Utils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Until;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */
@Table(name = "parking")
public class Parking extends SugarRecord implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("space")
    private int space;
    @SerializedName("price")
    private int price;
    @SerializedName("distance")
    private int distance;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("imgUrl")
    private String imgUrl;

    @SerializedName("filterType")
    private String filterType;

    public Parking() {
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public static List<Parking> getParkingList(String filterType, String sortType) {
        List<Parking> parkingList = Parking.find(Parking.class, "filter_type = ?", filterType);
        return parkingList;
    }
}
