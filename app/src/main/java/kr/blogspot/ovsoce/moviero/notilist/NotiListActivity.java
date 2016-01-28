package kr.blogspot.ovsoce.moviero.notilist;

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
    private RecyclerViewAdapter mRecyclerViewAdapter;
    @Override
    public void onRecyclerView(ArrayList<ProgramData> list) {
        for (int i = 0; i < list.size(); i++) {
            Log.d(list.get(i).getScheduleName()+", " + list.get(i).getNotificationsTime());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_noti);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(list);
        //mRecyclerViewAdapter.setOnClickListener(onRecyclerViewItemClickListener);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private View.OnClickListener onRecyclerViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
/*
            ProgramData programData = mRecyclerViewAdapter.getSearchList().get(position);
            mPresenter.onRecyclerViewItem(v, programData);
            Log.d("position = " + position);
*/
        }
    };
}