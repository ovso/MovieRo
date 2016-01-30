package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.R;

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
        mView.replaceFragment();
    }

    @Override
    public void onClick(android.view.View v) {

    }

    @Override
    public boolean onNavigationItemSelected(int id) {
        if (id == R.id.nav_camera) {
            return true;
        } else {
            return mView.navigateToNotiListActivity();
        }
    }
}