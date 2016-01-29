package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.app.MyApplication;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.db.DatabaseHelper;
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

        final ArrayList<ProgramData> list = mModel.getProgramList(movieData);
        // DB 등록.
        final DatabaseHelper helper = ((MyApplication) context.getApplicationContext()).getDatabaseHelper();
        //Log.d("before data list = " + helper.get);
        ArrayList<ProgramData> list2;
        new AsyncTask<Void, Void, ArrayList<ProgramData>>() {

            @Override
            protected void onPreExecute() {
                //super.onPreExecute();
                mView.showProgress();
            }

            @Override
            protected ArrayList<ProgramData> doInBackground(Void... params) {
//                Log.d("list.size = " + list.size());
                if(helper.queryNumEntries() < 1) {
                    for (int i = 0; i < list.size(); i++) {
                        helper.insertProgramData(list.get(i));
                    }
                }

                return helper.getProgramDataList();
            }

            @Override
            protected void onPostExecute(ArrayList<ProgramData> datas) {
                //super.onPostExecute(aVoid);
                mView.initRecyclerView(datas);
                mView.hideProgress();
            }
        }.execute();
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
        mModel.setNotifications(context, programData);
        ProgramData data = mModel.getProgramData(context, programData.getScheduleId());
        mView.updateRecyclerView(data);
        //mView.navigateToNotiListActivity();
    }

    @Override
    public void onRecyclerViewItemClick(android.view.View view, ProgramData programData) {
        int id = view.getId();
        if(id == R.id.ibtn_notifications) {
            //int checkItem = view.getTag();
            mView.showSetDialog(programData, mModel.getChoiceTime(view.getContext()), Integer.parseInt(programData.getNotificationsValue()));
        } else {
            mView.navigateToMovieSearchActivity(programData.getScheduleName());
        }
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