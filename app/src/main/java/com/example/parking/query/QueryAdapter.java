package com.example.parking.query;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.query.model.SearchResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public class QueryAdapter extends RecyclerView.Adapter {


    private OnSearchItemClickListener mOnSearchItemClickListener;
    private Context mContext;

    private ArrayList<SearchResult> searchResults;

    public QueryAdapter(Context context, OnSearchItemClickListener onSearchItemClickListener) {
        mContext = context;
        mOnSearchItemClickListener = onSearchItemClickListener;
        searchResults = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_query_result, null);
        return new QueryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SearchResult searchResult = searchResults.get(position);
        QueryViewHolder queryViewHolder = (QueryViewHolder) holder;
        queryViewHolder.tvResultName.setText(searchResult.getAddress());
        queryViewHolder.layoutResultItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSearchItemClickListener != null) {
                    mOnSearchItemClickListener.onItemClick(searchResult);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults == null ? 0 : searchResults.size();
    }

    public static class QueryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_result_name)
        TextView tvResultName;
        @BindView(R.id.layout_result_item)
        RelativeLayout layoutResultItem;

        public QueryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public void refreshData(List<SearchResult> searchResultList) {
        if (searchResultList != null) {
            searchResults.clear();
            searchResults.addAll(searchResultList);
            notifyDataSetChanged();
        }

    }

    public interface OnSearchItemClickListener {
        void onItemClick(SearchResult searchResult);
    }
}
