package kr.blogspot.ovsoce.moviero.main.vo.vointerface;

import java.util.List;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;
/**
 * Created by ovso on 2016. 1. 21..
 */
public interface ChannelItem {
    String getName();
    String getImageMulti();
    List<ProgramItem> getProgramList();
}
