package kr.blogspot.ovsoce.moviero.main.vo.VOInterface;

import java.util.List;

/**
 * Created by jaeho_oh on 2016-01-21.
 */
public interface MovieDataByDate {
    String getBroadcastType();
    String broadcastName();
    String getMyChannel();
    String getAdult();
    String getChannelId();
    String getChannelName();
    String getImageMulti();
    String getChannelBeginDate();
    String getChannelBeginTime();
    List<ProgramData> getProgramDataList();
}
