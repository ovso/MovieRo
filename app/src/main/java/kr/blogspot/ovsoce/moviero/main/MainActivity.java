package kr.blogspot.ovsoce.moviero.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //Common.loadJSONFromAsset(getApplicationContext());
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

        mSearchView.setOnQueryTextListener(mOnQueryTextListener);
        mSearchView.setOnSearchViewListener(mSearchViewListener);
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
        mRecyclerViewAdapter = new RecyclerViewAdapter(list);
        mRecyclerViewAdapter.setOnClickListener(onRecyclerViewItemClickListener);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }
    private View.OnClickListener onRecyclerViewItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            ProgramData programData = mRecyclerViewAdapter.getSearchList().get(position);
            mPresenter.onRecyclerViewItem(v, programData);
            Log.d("position = " + position);
        }
    };
    RecyclerViewAdapter mRecyclerViewAdapter;
    @Override
    public void setSuggestion(String[] names) {
        //mSearchView.setSuggestions(names);
    }

    @Override
    public void startFilter(CharSequence s) {
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.getFilter().filter(s, MainActivity.this);
        }
    }

    @Override
    public void showSetDialog(final ProgramData programData, String[] choiceItems, int checkedItem) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        View customTitleView = getLayoutInflater().inflate(R.layout.dialog_set_title, null);
        ((TextView) customTitleView.findViewById(R.id.tv_title)).setText(programData.getScheduleName());
        ab.setCustomTitle(customTitleView);

        ab.setSingleChoiceItems(choiceItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.onChoiceNoti(which);
            }
        });
        ab.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
    public void navigateToSearch(String name) {
        Log.d("name = " + name);
    }

    @Override
    public void onFilterComplete(int count) {
        //Log.d("count="+count);
    }

    @Override
    public void onClick(View v) {
        mPresenter.onClick(v);
    }
}
