package com.example.parking.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.base.BaseFragment;
import com.example.parking.utils.Utils;
import com.example.parking.widget.ItemLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class MyFragment extends BaseFragment {
    public static final int REQUEST_CODE = 100;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.item_safety_exit)
    ItemLayout itemSafetyExit;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        MyApplication.getImageLoader(getActivity()).displayImage("",
                ivHead, Utils.getImageOptions(R.mipmap.park, 360));
        return view;
    }


    @OnClick({R.id.tv_account, R.id.item_safety_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.item_safety_exit:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            String account = data.getStringExtra(Utils.ACCOUNT);
            tvAccount.setText(String.format(getString(R.string.account), account));
        }
    }
}
