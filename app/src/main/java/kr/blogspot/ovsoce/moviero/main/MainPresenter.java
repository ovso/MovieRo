package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

/**
 * Created by ovso on 2016. 1. 20..
 */
public interface MainPresenter {
    void onCreate(Context context);
    interface View {
        void onInit();
    }
}
