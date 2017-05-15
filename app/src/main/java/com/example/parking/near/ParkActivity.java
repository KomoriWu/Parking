package com.example.parking.near;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.base.BaseActivity;
import com.example.parking.bean.Parking;
import com.example.parking.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.layout)
    RelativeLayout layout;
    private Parking mParking;
    private int mCount = 1;

    @Override
    public void init() {
        setContentView(R.layout.activity_park);
        ButterKnife.bind(this);
        mParking = (Parking) getIntent().getSerializableExtra(Utils.PARKING);
        isShowBackBar = true;
        initToolbar();
        tvTitle.setText(mParking.getName());
        initData();

    }

    private void initData() {
        MyApplication.getImageLoader(this).displayImage(mParking.getImgUrl(),
                ivParkImg, Utils.getImageOptions(R.mipmap.park, 360));
        tvParkSpace.setText(String.format(getString(R.string.park_space), mParking.
                getSpace() + ""));
        tvParkPrice.setText(String.format(getString(R.string.park_price), mParking.
                getPrice() + ""));
        tvParkPhoneNumber.setText(String.format(getString(R.string.park_phone_number), mParking.
                getPhoneNumber()));
        tvParkAddress.setText(String.format(getString(R.string.park_address), mParking.
                getAddress()));
    }


    @OnClick(R.id.fab_reserve)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.reserve_dialog, null);
        final TextView tvCount = (TextView) view.findViewById(R.id.tv_count);
        FloatingActionButton fabDecrease = (FloatingActionButton) view.findViewById(R.id.fab_decrease);
        FloatingActionButton fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);

        tvCount.setText(mCount + "");
        fabDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCount > 1) {
                    mCount -= 1;
                    tvCount.setText(mCount + "");
                }
            }
        });
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCount < 10) {
                    mCount += 1;
                    tvCount.setText(mCount + "");
                }
            }
        });
        builder.setView(view);
        builder.setTitle(getResources().getString(R.string.confirm_reserve));
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Utils.showSnackBar(layout, getResources().getString(R.string.
                                reserve_success));
                        
                        dialog.dismiss();
                    }
                }
        );
        builder.create().show();
    }
}
