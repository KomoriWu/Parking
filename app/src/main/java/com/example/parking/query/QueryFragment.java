package com.example.parking.query;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.base.BaseFragment;
import com.example.parking.main.MainActivity;
import com.example.parking.near.NearAdapter;
import com.example.parking.query.model.SearchResult;
import com.example.parking.query.presenter.IQueryPresenter;
import com.example.parking.query.presenter.QueryPresenterImpl;
import com.example.parking.query.view.IQueryView;
import com.example.parking.refresh.RefreshLayout;
import com.example.parking.refresh.SwipeRefreshLayoutDirection;
import com.example.parking.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by KomoriWu
 * on 2017-05-09.
 */


public class QueryFragment extends BaseFragment implements IQueryView,
        QueryAdapter.OnSearchItemClickListener, RefreshLayout.OnRefreshListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recycler_result)
    RecyclerView recyclerResult;
    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    private IQueryPresenter mQueryPresenter;
    private QueryAdapter mQueryAdapter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, null);
        ButterKnife.bind(this, view);
        mQueryPresenter = new QueryPresenterImpl(this);
        initEditText();
        initRecyclerView();
        return view;
    }

    private void initEditText() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        mQueryPresenter.query(getContext(), etSearch.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //在500毫秒内改变时不发送
                if (mHandler.hasMessages(MSG_SEARCH)) {
                    mHandler.removeMessages(MSG_SEARCH);
                }
                if (!TextUtils.isEmpty(s)) {
                    //否则延迟500ms开始模糊搜索
                    Message message = mHandler.obtainMessage();
                    message.obj = s.toString();
                    message.what = MSG_SEARCH;
                    mHandler.sendMessageDelayed(message, 500); //自动搜索功能 删除
                }
            }
        });
    }

    //模糊搜索
    private static final int MSG_SEARCH = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mQueryPresenter.tipsQuery(getContext(), (String) msg.obj);
        }
    };

    private void initRecyclerView() {

        refreshLayout.setOnRefreshListener(this);
        recyclerResult.setHasFixedSize(true);
        recyclerResult.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQueryAdapter = new QueryAdapter(getContext(), this);
        recyclerResult.setAdapter(mQueryAdapter);
    }


    @Override
    public void startQuery() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void querySucceed(List<SearchResult> searchResults) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
        mQueryAdapter.refreshData(searchResults);
    }

    @Override
    public void queryFailed() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        });
        Utils.showSnackBar(llLayout, getResources().getString(R.string.tip_query_failed));
    }


    @Override
    public void onItemClick(SearchResult searchResult) {
        MainActivity activity = (MainActivity) getActivity();
        activity.querySucceed(searchResult);
    }

    @Override
    public void onRefresh(SwipeRefreshLayoutDirection direction) {
        mQueryPresenter.query(getContext(), etSearch.getText().toString());
    }
}
