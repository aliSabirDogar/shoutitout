package food2you.hp.shoitout;

import com.google.gson.JsonObject;

import food2you.hp.shoitout.model.CreateEventModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

  //  String ENDPOINT = "http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/";

    @Headers("Content-Type: application/json")
    @POST("events")
    Call<CreateEventModel> postData(
            @Body JsonObject body);








}
