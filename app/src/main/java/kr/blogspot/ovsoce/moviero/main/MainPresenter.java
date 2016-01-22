package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;

/**
 * Created by ovso on 2016. 1. 20..
 */
public interface MainPresenter {
    void onCreate(Context context);
    interface View {
        void onInit();
        void initRecyclerView(List<ProgramItem> list);
    }
}
