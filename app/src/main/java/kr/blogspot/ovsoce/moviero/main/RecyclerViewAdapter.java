package kr.blogspot.ovsoce.moviero.main;

import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.common.Log;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramItem;

/**
 * Created by ovso on 2016. 1. 21..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter implements Filterable {

    List<ProgramData> mList;
    List<ProgramData> mSearchList;
    RecyclerView mRecyclerView;
    OnAdapterItemClickListener mListener;
    public RecyclerViewAdapter(List<ProgramData> list) {
        mList = list;
        mSearchList = (List<ProgramData>) ((ArrayList<ProgramData>)list).clone();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProgramData data = mList.get(position);
        String name = data.getScheduleName();
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.titleTv.setText(name);
        //myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));

    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(!TextUtils.isEmpty(constraint)) {
                    ArrayList<ProgramData> tempArrayList = new ArrayList<>();
                    for (ProgramData data : mList) {
                        if(data.getScheduleName().toLowerCase().contains(constraint)) {
                            tempArrayList.add(data);
                        }
                    }

                    filterResults.values = tempArrayList;
                    filterResults.count = tempArrayList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d("result.values=" + results.values);
                Log.d("result.count=" + results.count);
                if (results.values != null) {
                    mSearchList = (ArrayList<ProgramData>) results.values;
                    notifyDataSetChanged();
                }

            }
        };

        return filter;
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
