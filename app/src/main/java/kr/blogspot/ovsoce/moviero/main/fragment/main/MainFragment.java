package kr.blogspot.ovsoce.moviero.main.fragment.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.RecyclerViewAdapter;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class MainFragment extends Fragment implements MainFragmentPresenter.View, Filter.FilterListener {

    RecyclerView mRecyclerView;
    MainFragmentPresenter mPresenter;
    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, null);
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new MainFragmentPresenterImpl(this);
        mPresenter.onCreateView(getActivity());
    }

    @Override
    public void showSetDialog(final ProgramData programData, final String[] choiceItems, final int checkedItem) {
        final Activity activity = getActivity();
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        View customTitleView = activity.getLayoutInflater().inflate(R.layout.dialog_set_title, null);
        ((TextView) customTitleView.findViewById(R.id.tv_title)).setText(programData.getScheduleName());
        ab.setCustomTitle(customTitleView);

        mPresenter.onChoiceNoti(checkedItem);

        ab.setSingleChoiceItems(choiceItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onChoiceNoti(which);
                //Log.d("choice = " + which);
            }
        });
        ab.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onChoiceNotiOK(getActivity(), programData);
            }
        });
        ab.setNeutralButton(R.string.text_notifications_off, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onChoiceNoti(-1);
                mPresenter.onChoiceNotiOK(activity, programData);
            }
        });
        ab.setNegativeButton(R.string.text_cancel, null);
        ab.show();
    }

    @Override
    public void showSortDialog(String[] choiceItems, int checkedItem) {

    }
    MaterialSearchView mSearchView;
    @Override
    public void onInit() {
        Activity activity = getActivity();
        mSearchView = (MaterialSearchView)activity.findViewById(R.id.search_view);

        mSearchView.setOnQueryTextListener(mOnQueryTextListener);
        //mSearchView.setOnSearchViewListener(mSearchViewListener);
    }
    private MaterialSearchView.OnQueryTextListener mOnQueryTextListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            //Log.d("query = " + query);
            mSearchView.hideKeyboard(mSearchView);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            //Do some magic
            //Log.d("newText = " + newText);
            mPresenter.onQueryTextChange(newText);
            return false;
        }
    };

    @Override
    public void initRecyclerView(ArrayList<ProgramData> list) {
        Log.d("list.size = " + list.size());
        mRecyclerView = (RecyclerView)getView().findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        adapter.setOnClickListener(onRecyclerViewItemClickListener);
        mRecyclerView.setAdapter(adapter);

    }
    private View.OnClickListener onRecyclerViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.string.tag_key_position);
            ProgramData programData = ((RecyclerViewAdapter)mRecyclerView.getAdapter()).getSearchList().get(position);
            mPresenter.onRecyclerViewItemClick(v, programData);
            Log.d("position = " + position);
        }
    };

    @Override
    public void navigateToMovieSearchActivity(String movieName) {
        Intent intent = new Intent("kr.blogspot.ovsoce.moviero.moviesearch");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("name", movieName);
        startActivity(intent);

    }
    private ProgressDialog mProgress;
    @Override
    public void showProgress() {
        mProgress = ProgressDialog.show(getActivity(), null, "Wait...");
    }

    @Override
    public void hideProgress() {
        mProgress.cancel();
        mProgress.hide();
    }

    @Override
    public void updateRecyclerView(ProgramData data) {
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) mRecyclerView.getAdapter();
        ArrayList<ProgramData> searchList = adapter.getSearchList();
        ArrayList<ProgramData> originList = adapter.getList();

        for (int i = 0; i < searchList.size(); i++) {
            if(searchList.get(i).getScheduleId().equals(data.getScheduleId())) {
                searchList.set(i, data);
            }
        }
        for (int i = 0; i < originList.size(); i++) {
            if(originList.get(i).getScheduleId().equals(data.getScheduleId())) {
                originList.set(i, data);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startFilter(CharSequence newText) {
        if (mRecyclerView.getAdapter() != null) {
            ((RecyclerViewAdapter)mRecyclerView.getAdapter()).getFilter().filter(newText, MainFragment.this);
        }
    }

    @Override
    public void onFilterComplete(int count) {

    }
}
