package food2you.hp.shoitout.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import food2you.hp.shoitout.DetailEvent;
import food2you.hp.shoitout.R;
import food2you.hp.shoitout.model.ModelHorizontalList;


public class HomeScreenHorizontalListAdapter extends RecyclerView.Adapter<HomeScreenHorizontalListAdapter.MyViewHolder> {

    public Context con;

    private ArrayList<ModelHorizontalList> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TvNameLeaderBorad_horizontal;
        TextView event_title;
        ImageView event_image;

        public MyViewHolder(View itemView) {
            super(itemView);


            this.event_title = (TextView) itemView.findViewById(R.id.event_title);
            this.event_image = (ImageView) itemView.findViewById(R.id.title_image);

        }
    }

    public HomeScreenHorizontalListAdapter(ArrayList<ModelHorizontalList> data, Context con) {
        this.dataSet = data;
        this.con=con;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item_horizontal, parent, false);

        //   view.setOnClickListener(myOnClickListener);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(con, DetailEvent.class);
                con.startActivity(intent);

                // Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
            }
        });

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        TextView textViewName = holder.event_title;;

        ImageView imageView = holder.event_image;



        textViewName.setText(dataSet.get(listPosition).getName());

        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
