package com.example.parking.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.parking.R;
import com.example.parking.base.BaseActivity;
import com.example.parking.bean.ParkOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements OrderAdapter.OnItemClickListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private OrderAdapter mOrderAdapter;
    private List<ParkOrder> mParkOrderList;

    @Override
    public void init() {
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initToolbar();
        tvTitle.setText(getResources().getString(R.string.my_order));
        initRecycleView();
    }

    private void initRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOrderAdapter = new OrderAdapter(this, this);
        recyclerView.setAdapter(mOrderAdapter);
        mParkOrderList = ParkOrder.listAll(ParkOrder.class);
        mOrderAdapter.setOrderList(mParkOrderList);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}