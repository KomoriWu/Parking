package com.example.parking.query.presenter;

import android.content.Context;

import com.example.parking.query.model.IQueryModel;
import com.example.parking.query.model.QueryModelImpl;
import com.example.parking.query.model.SearchResult;
import com.example.parking.query.view.IQueryView;

import java.util.List;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public class QueryPresenterImpl implements IQueryPresenter, OnQueryListener {

    private IQueryView mQueryView;
    private IQueryModel mQueryModel;

    public QueryPresenterImpl(IQueryView iQueryView) {
        mQueryView = iQueryView;
        mQueryModel = new QueryModelImpl(this);
    }

    @Override
    public void query(Context context, String keyWord) {
        mQueryView.startQuery();
        mQueryModel.startQuery(context, keyWord);
    }

    @Override
    public void tipsQuery(Context context, String keyWord) {
        mQueryView.startQuery();
        mQueryModel.tipQuery(context, keyWord);
    }


    @Override
    public void querySucceed(List<SearchResult> searchResults) {
        mQueryView.querySucceed(searchResults);
    }

    @Override
    public void queryFailed() {
        mQueryView.queryFailed();
    }
}
