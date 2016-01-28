package kr.blogspot.ovsoce.moviero.notilist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.app.MyApplication;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramNotiData;

/**
 * Created by ovso on 2016. 1. 27..
 */
public class NotiListActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyApplication app = (MyApplication) getApplicationContext();
        ArrayList<ProgramNotiData> data = app.getDatabaseHelper().getNotificationsList();
        for (int i = 0; i < data.size(); i++) {
            Log.d(data.get(i).getScheduleName()+", " + data.get(i).getNotificationsTime());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(item.toString());
        finish();
        return true;
    }
}
