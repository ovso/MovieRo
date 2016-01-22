package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;

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
        List<ProgramItem> list = mModel.getProgramList(context);
        MovieData movieData = mModel.getMovieData(context);
        mModel.getMovieItem(movieData);
        mView.initRecyclerView(list);
    }
}
