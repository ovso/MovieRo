package kr.blogspot.ovsoce.moviero.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.main.vo.ChannelItem;

/**
 * Created by ovso on 2016. 1. 21..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    List<ChannelItem> mList;
    RecyclerView mRecyclerView;
    OnAdapterItemClickListener mListener;
    public RecyclerViewAdapter(List<ChannelItem> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelItem data = mList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.titleTv.setText(data.getName());
        Log.d("Name", data.getName());
        //myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv;
        TextView descriptionTv;
        ImageView thumbnailIv;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnailIv = (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            descriptionTv = (TextView) itemView.findViewById(R.id.tv_description);
            //itemView.setOnClickListener(mListener);
            //itemView.setOnLongClickListener(mListener);
        }
    }

    public interface OnAdapterItemClickListener extends android.view.View.OnClickListener, android.view.View.OnLongClickListener {

    }
}
