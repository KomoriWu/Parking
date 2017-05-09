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
            case R.id.rb_near:
                mMainView.switchNear();
                break;
            case R.id.rb_query:
                mMainView.switchQuery();
                break;
            case R.id.rb_add:
                mMainView.switchAdd();
                break;
            case R.id.rb_message:
                mMainView.switchMessage();
                break;
            case R.id.rb_my:
                mMainView.switchMy();
                break;
        }
    }
}

