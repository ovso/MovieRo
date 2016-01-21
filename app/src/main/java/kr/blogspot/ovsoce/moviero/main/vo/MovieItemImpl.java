package kr.blogspot.ovsoce.moviero.main.vo;

import kr.blogspot.ovsoce.moviero.main.vo.VOInterface.MovieItem;

/**
 * Created by ovso on 2016. 1. 21..
 */
public class MovieItemImpl implements MovieItem {
    String name;
    String beginDate;
    String beginTime;
    String endTime;
    String rnutime;
    boolean caption;
    String ageRating;
    String subtitle;
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBeginDate() {
        return null;
    }

    @Override
    public String getBeginTime() {
        return null;
    }

    @Override
    public String getEndTime() {
        return null;
    }

    @Override
    public String getRuntime() {
        return null;
    }

    @Override
    public String getLargeGenrdId() {
        return null;
    }

    @Override
    public boolean isCaption() {
        return false;
    }

    @Override
    public String getAgeRating() {
        return null;
    }

    @Override
    public String getSubTitle() {
        return null;
    }
}
