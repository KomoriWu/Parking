package com.example.parking.bean;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by KomoriWu
 * on 2017-05-15.
 */
@Table(name = "park_order")
public class ParkOrder extends SugarRecord implements Serializable {
    @SerializedName("userAccount")
    private String userAccount;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("price")
    private int price;
    @SerializedName("count")
    private int count;
    @SerializedName("orderTime")
    private int orderTime;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("isPay")
    private boolean isPay;

    public ParkOrder() {
    }

    public ParkOrder(String userAccount, String name, String address, int price, int count,
                     int orderTime, String imgUrl) {
        this.userAccount = userAccount;
        this.name = name;
        this.address = address;
        this.price = price;
        this.count = count;
        this.orderTime = orderTime;
        this.imgUrl = imgUrl;
        this.isPay = false;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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


    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void deleteOrderByName(String name) {
        ParkOrder.deleteAll(ParkOrder.class, "name = ?", name);
    }
}
