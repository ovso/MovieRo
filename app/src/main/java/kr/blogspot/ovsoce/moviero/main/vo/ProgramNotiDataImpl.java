package kr.blogspot.ovsoce.moviero.main.vo;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramNotiData;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class ProgramNotiDataImpl implements ProgramNotiData {
    private String scheduleId;
    private String programMasterId;
    private String scheduleName;
    private String beginDate;
    private String beginTime;
    private String endTime;
    private String runtime;
    private String largeGenreId;
    private String episodeNo;
    private String live;
    private String rebroadcast;
    private String hd;
    private String audio;
    private String screenExplain;
    private String caption;
    private String ageRating;
    private String subtitle;
    private String signLanguage;
    private String channelName;
    private String notificationsTime;


    @Override
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String getProgramMasterId() {
        return programMasterId;
    }

    public void setProgramMasterId(String programMasterId) {
        this.programMasterId = programMasterId;
    }

    @Override
    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    @Override
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    @Override
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @Override
    public String getLargeGenreId() {
        return largeGenreId;
    }

    public void setLargeGenreId(String largeGenreId) {
        this.largeGenreId = largeGenreId;
    }

    @Override
    public String getEpisodeNo() {
        return episodeNo;
    }

    public void setEpisodeNo(String episodeNo) {
        this.episodeNo = episodeNo;
    }

    @Override
    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    @Override
    public String getRebroadcast() {
        return rebroadcast;
    }

    public void setRebroadcast(String rebroadcast) {
        this.rebroadcast = rebroadcast;
    }

    @Override
    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String getScreenExplain() {
        return screenExplain;
    }

    public void setScreenExplain(String screenExplain) {
        this.screenExplain = screenExplain;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String getSignLanguage() {
        return signLanguage;
    }

    public void setSignLanguage(String signLanguage) {
        this.signLanguage = signLanguage;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String getNotificationsTime() {
        return notificationsTime;
    }

    public void setNotificationsTime(String notificationsTime) {
        this.notificationsTime = notificationsTime;
    }
}
