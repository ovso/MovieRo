package kr.blogspot.ovsoce.moviero.main;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 30..
 */
public interface Presenter {

    interface View {
        void onInit();
        void showProgress();
        void hideProgress();
    }

    interface IRecyclerView {
        void updateRecyclerView(ProgramData data);
        void initRecyclerView(ArrayList<ProgramData> list);
        void navigateToMovieSearchActivity(String movieName);
    }

}
