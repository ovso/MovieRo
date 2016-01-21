package kr.blogspot.ovsoce.moviero.main.vo.VOInterface;

import java.util.List;

/**
 * Created by ovso on 2016. 1. 21..
 */
public interface ChannelItem {
    String getName();
    String getImageMulti();
    List<ProgramItem> getProgramList();
/*
        "broadcastType": 200,
        "broadcastName": "케이블",
        "isMyChannel": false,
        "adult": "N",
        "channelId": 22,
        "channelName": "OCN",
        "imageMulti": "http://static.news.naver.net/image/tvguide/txt_200_22.gif",
        "channelBeginDate": "2016-01-18",
        "channelBeginTime": "23:00",
        "programList": [

 */
}
