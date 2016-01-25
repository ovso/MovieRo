package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.widget.Filterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Common;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.vo.ChannelDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.MovieDataByDateImpl;
import kr.blogspot.ovsoce.moviero.main.vo.MovieDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramDataImpl;
import kr.blogspot.ovsoce.moviero.main.vo.ProgramItemImpl;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ChannelData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieDataByDate;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;

/**
 * Created by ovso on 2016. 1. 20..
 */
public class MainModel {
    private List<ProgramData> searchList;
    public ProgramData getProgramData(int position) {
        return searchList.get(position);
    }
    public void onQueryTextChange(String newText, Filterable filterable) {
        filterable.getFilter().filter(newText);
        searchList = ((RecyclerViewAdapter)filterable).getSearchList();
    }
    public List<ProgramData> getProgramList(MovieData movieData) {
        List<ProgramData> programDataList = new ArrayList<>();

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

    public List<ProgramItem> getProgramList(Context context) {
        String[] channelJsons = {
                Common.loadJSONFromAsset(context, "moviero01.json"),
                Common.loadJSONFromAsset(context, "moviero02.json"),
                Common.loadJSONFromAsset(context, "moviero03.json"),
                Common.loadJSONFromAsset(context, "moviero04.json"),
                Common.loadJSONFromAsset(context, "moviero05.json"),
                Common.loadJSONFromAsset(context, "moviero06.json"),
                Common.loadJSONFromAsset(context, "moviero07.json"),
                Common.loadJSONFromAsset(context, "moviero08.json"),
        };
        //List<ChannelItem> list = new ArrayList<>();
        List<ProgramItem> programList = new ArrayList<>();
        for (int i = 0; i < channelJsons.length; i++) {
            try {
                JSONArray jsonArray = new JSONArray(channelJsons[i]); // 채널 12개..

                for (int j = 0; j < jsonArray.length(); j++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    JSONArray programListJsonArray = jsonObject.getJSONArray("programList");

                    for (int k = 0; k < programListJsonArray.length(); k++) {
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
    public String[] getChoiceItems(Context context) {
        return context.getResources().getStringArray(R.array.single_choice_items);
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
                Common.loadJSONFromAsset(context, "moviero08.json"),
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
}
