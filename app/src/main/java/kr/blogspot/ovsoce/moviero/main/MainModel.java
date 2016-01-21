package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.common.Common;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramItemImpl;
import kr.blogspot.ovsoce.moviero.main.vo.VOInterface.ProgramItem;

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
    public List<ProgramItem> getProgramList(Context context) {

        String json = Common.loadJSONFromAsset(context, "moviero01.json");
        String[] channelJsons = {
                Common.loadJSONFromAsset(context, "moviero01.json"),
                Common.loadJSONFromAsset(context, "moviero02.json"),
                Common.loadJSONFromAsset(context, "moviero03.json"),
                Common.loadJSONFromAsset(context, "moviero04.json"),
                Common.loadJSONFromAsset(context, "moviero05.json"),
                Common.loadJSONFromAsset(context, "moviero06.json"),
        };
        //List<ChannelItem> list = new ArrayList<>();
        List<ProgramItem> programList = new ArrayList<>();
        for(int i=0; i<channelJsons.length; i++) {
            try {
                JSONArray jsonArray = new JSONArray(channelJsons[i]); // 채널 12개..

                for(int j=0; j<jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    JSONArray programListJsonArray = jsonObject.getJSONArray("programList");

                    for(int k=0; k<programListJsonArray.length(); k++) {
                        JSONObject programJsonObject = programListJsonArray.getJSONObject(k);
                        ProgramItemImpl item = new ProgramItemImpl();
                        item.setScheduleName(programJsonObject.getString("scheduleName"));
                        programList.add(item);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return programList;
        //return list;
    }
}
