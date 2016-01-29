package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

/**
 * Created by ovso on 2016. 1. 30..
 */
public interface DialogChoice {
    void onChoiceNoti(int which);
    void onChoiceNotiOK(Context context, ProgramData programData);

}
