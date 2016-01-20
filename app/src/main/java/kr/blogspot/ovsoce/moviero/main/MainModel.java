package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.common.Common;
import kr.blogspot.ovsoce.moviero.main.vo.ChannelItem;
import kr.blogspot.ovsoce.moviero.main.vo.ChannelItemImpl;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramItem;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramItemImpl;

/**
 * Created by ovso on 2016. 1. 20..
 */
public class MainModel {
    /*
                "scheduleId": "P735202419",
                "programMasterId": "M614030916",
                "scheduleName": "모데카이",
                "beginDate": "2016-01-18",
                "beginTime": "23:00",
                "endTime": "00:10",
                "runtime": 70,
                "largeGenreId": "B",
                "episodeNo": "",
                "live": false,
                "rebroadcast": false,
                "hd": true,
                "audio": "",
                "screenExplain": false,
                "caption": true,
                "ageRating": 15,
                "subtitle": "2부",
                "signLanguage": false
     */
    public List<ChannelItem> getChannelList(Context context) {

        String json = Common.loadJSONFromAsset(context);
        List<ChannelItem> list = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i<jsonArray.length(); i++) {
                ChannelItemImpl item = new ChannelItemImpl();

                JSONObject object = jsonArray.getJSONObject(i);
                item.setName(object.getString("channelName"));
                JSONArray programListJsonArray = object.getJSONArray("programList");

                List<ProgramItem> programItemList = new ArrayList<>();
                for(int j=0; j<programListJsonArray.length(); j++) {
                    ProgramItemImpl programItem = new ProgramItemImpl();
                    object = programListJsonArray.getJSONObject(j);
                    programItem.setName(object.getString("scheduleName"));
                    programItemList.add(programItem);
                }
                item.setProgramList(programItemList);
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
