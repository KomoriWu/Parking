package com.example.parking.near;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.bean.Parking;
import com.example.parking.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class NearAdapter extends RecyclerView.Adapter<NearAdapter.ParkViewHolder> {
    private Context mContext;
    private List<Parking> mParkingList;
    private OnItemClickListener mOnItemClickListener;

    public NearAdapter(Context mContext, OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
        mParkingList = new ArrayList<>();
    }

//    public void setParkList(List<Parking> mParkingList) {
//        this.mParkingList = mParkingList;
//        notifyDataSetChanged();
//    }

    public void addParkList(List<Parking> parkingList, boolean isClearList) {
        if (parkingList != null) {
            if (isClearList) {
                mParkingList.clear();
            }
            mParkingList.addAll(parkingList);
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.park_item, null);
        return new ParkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParkViewHolder holder, int position) {
        Parking parking = mParkingList.get(position);
        holder.tvName.setText(parking.getName());
        holder.tvPrice.setText(parking.getPrice()+"");
        holder.tvAddress.setText(parking.getAddress());
        holder.tvDistance.setText(parking.getDistance()/1000+"km");
        MyApplication.getImageLoader(mContext).displayImage(parking.getImgUrl(),
                holder.ivHead, Utils.getImageOptions(R.mipmap.park, 360));

    }

    @Override
    public int getItemCount() {
        return mParkingList == null ? 0 : mParkingList.size();
    }

    public class ParkViewHolder extends RecyclerView.ViewHolder implements View.
            OnClickListener {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.layout_park_item)
        RelativeLayout layoutParkItem;

        public ParkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layoutParkItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getPosition());
            }
        }
    }
}