package kr.blogspot.ovsoce.moviero.search;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.R;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class MovieSearchModel {
    public String getUrl(Context context) {
        return context.getResources().getString(R.string.url_movie_search);
    }
}
