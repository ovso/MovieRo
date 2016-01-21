package kr.blogspot.ovsoce.moviero.main.vo;

import kr.blogspot.ovsoce.moviero.main.vo.VOInterface.ProgramItem;

/**
 * Created by ovso on 2016. 1. 21..
 */
public class ProgramItemImpl implements ProgramItem {
    String scheduleName;
    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }
    @Override
    public String getName() {
        return scheduleName;
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
