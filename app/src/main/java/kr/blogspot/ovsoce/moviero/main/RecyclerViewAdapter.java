package kr.blogspot.ovsoce.moviero.main;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.blogspot.ovsoce.moviero.R;
import kr.blogspot.ovsoce.moviero.main.vo.vointerface.ProgramData;

public class RecyclerViewAdapter extends RecyclerView.Adapter implements Filterable{
    public ArrayList<ProgramData> getSearchList() {
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
    public ArrayList<ProgramData> getList() {
        return mList;
    }
    private Context mContext;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, null);
        mContext = layoutView.getContext();
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProgramData data = mSearchList.get(position);
        String name = data.getScheduleName();
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.titleTv.setText(name);
        myViewHolder.descriptionTv.setText(getDescription(data));
        myViewHolder.channelTv.setText(getDay(data.getBeginDate()));
        myViewHolder.assistV.setTag(R.string.tag_key_position, position);
        myViewHolder.notificationsIBtn.setTag(R.string.tag_key_position, position);
        myViewHolder.notificationsIBtn.setTag(R.string.tag_key_schedule_id, data.getScheduleId());
        myViewHolder.notificationsIBtn.setImageResource(data.getNotificationsValue().equals("-1") ? R.drawable.ic_social_notifications_none_off : R.drawable.ic_social_notifications_none);
    }
    private String getDescription(ProgramData data) {
        StringBuilder descSb = new StringBuilder();
        descSb.append(data.getChannelName());
        descSb.append(" | ");
        descSb.append(data.getBeginTime() + "~" + data.getEndTime());
        descSb.append(" | ");
        descSb.append(data.getRuntime() + "분");

        return descSb.toString();
    };
    private String getToday() {
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = fm1.format(new Date());
        return date;
    }
    public String getDay(String beginDateStr){
        String start = getToday();
        String end = beginDateStr;
        String day = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date today = formatter.parse(start);
            Date beginDate = formatter.parse(end);

            // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
            long diff = beginDate.getTime() - today.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            //System.out.println("날짜차이=" + diffDays);

            Resources res = mContext.getResources();
            int index = (int) diffDays;
            if(diffDays >= 0) {
                if(diffDays < 5) {
                    day = res.getStringArray(R.array.day_after)[index];
                } else {
                    day = diffDays + res.getStringArray(R.array.day_after)[5];
                }
            } else {
                if(diffDays > -4) {
                    day = res.getStringArray(R.array.day_before)[Math.abs(index)-1];
                } else {
                    day = Math.abs(diffDays) + res.getStringArray(R.array.day_before)[3];
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return day;
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
        ImageButton notificationsIBtn;
        View assistV;

        public MyViewHolder(View itemView) {
            super(itemView);
            channelTv = (TextView) itemView.findViewById(R.id.tv_channel_name);
            notificationsIBtn = (ImageButton) itemView.findViewById(R.id.ibtn_notifications);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            descriptionTv = (TextView) itemView.findViewById(R.id.tv_description);
            assistV = itemView.findViewById(R.id.recyclerview_assist_item);
            assistV.setOnClickListener(onItemClickListener);
            notificationsIBtn.setOnClickListener(onItemClickListener);
        }
    }
}
