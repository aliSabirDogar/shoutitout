package food2you.hp.shoitout.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import food2you.hp.shoitout.DetailEvent;
import food2you.hp.shoitout.ListListenerInterface.CustomItemClickListener;
import food2you.hp.shoitout.R;
import food2you.hp.shoitout.model.ModelHorizontalList;
import food2you.hp.shoitout.model.eventbody;


/*public class HomeScreenVerticalListAdapter extends RecyclerView.Adapter<HomeScreenVerticalListAdapter.MyViewHolder>  {

    public Context con;
    private List<eventbody> EventsList;
    private eventbody events;
   // private CustomItemClickListener onItemClickListener;

    private ArrayList<ModelHorizontalList> dataSet;
    public  View.OnClickListener mOnItemClickListener;





    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TvNameLeaderBorad_horizontal;
        TextView event_title,event_title_detail,date;
        ImageView event_image;

        public MyViewHolder(View itemView) {
            super(itemView);

                itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


            this.event_title = (TextView) itemView.findViewById(R.id.event_title);
            this.event_image = (ImageView) itemView.findViewById(R.id.title_image);
            this.event_title_detail = (TextView) itemView.findViewById(R.id.event_title_loc);
            this.date = (TextView) itemView.findViewById(R.id.event_title_date);

        }
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public HomeScreenVerticalListAdapter(List<eventbody> eventsList, Context con) {
        EventsList = eventsList;
        this.con=con;
      //  onItemClickListener = clickListener;
    }

    @Override
    public HomeScreenVerticalListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item_vertical, parent, false);

      *//*  view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(con, DetailEvent.class);
                con.startActivity(intent);

               // Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
            }
        });*//*

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeScreenVerticalListAdapter.MyViewHolder holder, final int listPosition) {


        TextView textViewName = holder.event_title;;

        ImageView imageView = holder.event_image;


*//*

        textViewName.setText(EventsList.get(listPosition).getName());

        imageView.setImageResource(EventsList.get(listPosition).getImage());
*//*


        events = EventsList.get(listPosition);
        holder.event_title.setText(events.getFullName());
        holder.date.setText(events.getEventDate());
        holder.event_title_detail.setText(events.getDescription());
        Glide.with(con).load(events.getEventBanner()).into(((MyViewHolder)holder).event_image);




    }

    @Override
    public int getItemCount() {
        return EventsList.size();
    }
}*/

public class HomeScreenVerticalListAdapter extends RecyclerView.Adapter<HomeScreenVerticalListAdapter.MyViewHolder> {
    public Context con;
    private List<eventbody> EventsList;
    private eventbody events;
    private View.OnClickListener mOnItemClickListener;


    public HomeScreenVerticalListAdapter(List<eventbody> eventsList, Context con) {
        EventsList = eventsList;
        this.con=con;
        //  onItemClickListener = clickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item_vertical, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        events = EventsList.get(position);
        holder.event_title.setText(events.getFullName());
        holder.date.setText(events.getEventDate());
        holder.event_title_detail.setText(events.getDescription());
        Glide.with(con).load(events.getEventBanner()).into(((MyViewHolder)holder).event_image);
    }

    @Override
    public int getItemCount() {
        return EventsList.size();
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TvNameLeaderBorad_horizontal;
        TextView event_title,event_title_detail,date;
        ImageView event_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.event_title = (TextView) itemView.findViewById(R.id.event_title);
            this.event_image = (ImageView) itemView.findViewById(R.id.title_image);
            this.event_title_detail = (TextView) itemView.findViewById(R.id.event_title_loc);
            this.date = (TextView) itemView.findViewById(R.id.event_title_date);

            //TODO: Step 3 of 4: setTag() as current view holder along with
            // setOnClickListener() as your local View.OnClickListener variable.
            // You can set the same mOnItemClickListener on multiple views if required
            // and later differentiate those clicks using view's id.
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}

