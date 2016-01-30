package kr.blogspot.ovsoce.moviero.notilist;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.main.OnRecyclerViewItemClick;
import kr.blogspot.ovsoce.moviero.main.Presenter;

/**
 * Created by ovso on 2016. 1. 28..
 */
public interface NotiListPresenter extends OnRecyclerViewItemClick{
    void onCreate(Context context);
    interface View extends Presenter.View,Presenter.IRecyclerView {

    }
}
