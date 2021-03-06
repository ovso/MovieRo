package kr.blogspot.ovsoce.moviero.notilist;

import android.content.Context;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

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
        mView.initRecyclerView(mModel.getList(context));
    }


    @Override
    public void onRecyclerViewItemClick(android.view.View view, ProgramData programData) {
        int id = view.getId();
        if(id == R.id.ibtn_notifications) {
            //int checkItem = view.getTag();
            //mView.showSetDialog(programData, mModel.getChoiceTime(view.getContext()), Integer.parseInt(programData.getNotificationsValue()));
            if(mModel.removeItem(view.getContext(), programData.getScheduleId())) {

                //mView.updateRecyclerView();
            }
        } else {
            mView.navigateToMovieSearchActivity(programData.getScheduleName());
        }
    }

}
