package com.example.parking.query.model;

import android.content.Context;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public interface IQueryModel {
    void startQuery(Context context,String query);

    void tipQuery(Context context,String keyWord);
}
