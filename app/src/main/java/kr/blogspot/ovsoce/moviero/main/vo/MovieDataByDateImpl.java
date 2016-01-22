package kr.blogspot.ovsoce.moviero.main.vo;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ChannelData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieDataByDate;

/**
 * Created by jaeho_oh on 2016-01-22.
 */
public class MovieDataByDateImpl implements MovieDataByDate {
    List<ChannelData> list;
    @Override
    public List<ChannelData> getChannelDataList() {
        return list;
    }
    public void setChannelDataList(List<ChannelData> list) {
        this.list = list;
    }
}
