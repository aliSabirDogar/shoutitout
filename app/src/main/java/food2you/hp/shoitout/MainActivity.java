package food2you.hp.shoitout;


import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import food2you.hp.shoitout.ListListenerInterface.CustomItemClickListener;
import food2you.hp.shoitout.adapters.HomeScreenHorizontalListAdapter;
import food2you.hp.shoitout.adapters.HomeScreenVerticalListAdapter;
import food2you.hp.shoitout.adapters.UpComingEventsAdapter;
import food2you.hp.shoitout.model.EventList;
import food2you.hp.shoitout.model.ModelHorizontalList;
import food2you.hp.shoitout.model.eventbody;
import food2you.hp.shoitout.model.testData;
import food2you.hp.shoitout.service.APIService;
import food2you.hp.shoitout.service.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity implements RecyclerView.OnItemTouchListener{


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager_VERTICAL,layoutManager_HORIZONTAL;
    private static RecyclerView recyclerView_VERTICAL,recyclerView_HORIZONTAL;
    private static ArrayList<ModelHorizontalList> data;
    private RecyclerView.Adapter AdapterForUpcomingEvents;
    private  APIService apiInterface;

    SpotsDialog dailog ;
    TextView create_event;
    private List<eventbody> EventsList;

    String token;


    public static String  person_name,party,ticket,no_people,date,desc,banner,email,id;






    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            person_name=EventsList.get(position).getFullName();
            party=EventsList.get(position).getFullName();
            ticket=String.valueOf(EventsList.get(position).getTicketPrice());
            no_people=String.valueOf(EventsList.get(position).getNoOfPeople());
            date=EventsList.get(position).getEventDate();
            desc=EventsList.get(position).getDescription();
            banner=EventsList.get(position).getEventBanner();
            email=EventsList.get(position).getEmail();
            id=EventsList.get(position).getId().toString();
            Toast.makeText(getApplicationContext(),EventsList.get(position).getFullName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DetailEvent.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.this.startActivity(intent);
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_event=findViewById(R.id.create_event);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        SharedPreferences sharedPreferences = getSharedPreferences("Authtoken", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        Log.d("tokenPassed",token);



        dailog = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .build();

       /* final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUpComingEventListFromServer();
                pullToRefresh.setRefreshing(false);
            }
        });*/

        apiInterface = RetrofitClient.getApiClient().create(APIService.class);


        recyclerView_VERTICAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_vertical);
        recyclerView_HORIZONTAL = (RecyclerView) findViewById(R.id.my_recycler_view_hot_horizontal);
        recyclerView_VERTICAL.setHasFixedSize(true);
        recyclerView_HORIZONTAL.setHasFixedSize(true);

        layoutManager_VERTICAL = new LinearLayoutManager(MainActivity.this);
        recyclerView_VERTICAL.setLayoutManager(layoutManager_VERTICAL);





        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_HORIZONTAL.setLayoutManager(layoutManager);

        data = new ArrayList<ModelHorizontalList>();
        for (int i = testData.NameEvent.length-1; i >= 0; i--) {
            data.add(new ModelHorizontalList(
                    testData.NameEvent[i],

                    testData.drawableArray[i]
            ));
        }






        data = new ArrayList<ModelHorizontalList>();
        for (int i = 0; i < testData.NameEvent.length; i++) {
            data.add(new ModelHorizontalList(
                    testData.NameEvent[i],
                    testData.drawableArray[i]
            ));
        }



        adapter = new HomeScreenHorizontalListAdapter(data,MainActivity.this);
        recyclerView_HORIZONTAL.setAdapter(adapter);

        getUpComingEventListFromServer();

        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateEvent.class);
                startActivity(intent);
               // finish();

            }
        });
    }

    // end  of OnCreate Method.



    void  getUpComingEventListFromServer(){

        dailog.show();



//        Retrofit.Builder   builder = new Retrofit.Builder()
//                .baseUrl("http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
   //     APIService mAPIServices = retrofit.create(APIService.class);



        Call<EventList> call = apiInterface.getEventList("application/json",token);
        call.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(Call<EventList> call, Response<EventList> response) {

                Log.d("Response:",response.message());
//

                if(response.isSuccessful()){


                    Log.d("Response:",response.body().getMsg());
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    EventsList = response.body().getData();
                    HomeScreenVerticalListAdapter recyclerViewAdapter = new HomeScreenVerticalListAdapter(response.body().getData(),getApplicationContext());
                   /*AdapterForUpcomingEvents = new HomeScreenVerticalListAdapter(response.body().getData(),getApplicationContext());
                    recyclerView_VERTICAL.setAdapter(AdapterForUpcomingEvents);
                    AdapterForUpcomingEvents.setOnItemClickListener(onItemClickListener);*/

                    //recyclerView_VERTICAL.setLayoutManager(new LinearLayoutManager(this));
                    recyclerView_VERTICAL.setAdapter(recyclerViewAdapter);
                    //TODO: Step 1 of 4: Create and set OnItemClickListener to the adapter.
                    recyclerViewAdapter.setOnItemClickListener(onItemClickListener);
                    dailog.dismiss();
                }else {

                    if(response.body()!=null) {
                        Log.d("Response:", response.body().getMsg());
                        Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                    dailog.dismiss();
                }




            }

            @Override
            public void onFailure(Call<EventList> call, Throwable t) {

            }
        });









    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        Intent intent = new Intent(MainActivity.this, DetailEvent.class);
        startActivity(intent);
        //   finish();

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

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


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    // toolbar.setTitle("Shop");
                    return true;
                case R.id.navigation_gifts:
                    // toolbar.setTitle("My Gifts");
                    return true;
                case R.id.navigation_cart:
                    Intent intent2 = new Intent(getApplicationContext(), notificationActivity.class);
                    startActivity(intent2);
                    // toolbar.setTitle("Cart");
                    return true;
                case R.id.navigation_noti:

                    Intent intent = new Intent(getApplicationContext(), Profile.class);
                    startActivity(intent);
                    // toolbar.setTitle("Cart");
                    return true;

            }
            return false;
        }
    };

}
