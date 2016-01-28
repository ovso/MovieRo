package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public interface MainPresenter {
    void onCreate(Context context);
    void onQueryTextChange(String newText);
    void onClick(android.view.View v);
    void onChoiceNoti(int which);
    void onChoiceNotiOK(Context context, ProgramData programData);
    void onRecyclerViewItem(android.view.View view, ProgramData programData);
    boolean onNavigationItemSelected(int id);
    interface View {
        void onInit();
        void initRecyclerView(ArrayList<ProgramData> list);
        void setSuggestion(String[] names);
        void startFilter(CharSequence newText);
        void showSetDialog(ProgramData programData, String[] choiceItems, int checkedItem);
        void showSortDialog(String[] choiceItems, int checkedItem);
        void navigateToSearch(String name);
        boolean navigateToNotiListActivity();
    }
}
