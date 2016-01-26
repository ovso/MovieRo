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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class RecyclerViewAdapter extends RecyclerView.Adapter implements Filterable{
    public List<ProgramData> getSearchList() {
        return mSearchList;
    }
    ArrayList<ProgramData> mList;
    ArrayList<ProgramData> mSearchList;
    public RecyclerViewAdapter(ArrayList<ProgramData> list) {
        mList = list;
        mSearchList = (ArrayList<ProgramData>) mList.clone();
    }
    private View.OnClickListener onItemClickListener;
    public void setOnClickListener(View.OnClickListener listener) {
        onItemClickListener = listener;
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
        myViewHolder.descriptionTv.setText(getDescription(data));
        myViewHolder.channelTv.setText(data.getChannelName());
        myViewHolder.assistV.setTag(position);
        myViewHolder.notificationsIv.setTag(position);
    }
    private String getDescription(ProgramData data) {
        StringBuilder descSb = new StringBuilder();
        descSb.append(getDay(data));
        descSb.append(" | ");
        descSb.append(data.getBeginTime()+"~"+data.getEndTime());
        descSb.append(" | ");
        descSb.append(data.getRuntime()+"분");
        descSb.append(" | ");
        descSb.append(data.getAgeRating().equals("0")?"전체":data.getAgeRating()+"세");

        return descSb.toString();
    };
    private String getDay(ProgramData data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        String today = sdf.format(new Date());
        String beginDate = null;
        try {
            beginDate = sdf.format(sdf.parse(data.getBeginDate().replaceAll("-", "")));
        } catch (ParseException e) {
            e.printStackTrace();
            beginDate = "";
        }
        //Log.d("beginDate = " + beginDate);
        //Log.d("today = " + today);
        int beginDateInt = Integer.parseInt(beginDate);
        int todayInt = Integer.parseInt(today);

        String rtnDay;
        if(todayInt > beginDateInt) {
            int tempDay = (todayInt - beginDateInt);
            switch (tempDay) {
                case 1:
                    rtnDay = "어제";
                    break;
                case 2:
                    rtnDay = "그저께";
                    break;
                case 3:
                    rtnDay = "그끄저께";
                    break;
                default:
                    rtnDay = tempDay+"일 전";
                    break;
            }

        } else if(todayInt == beginDateInt) {
            rtnDay = "오늘";
        } else {
            int tempDay = Math.abs(todayInt - beginDateInt);
            switch (tempDay) {
                case 1:
                    rtnDay = "내일";
                    break;
                case 2:
                    rtnDay = "모레";
                    break;
                case 3:
                    rtnDay = "글피";
                    break;
                case 4:
                    rtnDay = "그글피";
                    break;
                default:
                    rtnDay = tempDay+"일 후";
                    break;
            }
        }
        return rtnDay;
    }
/*
    private String stringDate(String date) {
        String movieDate =
        String format = new String("yy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
        String  = sdf.format(new Date());
        return d;
    }
*/
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
                    mSearchList = (ArrayList<ProgramData>) mList.clone();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //Log.d("result.values=" + results.values);
                //Log.d("result.count=" + results.count);
                if (results.values != null) {
                    mSearchList = (ArrayList<ProgramData>) results.values;
                    notifyDataSetChanged();
                } else {
                    mSearchList = (ArrayList<ProgramData>) mList.clone();
                    notifyDataSetChanged();
                }
            }
        };
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView channelTv;
        TextView titleTv;
        TextView descriptionTv;
        ImageView notificationsIv;
        View assistV;

        public MyViewHolder(View itemView) {
            super(itemView);
            channelTv = (TextView) itemView.findViewById(R.id.tv_channel_name);
            notificationsIv = (ImageView) itemView.findViewById(R.id.iv_notification);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            descriptionTv = (TextView) itemView.findViewById(R.id.tv_description);
            assistV = itemView.findViewById(R.id.recyclerview_assist_item);
            assistV.setOnClickListener(onItemClickListener);
            notificationsIv.setOnClickListener(onItemClickListener);
        }
    }

}
