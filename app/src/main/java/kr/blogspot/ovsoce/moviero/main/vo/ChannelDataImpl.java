package kr.blogspot.ovsoce.moviero.main.vo;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ChannelData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by jaeho_oh on 2016-01-22.
 */
public class ChannelDataImpl implements ChannelData {
    private String broadcastType;
    private String broadcastName;
    private String isMyChannel;
    private String adult;
    private String channelId;
    private String channelName;
    private String imageMulti;
    private String channelBeginDate;
    private String channelBeginTime;
    private List<ProgramData> programDataList;

    @Override
    public String getBroadcastType() {
        return broadcastType;
    }

    public void setBroadcastType(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    @Override
    public String getBroadcastName() {
        return broadcastName;
    }

    public void setBroadcastName(String broadcastName) {
        this.broadcastName = broadcastName;
    }

    public String isMyChannel() {
        return isMyChannel;
    }

    public void setIsMyChannel(String isMyChannel) {
        this.isMyChannel = isMyChannel;
    }

    @Override
    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    @Override
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String getImageMulti() {
        return imageMulti;
    }

    public void setImageMulti(String imageMulti) {
        this.imageMulti = imageMulti;
    }

    @Override
    public String getChannelBeginDate() {
        return channelBeginDate;
    }

    public void setChannelBeginDate(String channelBeginDate) {
        this.channelBeginDate = channelBeginDate;
    }

    @Override
    public String getChannelBeginTime() {
        return channelBeginTime;
    }

    public void setChannelBeginTime(String channelBeginTime) {
        this.channelBeginTime = channelBeginTime;
    }

    @Override
    public List<ProgramData> getProgramDataList() {
        return programDataList;
    }

    public void setProgramDataList(List<ProgramData> programDataList) {
        this.programDataList = programDataList;
    }
}
