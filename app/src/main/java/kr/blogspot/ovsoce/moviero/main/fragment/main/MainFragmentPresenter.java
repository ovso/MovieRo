package kr.blogspot.ovsoce.moviero.main.fragment.main;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.main.DialogChoice;
import kr.blogspot.ovsoce.moviero.main.OnRecyclerViewItemClick;
import kr.blogspot.ovsoce.moviero.main.Presenter;

/**
 * Created by ovso on 2016. 1. 30..
 */
public interface MainFragmentPresenter extends OnRecyclerViewItemClick,DialogChoice {
    void onCreateView(Context context);
    void onQueryTextChange(String newText);
    void onClick(android.view.View v);

    interface View extends Presenter.View {

    }
}
