package kr.blogspot.ovsoce.moviero.main.vo.vointerface;

import java.util.List;

/**
 * Created by jaeho_oh on 2016-01-21.
 */
public interface ChannelData {
    String getBroadcastType();
    String getBroadcastName();
    String isMyChannel();
    String getAdult();
    String getChannelId();
    String getChannelName();
    String getImageMulti();
    String getChannelBeginDate();
    String getChannelBeginTime();
    List<ProgramData> getProgramDataList();
}
