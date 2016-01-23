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
import android.view.MotionEvent;
import android.widget.Filter;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainPresenter.View, Filter.FilterListener{

    MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate(getApplicationContext());
    }
    MaterialSearchView mSearchView;
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
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

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
            Log.d("");
        }

        @Override
        public void onSearchViewClosed() {
            Log.d("");
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
    public void initRecyclerView(List<ProgramData> list) {
        Log.d("list.size = " + list.size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(list);
        recyclerView.setAdapter(mRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(mSimpleOnItemTouchListener);

    }
    private RecyclerView.SimpleOnItemTouchListener mSimpleOnItemTouchListener = new RecyclerView.SimpleOnItemTouchListener() {
        private boolean isMoveEvent = false;
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            if(e.getAction() == MotionEvent.ACTION_UP) {
                if(!isMoveEvent) {
                    int position = rv.getChildAdapterPosition(rv.findChildViewUnder(e.getX(), e.getY()));
                    //Log.d("position = " + position);
                    mPresenter.onInterceptTouchEvent(position);
                }
                isMoveEvent = false;
            } else if(e.getAction() == MotionEvent.ACTION_MOVE) {
                isMoveEvent = true;
            }
            return super.onInterceptTouchEvent(rv, e);
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
    public void showSetDialog(final int position) {
        ProgramData data = mRecyclerViewAdapter.getSearchList().get(position);

        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("알림 설정");
        ab.setMessage(data.getScheduleName() + ", "+data.getProgramMasterId() + ", " + data.getSubtitle()+ ", "+data.getChannelName()
        + ", " + data.getRuntime());
        ab.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //mRecyclerViewAdapter.getProgramData(position);

            }
        });
        ab.setNegativeButton("취소", null);
        ab.show();
    }


    @Override
    public void onFilterComplete(int count) {
        //Log.d("count="+count);
    }
}
