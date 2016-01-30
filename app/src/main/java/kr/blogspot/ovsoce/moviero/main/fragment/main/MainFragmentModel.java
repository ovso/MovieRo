package kr.blogspot.ovsoce.moviero.main.fragment.main;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.app.MyApplication;
import kr.blogspot.ovsoce.moviero.common.Common;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.db.DatabaseHelper;
import kr.blogspot.ovsoce.moviero.main.vo.ChannelDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.MovieDataByDateImpl;
import kr.blogspot.ovsoce.moviero.main.vo.MovieDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ChannelData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieDataByDate;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 30..
 */
public class MainFragmentModel {
    public ArrayList<ProgramData> getProgramList(MovieData movieData) {
        ArrayList<ProgramData> programDataList = new ArrayList<>();

        int size = movieData.getMovieDataByDateList().size();
        //Log.d("date length = " + size + "일");

        int count = 0;
        int movieCount = 0;

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < movieData.getMovieDataByDateList().get(i).getChannelDataList().size(); j++) {

                for (int k = 0; k < movieData.getMovieDataByDateList().get(i).getChannelDataList().get(j).getProgramDataList().size(); k++) {
                    ProgramData d = movieData.getMovieDataByDateList().get(i).getChannelDataList().get(j).getProgramDataList().get(k);
                    if (d.getLargeGenreId().equalsIgnoreCase("B")) {

                        programDataList.add(d);

                        movieCount++;
                    }
                    count++;
                }

            }
        }

        //Log.d("Total program count = " + count);
        Log.d("movie count = " + movieCount);
        return programDataList;
    }
    public String[] getChoiceTime(Context context) {
        return context.getResources().getStringArray(R.array.time_single_choice_items);
    }
    public String[] getSortChoiceItems(Context context) {
        return context.getResources().getStringArray(R.array.sort_single_choice_items);
    }
    public MovieData getMovieData(Context context) {
        String[] jsonMovieData = {
                Common.loadJSONFromAsset(context, "moviero01.json"),
                Common.loadJSONFromAsset(context, "moviero02.json"),
                Common.loadJSONFromAsset(context, "moviero03.json"),
                Common.loadJSONFromAsset(context, "moviero04.json"),
                Common.loadJSONFromAsset(context, "moviero05.json"),
                Common.loadJSONFromAsset(context, "moviero06.json"),
                Common.loadJSONFromAsset(context, "moviero07.json"),
        };

        MovieDataImpl movieData = new MovieDataImpl();

        try {

            List<MovieDataByDate> movieDataByDateList = new ArrayList<>();
            for (int i = 0; i < jsonMovieData.length; i++) {

                MovieDataByDateImpl movieDataByDate = new MovieDataByDateImpl();

                JSONArray jsonArray = new JSONArray(jsonMovieData[i]);

                List<ChannelData> channelDataList = new ArrayList<>();
                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);

                    ChannelDataImpl channelData = new ChannelDataImpl();

                    channelData.setBroadcastType(jsonObject.getString("broadcastType"));
                    channelData.setBroadcastName(jsonObject.getString("broadcastName"));
                    channelData.setIsMyChannel(jsonObject.getString("isMyChannel"));
                    channelData.setAdult(jsonObject.getString("adult"));
                    channelData.setChannelId(jsonObject.getString("channelId"));
                    channelData.setChannelName(jsonObject.getString("channelName"));
                    channelData.setImageMulti(jsonObject.getString("imageMulti"));
                    channelData.setChannelBeginDate(jsonObject.getString("channelBeginDate"));
                    channelData.setChannelBeginTime(jsonObject.getString("channelBeginTime"));

                    JSONArray programJsonArray = jsonObject.getJSONArray("programList");

                    List<ProgramData> programDataList = new ArrayList<>();
                    for (int k = 0; k < programJsonArray.length(); k++) {
                        JSONObject programJsonObject = programJsonArray.getJSONObject(k);

                        ProgramDataImpl programData = new ProgramDataImpl();

                        programData.setScheduleId(programJsonObject.getString("scheduleId"));
                        programData.setProgramMasterId(programJsonObject.getString("programMasterId"));
                        programData.setScheduleName(programJsonObject.getString("scheduleName"));
                        programData.setBeginDate(programJsonObject.getString("beginDate"));
                        programData.setBeginTime(programJsonObject.getString("beginTime"));
                        programData.setEndTime(programJsonObject.getString("endTime"));
                        programData.setRuntime(programJsonObject.getString("runtime"));
                        programData.setLargeGenreId(programJsonObject.getString("largeGenreId"));
                        programData.setEpisodeNo(programJsonObject.getString("episodeNo"));
                        programData.setLive(programJsonObject.getString("live"));
                        programData.setRebroadcast(programJsonObject.getString("rebroadcast"));
                        programData.setHd(programJsonObject.getString("hd"));
                        programData.setAudio(programJsonObject.getString("audio"));
                        programData.setScreenExplain(programJsonObject.getString("screenExplain"));
                        programData.setCaption(programJsonObject.getString("caption"));
                        programData.setAgeRating(programJsonObject.getString("ageRating"));
                        programData.setSubtitle(programJsonObject.getString("subtitle"));
                        programData.setSignLanguage(programJsonObject.getString("signLanguage"));
                        programData.setChannelName(jsonObject.getString("channelName"));
                        programData.setNotificationsValue("-1"); // JSON 존재하지 않는 기본값
                        programDataList.add(programData);
                    }

                    channelData.setProgramDataList(programDataList);
                    channelDataList.add(channelData);
                }
                movieDataByDate.setChannelDataList(channelDataList);
                movieDataByDateList.add(movieDataByDate);
            }
            movieData.setMovieDataByDateList(movieDataByDateList);

            return movieData;
        } catch (JSONException e) {
            return movieData;
        }

    }
    private int choiceNotifications = -1;
    public void setChoiceNoti(int which) {
        choiceNotifications = which;
    }
    public boolean setNotifications(Context context, ProgramData programData) {
        MyApplication app = (MyApplication) context.getApplicationContext();
        return app.getDatabaseHelper().updateNotificationsValue(programData.getScheduleId(), String.valueOf(choiceNotifications));

    }
    public ProgramData getProgramData(Context context, String scheduleId) {
        return ((MyApplication)context.getApplicationContext()).getDatabaseHelper().getProgramData(scheduleId);
    }

    public DatabaseHelper getDatabaseHelper(Context context) {
        return ((MyApplication)context.getApplicationContext()).getDatabaseHelper();
    }
}
