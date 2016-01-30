package kr.blogspot.ovsoce.moviero.notilist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.RecyclerViewAdapter;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class NotiListActivity extends AppCompatActivity implements NotiListPresenter.View {
    NotiListPresenter mPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_list);

        mPresenter = new NotiListPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onInit() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private RecyclerView mRecyclerView;
    @Override
    public void initRecyclerView(ArrayList<ProgramData> list) {
        for (int i = 0; i < list.size(); i++) {
            Log.d(list.get(i).getScheduleName()+", " + list.get(i).getNotificationsValue());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_noti);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        adapter.setOnClickListener(onRecyclerViewItemClickListener);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToMovieSearchActivity(String movieName) {
        Intent intent = new Intent(getString(R.string.action_activity_movie_search));
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(getString(R.string.extra_name_movie_name), movieName);
        startActivity(intent);
    }

    private ProgressDialog mProgress;
    @Override
    public void showProgress() {
        mProgress = ProgressDialog.show(NotiListActivity.this, null, getString(R.string.text_waiting));
    }

    @Override
    public void hideProgress() {
        mProgress.cancel();
        mProgress.hide();
    }

    @Override
    public void updateRecyclerView(ProgramData data) {

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

}