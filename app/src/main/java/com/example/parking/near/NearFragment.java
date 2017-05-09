package com.example.parking.near;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.parking.R;
import com.example.parking.base.BaseFragment;
import com.example.parking.bean.Parking;
import com.example.parking.near.view.NearView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class NearFragment extends BaseFragment implements NearView, NearAdapter.
        OnItemClickListener {

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
    private NearAdapter mNearAdapter;
    private List<Parking> mParkingList;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, null);
        ButterKnife.bind(this, view);
        initSpinner();
        initRecycleView();
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

    private void initRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNearAdapter = new NearAdapter(getActivity(), this);
        recyclerView.setAdapter(mNearAdapter);
        mParkingList = new ArrayList<>();
    }

    @Override
    public void showParkData(List<Parking> parkingList) {
        mNearAdapter.setMusicList(parkingList);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}