package com.example.parking.bean;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;

/**
 * Created by KomoriWu
 * on 2017-05-10.
 */
@Table(name = "user")
public class User extends SugarRecord implements Serializable {
    @SerializedName("account")
    private String account;
    @SerializedName("password")
    private String password;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
