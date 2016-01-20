package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.common.Common;

/**
 * Created by ovso on 2016. 1. 20..
 */
public class MainPresenterImpl implements MainPresenter {
    MainPresenter.View mView;
    MainModel mModel;
    public MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void onCreate(Context context) {
        mView.onInit();
    }
}
