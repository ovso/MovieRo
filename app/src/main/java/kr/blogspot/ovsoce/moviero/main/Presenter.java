package kr.blogspot.ovsoce.moviero.main;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 30..
 */
public interface Presenter {

    interface View {
        void showSetDialog(ProgramData programData, String[] choiceItems, int checkedItem);
        void showSortDialog(String[] choiceItems, int checkedItem);
        void onInit();
        void initRecyclerView(ArrayList<ProgramData> list);
        void navigateToMovieSearchActivity(String movieName);
        void showProgress();
        void hideProgress();
        void updateRecyclerView(ProgramData data);
        void startFilter(CharSequence newText);
    }


}
