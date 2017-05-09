package com.example.parking.near;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.parking.R;
import com.example.parking.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class NearFragment extends BaseFragment {

    @BindView(R.id.spinner_default)
    Spinner spinnerDefault;
    @BindView(R.id.spinner_distance)
    Spinner spinnerDistance;
    @BindView(R.id.spinner_price)
    Spinner spinnerPrice;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private String[] mDefaults;
    private String[] mDistances;
    private String[] mPrices;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, null);
        ButterKnife.bind(this, view);
        initSpinner();
        return view;
    }

    private void initSpinner() {
        mDefaults = getResources().getStringArray(R.array.spinner_default);
        mDistances = getResources().getStringArray(R.array.spinner_distance);
        mPrices = getResources().getStringArray(R.array.spinner_price);
        ArrayAdapter<String> defaultsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.
                simple_spinner_item, mDefaults);
        defaultsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.
                simple_spinner_item, mDistances);
        distanceAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        ArrayAdapter<String> pricesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.
                simple_spinner_item, mPrices);
        pricesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerDefault.setAdapter(defaultsAdapter);
        spinnerDistance.setAdapter(distanceAdapter);
        spinnerPrice.setAdapter(pricesAdapter);
    }



}
