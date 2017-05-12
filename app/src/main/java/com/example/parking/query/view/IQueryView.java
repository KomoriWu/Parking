package com.example.parking.query.view;

import com.example.parking.query.model.SearchResult;

import java.util.List;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public interface IQueryView {
    void startQuery();

    void querySucceed(List<SearchResult> searchResults);

    void queryFailed();
}
