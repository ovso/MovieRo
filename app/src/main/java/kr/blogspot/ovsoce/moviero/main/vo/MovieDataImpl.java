package kr.blogspot.ovsoce.moviero.main.vo;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.MovieDataByDate;

/**
 * Created by jaeho_oh on 2016-01-22.
 */
public class MovieDataImpl implements MovieData {
    List<MovieDataByDate> list;
    @Override
    public List<MovieDataByDate> getMovieDataByDateList() {
        return list;
    }
    public void setMovieDataByDateList(List<MovieDataByDate> list) {
        this.list = list;
    }
}
