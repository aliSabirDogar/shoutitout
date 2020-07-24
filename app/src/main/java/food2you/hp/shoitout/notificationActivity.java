package food2you.hp.shoitout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import food2you.hp.shoitout.adapters.CardListNotificationAdapter;
import food2you.hp.shoitout.model.CardListModel;
import food2you.hp.shoitout.model.MyDataNotification;

public class notificationActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CardListModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //   View view=inflater.inflate(R.layout.fragment_discover, container, false);

        myOnClickListener = new MyOnClickListener(notificationActivity.this);

        recyclerView = findViewById(R.id.my_recycler_view_noti);


        layoutManager = new LinearLayoutManager(notificationActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.recycle_view_divider));
        recyclerView.addItemDecoration(divider);

        data = new ArrayList<CardListModel>();
        for (int i = 0; i < MyDataNotification.Name.length; i++) {
            data.add(new CardListModel(
                    MyDataNotification.Name[i],
                    MyDataNotification.Coments[i],
                    0,
                    MyDataNotification.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CardListNotificationAdapter(data);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //  removeItem(v);
        }


    }
}
