package food2you.hp.shoitout.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import food2you.hp.shoitout.R;
import food2you.hp.shoitout.model.CardListModel;


public class CardListNotificationAdapter extends RecyclerView.Adapter<CardListNotificationAdapter.MyViewHolder> {



    private ArrayList<CardListModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewComment;
        TextView textViewNameNoti;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewComment = (TextView) itemView.findViewById(R.id.textViewComment);
            this.textViewNameNoti = (TextView) itemView.findViewById(R.id.textViewNameNoti);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.profile_pic_noti);
        }
    }

    public CardListNotificationAdapter(ArrayList<CardListModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout_notification, parent, false);

     //   view.setOnClickListener(myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewComment = holder.textViewComment;
        TextView textViewNameNoti = holder.textViewNameNoti;
        ImageView imageView = holder.imageViewIcon;

        textViewComment.setText(dataSet.get(listPosition).getVersion());
        textViewNameNoti.setText(dataSet.get(listPosition).getName());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
