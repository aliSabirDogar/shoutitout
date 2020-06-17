package food2you.hp.shoitout.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //Declaring Base Url for retrofit and creating retrofit insttance
    public static final String BASE_URL="http://ec2-3-12-111-3.us-east-2.compute.amazonaws.com/api/v1/";
    public static Retrofit retrofit = null;


    //this method will return the instance of retrofit
    public static Retrofit getApiClient(){

        if (retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }






}
