package com.example.parking.near;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.parking.R;
import com.example.parking.base.BaseFragment;
import com.example.parking.bean.Parking;
import com.example.parking.near.presenter.NearPresenter;
import com.example.parking.near.presenter.NearPresenterImpl;
import com.example.parking.near.view.NearView;
import com.example.parking.refresh.RefreshLayout;
import com.example.parking.refresh.SwipeRefreshLayoutDirection;
import com.example.parking.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class NearFragment extends BaseFragment implements NearView, NearAdapter.
        OnItemClickListener, RefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_park)
    RelativeLayout layoutPark;
    @BindView(R.id.spinner_default)
    Spinner spinnerDefault;
    @BindView(R.id.spinner_distance)
    Spinner spinnerDistance;
    @BindView(R.id.spinner_price)
    Spinner spinnerPrice;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    private String[] mDefaults;
    private String[] mDistances;
    private String[] mPrices;
    private NearAdapter mNearAdapter;
    private List<Parking> mParkingList;
    private NearPresenter mNearPresenter;
    private int mPage;
    private int mSize = 10;
    private int mCount;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_near, null);
        ButterKnife.bind(this, view);
        mNearPresenter = new NearPresenterImpl(this);
        initSpinner();
        initRecycleView();
        mCount = Parking.listAll(Parking.class).size();
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

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
        refreshLayout.setColorScheme(R.color.colorPrimary, R.color.colorAccent);
        onRefresh(SwipeRefreshLayoutDirection.TOP);
        mParkingList = new ArrayList<>();
    }

    @Override
    public void showProgress() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideProgress() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showParkData(final List<Parking> parkingList) {
        mParkingList = parkingList;
        mNearAdapter.addParkList(parkingList, mPage == 1 ? true : false);
        if (mCount <= mPage * mSize) {
            Utils.showSnackBar(layoutPark, getString(R.string.the_last_page));
            refreshLayout.setDirection(SwipeRefreshLayoutDirection.TOP);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ParkActivity.class);
        intent.putExtra(Utils.PARKING, mParkingList.get(position));
        startActivity(intent);
    }


    @Override
    public void onRefresh(SwipeRefreshLayoutDirection direction) {

        if (direction == SwipeRefreshLayoutDirection.TOP) {
            mPage = 1;
            refreshLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
        } else {
            mPage++;
        }
        mNearPresenter.loadParkData(mPage, mSize, "", "");
    }
}
