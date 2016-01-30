package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

public interface MainPresenter{
    void onCreate(Context context);
    void onClick(android.view.View v);
    boolean onNavigationItemSelected(int id);

    interface View extends Presenter.View{
        boolean navigateToNotiListActivity();
        void replaceFragment();
    }
}
