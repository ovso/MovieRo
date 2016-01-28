package kr.blogspot.ovsoce.moviero.search;

import android.content.Context;

/**
 * Created by ovso on 2016. 1. 28..
 */
public interface MovieSearchPresenter {
    void onCreate(Context context);
    interface View {
        void onInit();
        void search(String url);
    }
}
