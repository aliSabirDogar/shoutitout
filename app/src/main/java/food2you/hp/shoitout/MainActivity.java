package food2you.hp.shoitout;


import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

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


public class MainActivity extends Activity{


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager_VERTICAL,layoutManager_HORIZONTAL;
    private static RecyclerView recyclerView_VERTICAL,recyclerView_HORIZONTAL;
    private static ArrayList<ModelHorizontalList> data;
    private RecyclerView.Adapter AdapterForUpcomingEvents;
    private  APIService apiInterface;


    TextView create_event;

    String token;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_event=findViewById(R.id.create_event);

        SharedPreferences sharedPreferences = getSharedPreferences("shoutitout", MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        Log.d("tokenPassed",token);


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
    }// end  of OnCreate Method.



    void  getUpComingEventListFromServer(){





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


                   AdapterForUpcomingEvents = new UpComingEventsAdapter(response.body().getData(),getApplicationContext());
                    recyclerView_VERTICAL.setAdapter(AdapterForUpcomingEvents);

                }else {

                    Log.d("Response:",response.body().getMsg());

                }




            }

            @Override
            public void onFailure(Call<EventList> call, Throwable t) {

            }
        });









    }




}
