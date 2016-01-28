package kr.blogspot.ovsoce.moviero.notilist;

import android.content.Context;

/**
 * Created by ovso on 2016. 1. 28..
 */
public class NotiListPresenterImpl implements NotiListPresenter {
    private NotiListPresenter.View mView;
    private NotiListModel mModel;
    public NotiListPresenterImpl(NotiListPresenter.View view) {
        this.mView = view;
        mModel = new NotiListModel();
    }

    @Override
    public void onCreate(Context context) {
        mView.onInit();
        mView.onRecyclerView(mModel.getList(context));
    }
}
