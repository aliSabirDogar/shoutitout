package food2you.hp.shoitout.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import food2you.hp.shoitout.R;
import food2you.hp.shoitout.model.eventbody;

public class UpComingEventsAdapter extends RecyclerView.Adapter<UpComingEventsAdapter.ViewHolder> {
    private List<eventbody> EventsList;
    private Context mContext;
    private  eventbody events;


    public UpComingEventsAdapter(List<eventbody> eventsList, Context mContext) {
        EventsList = eventsList;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public UpComingEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item_vertical, parent, false);



        return new UpComingEventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingEventsAdapter.ViewHolder holder, int position) {

        events = EventsList.get(position);
        holder.eventTitle.setText(events.getFullName());
        holder.eventdate.setText(events.getEventDate());
        holder.eventdescription.setText(events.getDescription());
        Glide.with(mContext).load(events.getEventBanner()).into(((ViewHolder)holder).eventBanner);


    }

    @Override
    public int getItemCount() {
        return EventsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eventdescription;
        TextView eventdate;
        TextView eventTitle;
        ImageView eventBanner;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventdate = itemView.findViewById(R.id.event_title_date);
            eventTitle = itemView.findViewById(R.id.event_title);
            eventBanner = itemView.findViewById(R.id.title_image);
            eventdescription = itemView.findViewById(R.id.event_title_loc);



        }
    }















}

