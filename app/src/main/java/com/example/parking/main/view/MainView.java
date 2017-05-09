package com.example.parking.main.view;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public interface MainView {
    void showProgress();

    void switchNear();

    void switchQuery();

    void switchAdd();

    void switchMessage();

    void switchMy();

    void hideProgress();

    void showLoadSuccessMsg(String name);

    void showLoadFailMsg(String message);

    void showLoadExceptionMsg(String exception);

}
