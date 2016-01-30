package kr.blogspot.ovsoce.moviero.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.fragment.main.MainFragment;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View, Filter.FilterListener, View.OnClickListener{

    MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());
    }
    MaterialSearchView mSearchView;
    RecyclerView mRecyclerView;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return mPresenter.onNavigationItemSelected(item.getItemId());
    }


    @Override
    public void onInit() {

        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_float).setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_camera);

        //mSearchView.setOnQueryTextListener(mOnQueryTextListener);
        //mSearchView.setOnSearchViewListener(mSearchViewListener);
    }
    private MaterialSearchView.SearchViewListener mSearchViewListener = new MaterialSearchView.SearchViewListener() {
        @Override
        public void onSearchViewShown() {
            //Log.d("");
        }

        @Override
        public void onSearchViewClosed() {
            //Log.d("");
        }
    };
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
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
    public void startFilter(CharSequence s) {
        if (mRecyclerView.getAdapter() != null) {
            ((RecyclerViewAdapter)mRecyclerView.getAdapter()).getFilter().filter(s, MainActivity.this);
        }
    }

    @Override
    public void showSetDialog(final ProgramData programData, String[] choiceItems, final int checkedItem) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        View customTitleView = getLayoutInflater().inflate(R.layout.dialog_set_title, null);
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
                mPresenter.onChoiceNotiOK(getApplicationContext(), programData);
            }
        });
        ab.setNeutralButton(R.string.text_notifications_off, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onChoiceNoti(-1);
                mPresenter.onChoiceNotiOK(getApplicationContext(), programData);
            }
        });
        ab.setNegativeButton(R.string.text_cancel, null);
        ab.show();
    }

    @Override
    public void showSortDialog(String[] choiceItems, int checkedItem) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        View customTitleView = getLayoutInflater().inflate(R.layout.dialog_sort_title, null);
        //((TextView) customTitleView.findViewById(R.id.tv_title)).setText(getResources().getString(R.string.title_sort_dilaog));
        ab.setCustomTitle(customTitleView);
        ab.setSingleChoiceItems(choiceItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ab.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ab.setNegativeButton(R.string.text_cancel, null);
        ab.show();
    }

    @Override
    public boolean navigateToNotiListActivity() {
        Intent intent = new Intent("kr.blogspot.ovsoce.moviero.notilist");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return false;
    }


    private MainFragment mMainFragment;
    @Override
    public void replaceFragment() {
        if( mMainFragment == null ) {
            mMainFragment = new MainFragment();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, mMainFragment).commit();
    }

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
        mProgress = ProgressDialog.show(this, null, "Wait..." );
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

    /*
     */
    @Override
    public void onFilterComplete(int count) {
        //Log.d("count="+count);
    }

    @Override
    public void onClick(View v) {
        mPresenter.onClick(v);
    }
}
