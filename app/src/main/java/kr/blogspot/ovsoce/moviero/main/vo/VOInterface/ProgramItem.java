package kr.blogspot.ovsoce.moviero.main.vo.vointerface;

/**
 * Created by ovso on 2016. 1. 21..
 */
public interface ProgramItem {
    String getName();
    String getBeginDate();
    String getBeginTime();
    String getEndTime();
    String getRuntime();
    String getLargeGenrdId();
    boolean isCaption();
    String getAgeRating();
    String getSubTitle();
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
}
