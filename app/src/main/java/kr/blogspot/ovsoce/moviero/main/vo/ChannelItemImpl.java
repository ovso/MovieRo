package kr.blogspot.ovsoce.moviero.main.vo;

import java.util.List;

/**
 * Created by ovso on 2016. 1. 21..
 */
public class ChannelItemImpl implements ChannelItem {
    String name;
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImageMulti() {
        return null;
    }

    @Override
    public List<ProgramItem> getProgramList() {
        return null;
    }
}
