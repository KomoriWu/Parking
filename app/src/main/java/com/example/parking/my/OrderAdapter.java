package com.example.parking.my;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.bean.ParkOrder;
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


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ParkViewHolder> {
    private Context mContext;
    private List<ParkOrder> mParkOrderList;
    private OnItemClickListener mOnItemClickListener;

    public OrderAdapter(Context mContext, OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
        mParkOrderList = new ArrayList<>();
    }


    public void setOrderList(List<ParkOrder> parkOrderList) {
        this.mParkOrderList = parkOrderList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order, null);
        return new ParkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParkViewHolder holder, int position) {
        ParkOrder parkOrder = mParkOrderList.get(position);
        holder.tvName.setText(parkOrder.getName());
        holder.tvOrderTime.setText(mContext.getString(R.string.order_time)
                + Utils.getTime(parkOrder.getOrderTime()));
        holder.tvCountPrice.setText("¥" + Utils.getCountPrice(parkOrder.getCount(), parkOrder.
                getPrice(), parkOrder.getOrderTime()));
        MyApplication.getImageLoader(mContext).displayImage(parkOrder.getImgUrl(),
                holder.ivHead, Utils.getImageOptions(R.mipmap.park, 360));

    }

    @Override
    public int getItemCount() {
        return mParkOrderList == null ? 0 : mParkOrderList.size();
    }

    public class ParkViewHolder extends RecyclerView.ViewHolder implements View.
            OnClickListener {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_count_price)
        TextView tvCountPrice;
        @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.btn_pay)
        Button btnPay;
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