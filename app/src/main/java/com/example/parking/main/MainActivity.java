package com.example.parking.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.parking.R;
import com.example.parking.add.AddFragment;
import com.example.parking.base.BaseActivity;
import com.example.parking.main.presenter.MainPresenter;
import com.example.parking.main.presenter.MainPresenterImpl;
import com.example.parking.main.view.MainView;
import com.example.parking.message.MessageFragment;
import com.example.parking.my.MyFragment;
import com.example.parking.near.NearFragment;
import com.example.parking.query.QueryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        MainView {

    @BindView(R.id.frame_content)
    FrameLayout frameContent;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private NearFragment mNearFragment;
    private QueryFragment mQueryFragment;
    private AddFragment mAddFragment;
    private MessageFragment mMessageFragment;
    private MyFragment mMyFragment;
    private MainPresenter mMainPresenter;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        mMainPresenter = new MainPresenterImpl(this);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.rb_add);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        mMainPresenter.switchNavigation(radioGroup.getCheckedRadioButtonId());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void switchNear() {
        tvTitle.setText(R.string.near_park);
        if (mNearFragment == null) {
            mNearFragment = new NearFragment();
        }
        showFragment(mNearFragment);
    }

    @Override
    public void switchQuery() {
        tvTitle.setText(R.string.line_park);
        if (mQueryFragment == null) {
            mQueryFragment = new QueryFragment();
        }
        showFragment(mQueryFragment);
    }

    @Override
    public void switchAdd() {
        tvTitle.setText(R.string.park_master);
        if (mAddFragment == null) {
            mAddFragment = new AddFragment();
        }
        showFragment(mAddFragment);
    }

    @Override
    public void switchMessage() {
        tvTitle.setText(R.string.message);
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
        }
        showFragment(mMessageFragment);
    }

    @Override
    public void switchMy() {
        tvTitle.setText(R.string.personal_center);
        if (mMyFragment == null) {
            mMyFragment = new MyFragment();
        }
        showFragment(mMyFragment);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadSuccessMsg(String name) {

    }

    @Override
    public void showLoadFailMsg(String message) {

    }

    @Override
    public void showLoadExceptionMsg(String exception) {

    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commit();
    }
}
