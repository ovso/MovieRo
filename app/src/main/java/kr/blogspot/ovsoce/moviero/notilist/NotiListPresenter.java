package kr.blogspot.ovsoce.moviero.notilist;

import android.content.Context;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 28..
 */
public interface NotiListPresenter {
    void onCreate(Context context);
    void onRecyclerViewItemClick(android.view.View view, ProgramData programData);
    interface View {
        void onInit();
        void onRecyclerView(ArrayList<ProgramData> list);
    }
}
