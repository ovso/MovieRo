package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.widget.Filterable;

import java.util.List;

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

        List<ProgramData> list = mModel.getProgramList(movieData);
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
    public void onClickItem(Context context, ProgramData programData) {
        mView.showSetDialog(programData, mModel.getChoiceItems(context), -1);
    }
}
