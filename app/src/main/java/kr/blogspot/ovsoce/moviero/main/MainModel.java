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

/**
 * Created by ovso on 2016. 1. 20..
 */
public class MainModel {
    public List<ChannelItem> getChannelList(Context context) {

        String json = Common.loadJSONFromAsset(context);
        List<ChannelItem> list = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(json);
            for(int i=0; i<jsonArray.length(); i++) {
                ChannelItemImpl item = new ChannelItemImpl();

                JSONObject object = jsonArray.getJSONObject(i);
                item.setName(object.getString("channelName"));
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
