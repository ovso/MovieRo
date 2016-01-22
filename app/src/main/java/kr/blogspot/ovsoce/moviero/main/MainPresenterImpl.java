package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

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
}
