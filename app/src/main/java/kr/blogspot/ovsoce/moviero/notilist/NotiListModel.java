package kr.blogspot.ovsoce.moviero.notilist;

import android.content.Context;

import java.util.ArrayList;

import kr.blogspot.ovsoce.moviero.app.MyApplication;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class NotiListModel {

    public ArrayList<ProgramData> getList(Context context) {
        MyApplication app = (MyApplication)context.getApplicationContext();
        return app.getDatabaseHelper().getNotificationsList();
    }

    public boolean removeItem(Context context, String scheduleId) {
        MyApplication app = (MyApplication)context.getApplicationContext();
        return app.getDatabaseHelper().updateNotificationsValue(scheduleId, "-1");
    }
}
