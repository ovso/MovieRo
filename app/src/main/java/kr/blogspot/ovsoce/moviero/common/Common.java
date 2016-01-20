package kr.blogspot.ovsoce.moviero.common;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ovso on 2016. 1. 19..
 */
public class Common {

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("moviero01.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("TAG", json);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            Log.d("OJH", "jsonArray.length = " + jsonArray.length());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray array = jsonObject.getJSONArray("programList");
            Log.d("OJH", "array.length = " + array.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
/*        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("formules");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("formule"));
                String formula_value = jo_inside.getString("formule");
                String url_value = jo_inside.getString("url");

                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("formule", formula_value);
                m_li.put("url", url_value);

                formList.add(m_li);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
