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
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.application.MyApplication;
import com.example.parking.bean.ParkOrder;
import com.example.parking.bean.Parking;
import com.example.parking.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import c.b.BP;
import c.b.PListener;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ParkViewHolder> {
    private Context mContext;
    private List<ParkOrder> mParkOrderList;
    private OnItemClickListener mOnItemClickListener;
    private OnInterfaceProgressDialog mOnInterfaceProgressDialog;

    public interface OnInterfaceProgressDialog {
        void showInterfaceProgressDialog();

        void hideInterfaceProgressDialog();
    }


    public OrderAdapter(Context mContext, OnItemClickListener mOnItemClickListener,
                        OnInterfaceProgressDialog mOnInterfaceProgressDialog) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
        mParkOrderList = new ArrayList<>();
        this.mOnInterfaceProgressDialog = mOnInterfaceProgressDialog;
    }


    public void setOrderList(List<ParkOrder> parkOrderList) {
        this.mParkOrderList = parkOrderList;
        notifyDataSetChanged();
    }

    public void deleteOrder(String name) {
        ParkOrder.deleteOrderByName(name);
        for (int i = 0; i < mParkOrderList.size(); i++) {
            if (mParkOrderList.get(i).getName().equals(name)) {
                mParkOrderList.remove(i);
                notifyDataSetChanged();
            }
        }
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
    public void onBindViewHolder(final ParkViewHolder holder, int position) {
        final ParkOrder parkOrder = mParkOrderList.get(position);
        holder.tvName.setText(parkOrder.getName());
        holder.tvOrderTime.setText(mContext.getString(R.string.order_time)
                + Utils.getTime(parkOrder.getOrderTime()));
        holder.tvCountPrice.setText("¥" + Utils.getCountPrice(parkOrder.getCount(), parkOrder.
                getPrice(), parkOrder.getOrderTime()));
        MyApplication.getImageLoader(mContext).displayImage(parkOrder.getImgUrl(),
                holder.ivHead, Utils.getImageOptions(R.mipmap.park, 360));
        holder.btnPay.setText(parkOrder.isPay() ? mContext.getResources().getString(
                R.string.pay_success) : mContext.getResources().getString(R.string.pay));
        if (!parkOrder.isPay()) {
            holder.btnPay.setBackground(mContext.getResources().getDrawable(R.drawable.
                    ripple_btn_effect));
        } else {
            holder.btnPay.setBackgroundColor(mContext.getResources().getColor(R.color.medium_gray));
        }

        holder.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!parkOrder.isPay()) {
                    mOnInterfaceProgressDialog.showInterfaceProgressDialog();
                    BP.pay("停车费用", parkOrder.getName(), 0.01, true, new PListener() {
                        @Override
                        public void orderId(String s) {
                            mOnInterfaceProgressDialog.hideInterfaceProgressDialog();
                        }

                        @Override
                        public void succeed() {
                            parkOrder.setPay(true);
                            parkOrder.save();
                            notifyDataSetChanged();

                            Toast.makeText(mContext, "支付成功！", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void fail(int i, String s) {
                            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void unknow() {
                        }
                    });
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mParkOrderList == null ? 0 : mParkOrderList.size();
    }

    public class ParkViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
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
            layoutParkItem.setOnLongClickListener(this);
        }


        @Override
        public boolean onLongClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getPosition());
            }
            return false;
        }
    }
}