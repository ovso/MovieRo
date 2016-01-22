package kr.blogspot.ovsoce.moviero.main.vo.vointerface;

import java.util.List;

/**
 * Created by jaeho_oh on 2016-01-21.
 */
public interface MovieData {
    /**
     * 날짜별 영화 데이터를 가져옴(6~7일간 데이터)
     * @return
     */
    List<MovieDataByDate> getMovieDataByDateList();
}
