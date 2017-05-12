package com.example.parking.query.presenter;

import android.content.Context;

/**
 * Class description goes here.
 *
 * @author Zeller
 * @version 1.0.0  2017/5/12
 * @date 2017/5/12
 */

public interface IQueryPresenter {
    void query(Context context,String keyWord);

    void tipsQuery(Context context,String keyWord);
}
