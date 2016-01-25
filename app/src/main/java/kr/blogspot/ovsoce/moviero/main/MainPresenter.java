package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.view.View;
import android.widget.Filterable;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;

/**
 * Created by ovso on 2016. 1. 20..
 */
public interface MainPresenter {
    void onCreate(Context context);
    void onQueryTextChange(String newText);
    void onClickItem(Context context, ProgramData programData);
    void onClick(android.view.View v);
    interface View {
        void onInit();
        void initRecyclerView(List<ProgramData> list);
        void setSuggestion(String[] names);
        void startFilter(CharSequence newText);
        void showSetDialog(ProgramData programData, String[] choiceItems, int checkedItem);
        void showSortDialog(String[] choiceItems, int checkedItem);
    }
}
