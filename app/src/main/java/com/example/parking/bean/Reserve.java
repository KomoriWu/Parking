package com.example.parking.bean;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by KomoriWu
 * on 2017-05-11.
 */
@Table(name = "reserve")
public class Reserve extends SugarRecord implements Serializable {
    @SerializedName("account")
    private String account;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("price")
    private int price;
    @SerializedName("number")
    private int number;
    @SerializedName("date")
    private String date;

    public Reserve() {
    }

    public Reserve(String account, String name, String address, String phoneNumber, String imgUrl,
                   int price, int number, String date) {
        this.account = account;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imgUrl = imgUrl;
        this.price = price;
        this.number = number;
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
