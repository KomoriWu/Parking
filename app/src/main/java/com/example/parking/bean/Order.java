package com.example.parking.bean;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by KomoriWu
 * on 2017-05-15.
 */
@Table(name = "order")
public class Order extends SugarRecord implements Serializable {
    @SerializedName("user_account")
    private String userAccount;
    @SerializedName("address")
    private String address;
    @SerializedName("price")
    private int price;
    @SerializedName("distance")
    private int distance;
    @SerializedName("order_time")
    private int order_time;

    public Order() {
    }

    public Order(String userAccount, String address, int price, int distance, int order_time) {
        this.userAccount = userAccount;
        this.address = address;
        this.price = price;
        this.distance = distance;
        this.order_time = order_time;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getOrder_time() {
        return order_time;
    }

    public void setOrder_time(int order_time) {
        this.order_time = order_time;
    }
}
