package com.example.parking.bean;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

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
    private String space;
    @SerializedName("price")
    private String price;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("distance")
    private String distance;

    public Parking() {
    }

    public Parking(String name, String address, String space, String price, String phoneNumber,
                   String imgUrl, String distance) {
        this.name = name;
        this.address = address;
        this.space = space;
        this.price = price;
        this.phoneNumber = phoneNumber;
        this.imgUrl = imgUrl;
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

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
