package food2you.hp.shoitout.service;


import java.util.List;

import food2you.hp.shoitout.model.EventList;
import food2you.hp.shoitout.model.event;
import food2you.hp.shoitout.model.eventbody;
import food2you.hp.shoitout.model.eventresponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @POST("events")
    Call<eventresponse> CreateEvent(
                                @Header("Accept") String accept,
                                 @Header("Content-Type") String content,
                                 @Header("Authorization") String token,
                                 @Body event body);

    @PUT("events/update_event")
    Call<eventresponse> UpdateEvent(
            @Header("Accept") String accept,
            @Header("Content-Type") String content,
            @Header("Authorization") String token,
            @Body event body);

    @DELETE("events/delete_event")
    Call<eventresponse> DeleteEvent(
            @Header("Accept") String accept,
            @Header("Content-Type") String content,
            @Header("Authorization") String token,
            @Body event body);




    @GET("events")
    Call<EventList> getEventList(@Header("Accept") String content,
            @Header("Authorization") String token);

















}
