package com.example.parking.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KomoriWu
 * on 2017-05-10.
 */


public class ItemLayout extends RelativeLayout {
    @BindView(R.id.layout_item)
    RelativeLayout layoutItem;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    private Context mContext;
    public OnItemListener onItemListener;

    public OnItemListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }


    public interface OnItemListener {
        void onClickItemListener(View v);
    }


    public ItemLayout(Context context) {
        this(context, null);
    }

    public ItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Drawable drawableHead = null;
        String strName = "";
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ItemLayout,
                defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ItemLayout_iv_head:
                    drawableHead = a.getDrawable(attr);
                    break;
                case R.styleable.ItemLayout_text_name:
                    strName = a.getString(attr);
                    break;
            }

        }
        a.recycle();
        init(context);
        if (drawableHead != null) {
            ivHead.setImageDrawable(drawableHead);
        }
        if (strName != null) {
            tvName.setText(strName);
        }
    }




    public void init(Context c) {
        this.mContext = c;
        LayoutInflater.from(mContext).inflate(R.layout.item_layout, this, true);
        ButterKnife.bind(this);
        initListener();

    }

    private void initListener() {
        layoutItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener != null) {
                    onItemListener.onClickItemListener(v);
                }
            }
        });
    }
}
