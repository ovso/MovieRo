package kr.blogspot.ovsoce.moviero.main.vo;

import java.util.List;

import kr.blogspot.ovsoce.moviero.main.vo.VOInterface.ChannelItem;
import kr.blogspot.ovsoce.moviero.main.vo.VOInterface.ProgramItem;

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

    List<ProgramItem> list;
    @Override
    public String getImageMulti() {
        return null;
    }
    @Override
    public List<ProgramItem> getProgramList() {
        return null;
    }
    public void setProgramList(List<ProgramItem> list) {
        this.list = list;
    }
}
