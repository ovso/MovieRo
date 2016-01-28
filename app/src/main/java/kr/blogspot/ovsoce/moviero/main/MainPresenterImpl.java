package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

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
        //List<ProgramItem> list = mModel.getProgramList(context);
        MovieData movieData = mModel.getMovieData(context);

        ArrayList<ProgramData> list = mModel.getProgramList(movieData);
        mView.initRecyclerView(list);
        String[] names = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getScheduleName();
        }
        mView.setSuggestion(names);
    }

    @Override
    public void onQueryTextChange(String newText) {
        mView.startFilter(newText);
    }

    @Override
    public void onClick(android.view.View v) {
        int id = v.getId();
        if(id == R.id.btn_float) {
            mView.showSortDialog(mModel.getSortChoiceItems(v.getContext()), -1);
        }
    }

    @Override
    public void onChoiceNoti(int which) {
        mModel.setChoiceNoti(which);
    }

    @Override
    public void onChoiceNotiOK(Context context, ProgramData programData) {
        if(mModel.setNotifications(context, programData)) {
            mView.navigateToNotiListActivity();
        }
    }

    @Override
    public void onRecyclerViewItem(android.view.View view, ProgramData programData) {

        mView.showSetDialog(programData, mModel.getChoiceTime(view.getContext()), -1);

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