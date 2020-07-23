package food2you.hp.shoitout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.regions.Regions;
import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

import dmax.dialog.SpotsDialog;
import food2you.hp.shoitout.model.EventList;
import food2you.hp.shoitout.model.event;
import food2you.hp.shoitout.model.eventresponse;
import food2you.hp.shoitout.service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailEvent extends AppCompatActivity {

    TextView person_name,party,ticket,no_people,date,desc;
    ImageView banner,edit,del;
    public static int update=0;

    public static ImageView imageview;

    public static ImageView camera;
    SpotsDialog dailog ;





    CognitoCachingCredentialsProvider credentialsProvider;

    Retrofit.Builder   builder = new Retrofit.Builder()
            .baseUrl("http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    APIService mAPIServices = retrofit.create(APIService.class);
    private String baseUrl;
    public  String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        dailog = (SpotsDialog) new SpotsDialog.Builder()
                .setContext(this)
                .build();
        TextView map = (TextView) findViewById(R.id.map);
        person_name = (TextView) findViewById(R.id.person_name);
         party = (TextView) findViewById(R.id.party_name);
        ticket = (TextView) findViewById(R.id.ticket_event);
         no_people = (TextView) findViewById(R.id.No_person_event);
               banner = (ImageView) findViewById(R.id.banner_detail);
        edit = (ImageView) findViewById(R.id.edit_event);
        del = (ImageView) findViewById(R.id.del_event);

         desc = (TextView) findViewById(R.id.desc);
        date = (TextView) findViewById(R.id.date_event);
        update=0;

        person_name.setText(MainActivity.person_name);

        party.setText(MainActivity.party);
        ticket.setText(MainActivity.ticket);
        no_people.setText(MainActivity.no_people);
        desc.setText(MainActivity.desc);
        date.setText(MainActivity.date);
       // banner.setImageURI(Uri.parse(MainActivity.banner));
        Glide.with(DetailEvent.this).load(MainActivity.banner).into(banner);
       // Glide.with(DetailEvent.this).load(MainActivity.banner).into((DetailEvent.this).banner);
        AWSinit();
        camera=findViewById(R.id.camera_update);
        imageview=findViewById(R.id.image_update_selected);
        SharedPreferences sharedPreferences = getSharedPreferences("Authtoken", MODE_PRIVATE);

        token = sharedPreferences.getString("token", null);


        baseUrl = "http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/";

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailEvent.this, MapRoute.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                Intent intent = new Intent(DetailEvent.this, UpdateEvent.class);
                startActivity(intent);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailog.show();

              //  Deletefuction(MainActivity.id);

            }
        });
    }


    private void AWSinit(){
        credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "ap-south-1:28937b7b-fce2-4df2-a041-4c6a81afd420", // Identity pool ID
                Regions.AP_SOUTH_1 // Region
        );
//

    }



/*    void  Deletefuction(String id){


        event ebody=new event(Integer.valueOf(id));
        Call<eventresponse> call  =      mAPIServices.DeleteEvent("application/json","application/json",token,ebody);
        call.enqueue(new Callback<eventresponse>() {
            @Override
            public void onResponse(Call<eventresponse> call, Response<EventList> response) {
                if(response.isSuccessful()){
                    Log.d("Response",response.body().getData().getFullname());
                    Log.d("Response",response.body().getData().getEventdate() );
                    Log.d("response:Des",response.body().getData().getDescription() + response.body().getData().getUserId() + response.body().getData().getEventbanner() );
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();

                    dailog.dismiss();

                }else {
                    Log.d("Response",response.body().getMsg());
                    Log.d("Response",response.body().getData().getEventdate() );
                    Log.d("response:Des",response.body().getData().getEventdate());
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    dailog.dismiss();


                }

            }

            @Override
            public void onFailure(Call<eventresponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                dailog.dismiss();
            }
        });





    }*/
}
