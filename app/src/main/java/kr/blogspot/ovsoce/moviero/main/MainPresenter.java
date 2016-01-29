package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

public interface MainPresenter extends OnRecyclerViewItemClick,DialogChoice {
    void onCreate(Context context);
    void onQueryTextChange(String newText);
    void onClick(android.view.View v);
    boolean onNavigationItemSelected(int id);
    interface View extends Presenter.View {
        void startFilter(CharSequence newText);
        boolean navigateToNotiListActivity();
    }
}
