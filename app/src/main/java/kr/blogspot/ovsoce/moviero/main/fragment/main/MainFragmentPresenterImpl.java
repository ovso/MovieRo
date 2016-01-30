package kr.blogspot.ovsoce.moviero.main.fragment.main;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.db.DatabaseHelper;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 30..
 */
public class MainFragmentPresenterImpl implements MainFragmentPresenter {
    MainFragmentPresenter.View mView;
    MainFragmentModel mModel;
    public MainFragmentPresenterImpl(MainFragmentPresenter.View view){
        mView = view;
        mModel = new MainFragmentModel();
    }
    @Override
    public void onCreateView(Context context) {
        mView.onInit();
        MovieData movieData = mModel.getMovieData(context);

        final ArrayList<ProgramData> list = mModel.getProgramList(movieData);
        // DB 등록.
        final DatabaseHelper helper = mModel.getDatabaseHelper(context);
        new AsyncTask<Void, Void, ArrayList<ProgramData>>() {

            @Override
            protected void onPreExecute() {
                //super.onPreExecute();
                mView.showProgress();
            }

            @Override
            protected ArrayList<ProgramData> doInBackground(Void... params) {
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
}
