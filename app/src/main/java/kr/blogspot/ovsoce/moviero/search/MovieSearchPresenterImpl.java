package kr.blogspot.ovsoce.moviero.search;

import android.content.Context;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class MovieSearchPresenterImpl implements MovieSearchPresenter {
    MovieSearchPresenter.View mView;
    MovieSearchModel mModel;
    public MovieSearchPresenterImpl(MovieSearchPresenter.View view) {
        mView = view;
        mModel = new MovieSearchModel();
    }

    @Override
    public void onCreate(Context context) {
        mView.onInit();
        mView.search(mModel.getUrl(context));
    }
}
