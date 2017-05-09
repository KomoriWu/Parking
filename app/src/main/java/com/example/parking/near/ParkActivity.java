package com.example.parking.near;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.base.BaseActivity;
import com.example.parking.bean.Parking;
import com.example.parking.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkActivity extends BaseActivity {
    @BindView(R.id.iv_park_img)
    ImageView ivParkImg;
    @BindView(R.id.tv_park_space)
    TextView tvParkSpace;
    @BindView(R.id.tv_park_price)
    TextView tvParkPrice;
    @BindView(R.id.tv_park_phone_number)
    TextView tvParkPhoneNumber;
    @BindView(R.id.tv_park_address)
    TextView tvParkAddress;
    @BindView(R.id.fab_reserve)
    FloatingActionButton fabReserve;
    private Parking mParking;

    @Override
    public void init() {
        setContentView(R.layout.activity_park);
        ButterKnife.bind(this);
        mParking = (Parking) getIntent().getSerializableExtra(Utils.PARKING);
        isShowBackBar = true;
        initToolbar();
        tvTitle.setText(mParking.getName());
        tvParkSpace.setText(String.format(getString(R.string.park_space), mParking.
                getSpace()+""));
        tvParkPrice.setText(String.format(getString(R.string.park_price), mParking.
                getPrice()+""));
        tvParkPhoneNumber.setText(String.format(getString(R.string.park_phone_number), mParking.
                getPhoneNumber()));
        tvParkAddress.setText(String.format(getString(R.string.park_address), mParking.
                getAddress()));
    }

}
