package com.example.parking.main.presenter;

import com.example.parking.R;
import com.example.parking.main.view.MainView;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;

    public MainPresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case 0:
                mMainView.switchNear();
                break;
            case 1:
                mMainView.switchQuery();
                break;
            case 2:
                mMainView.switchAdd();
                break;
            case 3:
                mMainView.switchMessage();
                break;
            case 4:
                mMainView.switchMy();
                break;
        }
    }
}

