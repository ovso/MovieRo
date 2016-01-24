package kr.blogspot.ovsoce.moviero.main;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter implements Filterable{
    public List<ProgramData> getSearchList() {
        return mSearchList;
    }
    List<ProgramData> mList;
    List<ProgramData> mSearchList;
    public RecyclerViewAdapter(List<ProgramData> list) {
        mList = list;
        mSearchList = (List<ProgramData>) ((ArrayList<ProgramData>)list).clone();
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProgramData data = mSearchList.get(position);
        String name = data.getScheduleName();
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.titleTv.setText(name);
        String desc = data.getBeginTime()+"~"+data.getEndTime()+" | "+data.getRuntime()+"분 | "+data.getAgeRating()+"";
        myViewHolder.descriptionTv.setText(desc);
        //myViewHolder.blockV.setBackgroundColor(Color.parseColor(data.getColor()));
    }

    @Override
    public int getItemCount() {
        return mSearchList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
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
                } else {
                    mSearchList = (List<ProgramData>) ((ArrayList<ProgramData>)mList).clone();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //Log.d("result.values=" + results.values);
                //Log.d("result.count=" + results.count);
                if (results.values != null) {
                    mSearchList = (List<ProgramData>) results.values;
                    notifyDataSetChanged();
                } else {
                    mSearchList = (List<ProgramData>) ((ArrayList<ProgramData>)mList).clone();
                    notifyDataSetChanged();
                }
            }
        };

    }

    private OnRecyclerViewAdapterClickListener onRecyclerViewAdapterClickListener(final View itemView) {
        return new OnRecyclerViewAdapterClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClickItem(itemView);
            }
        };
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
            itemView.findViewById(R.id.recyclerview_assist_item).setOnClickListener(onRecyclerViewAdapterClickListener(itemView));
        }
    }

    public interface OnRecyclerViewAdapterClickListener extends android.view.View.OnClickListener{//, android.view.View.OnLongClickListener

    }
    public interface OnRecyclerViewItemClickListener{
        void onClickItem(View view);
        //void onLongClickItem(int position);
    }
}
