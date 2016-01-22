package kr.blogspot.ovsoce.moviero.main.vo.vointerface;

import java.util.List;

/**
 * Created by jaeho_oh on 2016-01-21.
 */
public interface MovieDataByDate {
    /**
     * 하루의 영화 데이터를 가져온다.
     * @return
     */
    List<ChannelData> getChannelDataList();
}
