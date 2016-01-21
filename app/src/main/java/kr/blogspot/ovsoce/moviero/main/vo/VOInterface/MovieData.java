package kr.blogspot.ovsoce.moviero.main.vo.VOInterface;

import java.util.List;

/**
 * Created by jaeho_oh on 2016-01-21.
 */
public interface MovieData {
    /**
     * 날짜별 영화 데이터를 가져옴
     * @return
     */
    List<MovieDataByDate> getMovieDataByDateList();
}
